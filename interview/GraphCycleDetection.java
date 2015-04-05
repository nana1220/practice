/*
Graph g = new Graph();
g.addEdge(“abb”, “cde”);
g.addEdge(“abb”, “ff”);
g.addEdge(“ff”, “abb”); // so it's a directed graph

implement  graph, addEdge,
implement cycle detection
 */
import java.util.*;
import java.util.HashSet;
import java.util.Stack;

class Node {
  String name;
  List<Node> adj;

  Node(String name) {
    this.name = name;
    adj = new ArrayList<Node>();
  }
}
class Graph {
  // ArrayList<Node>[] nodes;
  HashMap<String, Node> nodes;

  void addEdge(String name1, String name2) {
    if(!nodes.containsKey(name1)) {
      Node tmp = new Node(name1);
      nodes.put(name1, tmp);
    }
    if(!nodes.containsKey(name2)) {
      Node tmp = new Node(name2);
      nodes.put(name2, tmp);
    }
    nodes.get(name1).adj.add(nodes.get(name2));
  }

  /*
  To detect a back edge, we can keep track of vertices currently in recursion stack of function for
  DFS traversal. If we reach a vertex that is already in the recursion stack, then there is a cycle
  in the tree. The edge that connects current vertex to the vertex in the recursion stack is back edge.
  It must be both visited and on stack, then it is a cycle.
   */
  boolean isCyclic(Node node, Stack<Node> stack, HashSet<Node> visited) {
    if(node == null) return false;
    visited.add(node);
    stack.push(node);
    for (Node n : node.adj) {
      if (visited.contains(n)) {
        if (stack.contains(n)) // only if both visited and in stack means cycle, if not in stack skip
          return true;
      }
      else if (isCyclic(n, stack, visited)) return true; // don't simply return iscycle, only return
    }
    stack.pop(); // NOTE:!!!! must pop from stack before return fasle, then backtrack
    return false; // return false means this path not cyclic, need to backtrack and check other path
  }

  boolean isCyclic() {
    // NOTE: each call need a new stack and a new set
    for (Node node : nodes.values()) {
      if (isCyclic(node, new Stack<Node>(),  new HashSet<Node>())) return true;
    }
    return false;  // don't forget return
  }
}
