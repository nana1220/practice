package ch4treesandgraphs;

import java.lang.System;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class FindGraphRoute {
  // use hashcode to check if two objects are equal
  // if their hashcodes are equal use equal() to see if they are really equal
  // can instead add a visited state to GraphNode
  static HashSet<GraphNode> visited = new HashSet<GraphNode>();

  /*
   * iterative BFS
   */
  static boolean findRouteBFS(Graph graph, GraphNode node1, GraphNode node2) {
    LinkedList<GraphNode> queue = new LinkedList<ch4treesandgraphs.GraphNode>();
    visited.add(node1);
    queue.add(node1);
    while (!queue.isEmpty()) {
      GraphNode node = queue.poll();
      if (node.adjacentCount != 0) {
        for (GraphNode aNode : node.getAdjacent()) {
          if (!visited.contains(aNode)) {
            visited.add(aNode);
            if (aNode == node2) {
              return true;
            }
            queue.add(aNode);
          }
        }
      }
    }
    return false;
  }

  /*
   * recursive DFS
   */
  static boolean findRouteDfsRecur(Graph graph, GraphNode node1, GraphNode node2) {
    if (node1 == null) {
      return false;
    }
    visited.add(node1);
    if (node1 == node2) {
      return true;
    }
    boolean res = false;
    if (node1.adjacentCount != 0) {
      for (GraphNode node : node1.getAdjacent()) {
        if (!visited.contains(node)) {
          visited.add(node);
          res = res || findRouteDfsRecur(graph, node, node2);
        }
      }
    }
    return res;
  }

  /*
   * iterative DFS
   */
  static boolean findRouteDfsIter(Graph graph, GraphNode node1, GraphNode node2) {
    Stack<GraphNode> stack = new Stack<GraphNode>();
    stack.push(node1);
    while (!stack.isEmpty()) {
      GraphNode node = stack.pop();
      if (!visited.contains(node)) {
        visited.add(node);
        if (node == node2) {
          return true;
        }
        if (node.adjacentCount != 0) { // don't forget to check
          for (GraphNode aNode : node.getAdjacent()) {
            stack.push(aNode);
          }
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    GraphNode node1 = new GraphNode("node1", 2);
    GraphNode node2 = new GraphNode("node2", 2);
    GraphNode node3 = new GraphNode("node3", 2);
    GraphNode node4 = new GraphNode("node4", 2);
    GraphNode node5 = new GraphNode("node5", 1);
    GraphNode node6 = new GraphNode("node6", 0);
    node1.addAdjacent(node2);
    node1.addAdjacent(node4);
    node2.addAdjacent(node4);
    node2.addAdjacent(node5);
    node3.addAdjacent(node2);
    node3.addAdjacent(node4);
    node4.addAdjacent(node5);
    node4.addAdjacent(node1);
    node5.addAdjacent(node6);
    Graph graph = new Graph(6);
    graph.addNode(node1);
    graph.addNode(node2);
    graph.addNode(node3);
    graph.addNode(node4);
    graph.addNode(node5);
    graph.addNode(node6);

    System.out.println(findRouteBFS(graph, node1, node6));
    visited.clear();
    System.out.println(findRouteDfsRecur(graph, node1, node6));
    visited.clear();
    System.out.println(findRouteDfsIter(graph, node1, node6));

    visited.clear();
    System.out.println(findRouteBFS(graph, node1, node3));
    visited.clear();
    System.out.println(findRouteDfsRecur(graph, node1, node3));
    visited.clear();
    System.out.println(findRouteDfsIter(graph, node1, node3));
  }
}

class GraphNode {
  private GraphNode adjacent[];
  public int adjacentCount;
  private String vertex;
  public GraphNode(String vertex, int adjacentLength) {
    this.vertex = vertex;
    adjacentCount = 0; // need to add adjacent afterward
    adjacent = new GraphNode[adjacentLength];
  }

  public void addAdjacent(GraphNode node) {
    this.adjacent[adjacentCount] = node;
    adjacentCount++;
  }
  public GraphNode[] getAdjacent() {
    return adjacent;
  }
  public String getVertex() {
    return vertex;
  }
}

class Graph {
  private GraphNode vertices[];
  public int count;
  public Graph(int nodeCount) {
    vertices = new GraphNode[nodeCount];
    count = 0; // need to add nodeCount of GraphNode afterward
  }

  public void addNode(GraphNode node) {
    vertices[count] = node;
    count++;
  }

  public GraphNode[] getNodes() {
    return vertices;
  }
}