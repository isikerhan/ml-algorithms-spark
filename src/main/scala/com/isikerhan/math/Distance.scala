package com.isikerhan.math

import com.isikerhan.math.distance.DistanceFunction

/**
  * @author Işık Erhan
  */
object Distance extends Serializable {

  val EuclideanDistance: DistanceFunction = (vector: Vector[Number], other: Vector[Number]) => {
    if (vector.size != other.size)
      throw new IllegalArgumentException
    Math.sqrt(
      vector.zipWithIndex.map { e =>
        val (value, i) = (e._1, e._2)
        Math.pow(value.doubleValue() - other.apply(i).doubleValue(), 2)
      }.sum
    )
  }
}
