package com.isikerhan

/**
  * @author Işık Erhan
  */
package object math {

  private[math] object MathUtils {
    def nthRoot(value: Double, root: Double): Double = {
      if (value < 0)
        throw new IllegalArgumentException("Value cannot be negative")
      //use built-in methods for square and cubic roots
      if (root == 2)
        return Math.sqrt(value)
      if (root == 3)
        return Math.cbrt(value)
      //not exact nth root TODO: improve
      Math.pow(Math.E, Math.log(value) / root)
    }
  }

}