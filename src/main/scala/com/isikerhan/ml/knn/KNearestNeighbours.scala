package com.isikerhan.ml.knn

import com.isikerhan.math.Distance
import com.isikerhan.math.distance.DistanceFunction
import org.apache.spark.rdd.RDD

/**
  * @author Işık Erhan
  */
class KNearestNeighbours(distanceFunction: DistanceFunction, k: Int, trainingData: RDD[Data]) extends Serializable {

  def this(k: Int, trainingData: RDD[Data]) = this(Distance.EuclideanDistance, k, trainingData)

  private def classify(nearestNeighbours: Iterable[(Double, Data)]): String = {
    nearestNeighbours.take(k)
      .map(tuple => tuple._2._2)
      .groupBy(identity)
      .mapValues(_.size)
      .toSeq
      .sortWith(_._2 > _._2)
      .map(_._1)
      .head
  }

  def predict(testData: RDD[RawData]): RDD[Data] = {
    val numberOfPartitions = trainingData.getNumPartitions
    trainingData.cartesian(testData)
      //map to (testData, distance), trainData)
      .map(tuple => ((tuple._2, distanceFunction.getDistance(tuple._1._1, tuple._2)), tuple._1))
      .repartitionAndSortWithinPartitions(new KNNPartitioner(numberOfPartitions)) //preserve number of partitions
      .map(tuple => (tuple._1._1, (tuple._1._2, tuple._2)))
      .groupByKey()
      .map(tuple => (tuple._1, classify(tuple._2)))
  }
}
