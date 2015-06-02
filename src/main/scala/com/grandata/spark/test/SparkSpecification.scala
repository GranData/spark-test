package com.grandata.spark.test

import org.specs2.mutable.Specification

/**
 * Base class for specifications involving Spark and RDDs manipulation. It provides a global mocked
 * Spark Context instance and helper methods for mocking RDDs and checking content saved by those RDDs
 * 
 * @author Esteban Donato
 */
abstract class SparkSpecification extends Specification {
  
  /**
   * global Spark Context shared across all the Specification classes
   */
  val sc = MockedSparkContext
  
  def mockRDD = ???
}