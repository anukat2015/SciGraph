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
package edu.sdsc.scigraph.internal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import edu.sdsc.scigraph.neo4j.Graph;
import edu.sdsc.scigraph.owlapi.OwlLabels;
import edu.sdsc.scigraph.owlapi.OwlRelationships;
import edu.sdsc.scigraph.util.GraphTestBase;

public class GraphApiCategoryTest extends GraphTestBase {

  GraphApi graphApi;
  Graph graph;

  static String BASE_URI = "http://example.org/";

  static String uri = BASE_URI + "#fizz";
  static String uri2 = BASE_URI + "#fuzz";
  static String uri3 = BASE_URI + "#fazz";
  Node a;
  Node b;
  Node c;

  @Before
  public void addNodes() throws Exception {
    graph = new Graph(graphDb);
    a = graph.getOrCreateNode(uri);
    a.addLabel(OwlLabels.OWL_CLASS);
    b = graph.getOrCreateNode(uri2);
    b.addLabel(OwlLabels.OWL_CLASS);
    c = graph.getOrCreateNode(uri3);
    c.addLabel(OwlLabels.OWL_CLASS);
    graph.getOrCreateRelationship(a, b, OwlRelationships.RDF_SUBCLASS_OF);
    this.graphApi = new GraphApi(graph);
  }

  @Test
  public void testFoundClass() {
    assertThat(graphApi.classIsInCategory(a, b), is(true));
  }

  @Test
  public void testUnconnectedClass() {
    assertThat(graphApi.classIsInCategory(b, c), is(false));
  }

  /***
   * TODO: Move this to a GraphApiTest class
   */
  @Test
  public void testSelfLoop() {
    assertThat(graphApi.getSelfLoops(), is(empty()));
    Node t = graph.getOrCreateNode(BASE_URI + "#fozz");
    Relationship r = graph.getOrCreateRelationship(t, t, OwlRelationships.RDF_SUBCLASS_OF);
    assertThat(graphApi.getSelfLoops(), contains(r));
  }

}
