import java.lang.String;
import java.lang.System;
import java.util.LinkedList;

/*
 * BFS impl
 */
public class BFS {

  static void traverse(Node root) {
    // use a queue as buffer, nodes in the queue have been processed
    // dequeue node to get its children, process them and enqueue them
    LinkedList<Node> queue = new LinkedList<Node>();
    root.visited = true;
    root.print(); // process root
    queue.add(root); // enqueue root
    while (!queue.isEmpty()) {
      Node node = queue.poll(); // dequeue processed node
      for (Node aNode : node.children) { // traverse its children
        if (!aNode.visited) {
          aNode.visited = true; // mark visited
          aNode.print(); // process children node
          queue.add(aNode); // enqueue
        }
      }
    }
  }

  static class Node {
    Node[] children;
    int val;
    boolean visited = false;

    void print() {
      System.out.println(val);
    }
  }
}