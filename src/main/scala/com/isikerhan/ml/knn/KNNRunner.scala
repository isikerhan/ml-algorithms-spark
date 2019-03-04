package com.isikerhan.ml.knn

import java.io.File

import org.apache.spark.{SparkConf, SparkContext}
import org.rogach.scallop._

/**
  * @author Işık Erhan
  */
object KNNRunner {

  def main(args: Array[String]): Unit = {

    val config = new KNNConfig(args)
    config.verify()

    val sc = SparkContext.getOrCreate(new SparkConf().setAppName("KNN").setMaster("local"))

    val trainingRDD = sc.textFile(config.trainingData.apply().getAbsolutePath)
      .map(line => dataFromCSV(line))
    val testRDD = sc.textFile(config.testData.apply().getAbsolutePath)
      .map(line => dataFromCSV(line))
    val knn = new KNearestNeighbours(config.k.apply(), trainingRDD)
    val prediction = knn.predict(testRDD.map(_._1))

    prediction.foreach(println(_))
  }
}

class KNNConfig(args: Seq[String]) extends ScallopConf(args) {
  val trainingData = opt[File](short = 'd', required = true, descr = "Path to training data.", validate = f => f.exists())
  val testData = opt[File](short = 't', required = true, descr = "Path to test data.", validate = f => f.exists())
  val k = opt[Int](required = true, descr = "The K value for KNN", validate = _ > 0)
}
