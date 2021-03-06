package org.apache.lucene.util;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.PrintStream;

/** Replacement for Assume jUnit class, so we can add a message with explanation */
final class _TestIgnoredException extends RuntimeException {
  
  _TestIgnoredException(String msg) {
    super(msg);
  }
  
  _TestIgnoredException(String msg, Throwable t) {
    super(msg, t);
  }
  
  @Override
  public String getMessage() {
    StringBuilder sb = new StringBuilder(super.getMessage());
    if (getCause() != null)
      sb.append(" - ").append(getCause());
    return sb.toString();
  }
  
  // only this one is called by our code, exception is not used outside this class:
  @Override
  public void printStackTrace(PrintStream s) {
    if (getCause() != null) {
      s.println(super.toString() + " - Caused by:");
      getCause().printStackTrace(s);
    } else {
      super.printStackTrace(s);
    }
  }
}
