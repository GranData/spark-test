package com.grandata.spark.test

import org.apache.spark.rdd.RDD
import scala.reflect.ClassTag
import org.apache.spark.TaskContext
import org.apache.spark.Partition

/**
 * RDD adapter that forwards all the method calls to the internal RDD instance and intercepts all
 * the saveXX method calls to mock the i/o operations.
 * 
 * @author Esteban Donato
 */
class MockedSaveActionRDD[T: ClassTag](internal: RDD[T]) 
  extends RDD[T](internal.sparkContext, internal.dependencies) {
  //TODO test if we should call this(oneParent: RDD[_]) RDD's constructor instead.
  //This could save us to override all the RDD's methods 
  
  override def compute(split: Partition, context: TaskContext): Iterator[T] = 
    internal.compute(split, context)

  override def getPartitions: Array[Partition] = internal.partitions
}