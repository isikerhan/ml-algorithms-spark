package com.isikerhan.ml.knn

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Işık Erhan
  */
object KNNRunner {

  val K = 3

  def main(args: Array[String]): Unit = {

    val trainData = List(
      (Vector[Number](1.0, 2.0, 3.0), "A"),
      (Vector[Number](8.0, 9.0, 15), "A"),
      (Vector[Number](3.0, 4.0, 5), "A"),
      (Vector[Number](0.0, 0.0, 0), "A"),
      (Vector[Number](-1.0, 1.0, -1), "A"),
      (Vector[Number](2.0, 8.0, 22), "A"),
      (Vector[Number](10.0, 5.0, 25), "A"),
      (Vector[Number](-10.0, 5.0, -20), "A"),
      (Vector[Number](-100.0, -100.0, -100), "A"),
      (Vector[Number](82.0, 100.0, 60), "B"),
      (Vector[Number](100.0, 100.0, 100), "B"),
      (Vector[Number](90.0, 60.0, 90), "B")
    )

    val testData = List(
      Vector[Number](30, 40, 50),
      Vector[Number](130, 140, 150),
      Vector[Number](0.1, 0.12, 0.3),
    )

    val sc = SparkContext.getOrCreate(new SparkConf().setAppName("KNN").setMaster("local"))
    val trainingRDD = sc.parallelize(trainData)
    val testRDD = sc.parallelize(testData)
    val knn = new KNearestNeighbours(K, trainingRDD)
    val prediction = knn.predict(testRDD)
    prediction.foreach(println(_))
  }
}
