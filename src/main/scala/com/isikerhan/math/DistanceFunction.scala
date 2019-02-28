package com.isikerhan.math.distance

/**
  * @author Işık Erhan
  */
trait DistanceFunction extends Serializable {
  def getDistance(vector: Vector[Number], other: Vector[Number]): Double
}
