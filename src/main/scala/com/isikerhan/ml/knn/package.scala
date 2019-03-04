package com.isikerhan.ml

/**
  * @author Işık Erhan
  */
package object knn {
  private[knn] type Data = (RawData, String)
  private[knn] type RawData = Vector[Number]

  implicit val KNNOrdering: Ordering[(RawData, Double)] = (x: (RawData, Double), y: (RawData, Double)) => {
    x._2 compare y._2
  }

  private[knn] def dataFromCSV(line: String): Data = {
    val values = line.split("\\s*\\,\\s*")
    (values.slice(1, values.size).map(_.toDouble).toVector.asInstanceOf[RawData],
      String.valueOf(values.apply(0)))
  }
}
