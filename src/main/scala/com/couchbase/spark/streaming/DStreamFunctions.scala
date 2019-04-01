/*
 * Copyright (c) 2015 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.couchbase.spark.streaming

import com.couchbase.client.java.document.Document
import com.couchbase.spark._
import org.apache.spark.SparkContext
import org.apache.spark.streaming.dstream.DStream


class DStreamFunctions[D <: Document[_]](dstream: DStream[D]) extends Serializable {

  def sparkContext: SparkContext = dstream.context.sparkContext

  def saveToCouchbase(bucketName: String = null): Unit = {
    dstream.foreachRDD(_.saveToCouchbase(bucketName))
  }

  def saveToCouchbase(storeMode: StoreMode): Unit = {
    saveToCouchbase(null, storeMode)
  }

  def saveToCouchbase(bucketName: String, storeMode: StoreMode): Unit = {
    dstream.foreachRDD(_.saveToCouchbase(bucketName, storeMode))
  }

}
