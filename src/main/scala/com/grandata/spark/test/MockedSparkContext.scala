package com.grandata.spark.test

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.input.PortableDataStream
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.mapred.FileInputFormat
import org.apache.spark.SerializableWritable
import org.apache.hadoop.mapreduce.{InputFormat => NewInputFormat, Job => NewHadoopJob}
import org.apache.hadoop.mapreduce.lib.input.{FileInputFormat => NewFileInputFormat}
import org.apache.hadoop.mapred.InputFormat
import scala.reflect.ClassTag
import org.apache.spark.WritableConverter

/**
 * sub-class of SparkContext that sets Spark to run locally and returns mocked RDD instances for
 * all the methods that create RDDs (i.e. textFile, wholeTextFiles, hadoopFile, etc)
 * 
 * @author Esteban Donato
 */
protected class MockedSparkContext extends SparkContext("local","test") {
  
  /**
   * it doesn't stop anything at all
   */
  override  def stop() = {}
  
  /**
   * creates a MockedSaveActionRDD instance with the mocked input content defined in the Spark Specifications
   */
  override def textFile(path: String, minPartitions: Int = defaultMinPartitions): RDD[String] =
    new MockedSaveActionRDD(this.parallelize(List("content"), minPartitions)) //TODO here we should pick content defined by the Spec based on the path
  
  /**
   * not implemented
   */
  override def wholeTextFiles(path: String, minPartitions: Int = defaultMinPartitions): 
    RDD[(String, String)] = ???
  
  /**
   * not implemented
   */
  override def binaryFiles(path: String, minPartitions: Int = defaultMinPartitions): 
    RDD[(String, PortableDataStream)] = ???
  
  /**
   * not implemented
   */
  override def hadoopRDD[K, V](
      conf: JobConf,
      inputFormatClass: Class[_ <: InputFormat[K, V]],
      keyClass: Class[K],
      valueClass: Class[V],
      minPartitions: Int = defaultMinPartitions
      ): RDD[(K, V)] = ???
      
 /**
   * not implemented
   */
  override def hadoopFile[K, V](
      path: String,
      inputFormatClass: Class[_ <: InputFormat[K, V]],
      keyClass: Class[K],
      valueClass: Class[V],
      minPartitions: Int = defaultMinPartitions
      ): RDD[(K, V)] = ???
  
  /**
   * not implemented
   */
  override def newAPIHadoopFile[K, V, F <: NewInputFormat[K, V]](
      path: String,
      fClass: Class[F],
      kClass: Class[K],
      vClass: Class[V],
      conf: Configuration = hadoopConfiguration): RDD[(K, V)] = ???
  
  /**
   * not implemented
   */
  override def newAPIHadoopRDD[K, V, F <: NewInputFormat[K, V]](
      conf: Configuration = hadoopConfiguration,
      fClass: Class[F],
      kClass: Class[K],
      vClass: Class[V]): RDD[(K, V)] = ???
  
  /**
   * not implemented
   */
  override def checkpointFile[T: ClassTag](path: String): RDD[T] = ???
}

/**
 * Singleton MockedSparkContext to share across all the Spark Specifications
 */
object MockedSparkContext extends MockedSparkContext