/**
 * Copyright (C) 2014 The SciGraph authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.sdsc.scigraph.neo4j;

import java.util.Objects;

class BatchEdge {

  private final Long start;
  private final Long end;
  private final String type;

  public BatchEdge(long start, long end, String type) {
    super();
    this.start = start;
    this.end = end;
    this.type = type;
  }

  public long getStart() {
    return start;
  }

  public long getEnd() {
    return end;
  }

  public String getType() {
    return type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end, type);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof BatchEdge) {
      BatchEdge c = (BatchEdge) obj;
      return Objects.equals(start, c.getStart()) && Objects.equals(end, c.getEnd())
          && Objects.equals(type, c.getType());
    } else {
      return false;
    }

  }

}