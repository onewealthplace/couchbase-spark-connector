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
package com.couchbase.spark

import com.couchbase.client.java.document.Document
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream

import scala.language.implicitConversions

package object streaming {

  implicit def toDStreamFunctions[D <: Document[_]](ds: DStream[D]): DStreamFunctions[D] =
    new DStreamFunctions[D](ds)

  implicit def toSparkStreamingFunctions(ssc: StreamingContext): SparkStreamingFunctions =
    new SparkStreamingFunctions(ssc)

}
