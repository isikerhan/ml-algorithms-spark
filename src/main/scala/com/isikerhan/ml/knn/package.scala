package com.isikerhan.ml

/**
  * @author Işık Erhan
  */
package object knn {
  type Data = (RawData, String)
  type RawData = Vector[Number]

  implicit val KNNOrdering = new Ordering[(RawData, Double)] {
    override def compare(x: (RawData, Double), y: (RawData, Double)): Int = {
      x._2.compareTo(y._2)
    }
  }
}
