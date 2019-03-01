package com.isikerhan.math

import com.isikerhan.math.distance.DistanceFunction

/**
  * @author Işık Erhan
  */
object Distance extends Serializable {

  class MinkowskiDistance(p: Int) extends DistanceFunction {
    override def getDistance(vector: Vector[Number], other: Vector[Number]): Double = {
      if (vector.size != other.size)
        throw new IllegalArgumentException
      MathUtils.nthRoot(
        vector.zipWithIndex.map { e =>
          val (value, i) = (e._1, e._2)
          Math.pow(Math.abs(value.doubleValue() - other.apply(i).doubleValue()), p)
        }.sum, p
      )
    }
  }

  val ManhattanDistance: DistanceFunction = new MinkowskiDistance(1)
  val EuclideanDistance: DistanceFunction = new MinkowskiDistance(2)
}
