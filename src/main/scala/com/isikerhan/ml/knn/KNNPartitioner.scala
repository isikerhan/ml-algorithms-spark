package com.isikerhan.ml.knn

import org.apache.spark.Partitioner

/**
  * @author Işık Erhan
  */
class KNNPartitioner(partitions: Int) extends Partitioner {
  override def numPartitions: Int = partitions

  override def getPartition(key: Any): Int = {
    val hash = key.asInstanceOf[(RawData, Double)]._1.hashCode()
    (hash % numPartitions + numPartitions) % numPartitions //positive modulo
  }
}
