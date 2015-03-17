import java.lang.String;
import java.util.Stack;
import java.lang.System;

/*
 * DFS impl. recursive and iterative
 */
public class DFS {

  static void traverseRecursive(Node root) {
    if (root == null) {
      return;
    }
    root.visited = true; // mark visited (process), forward recursion
    root.print(); // process node
    for (Node child : root.children) {
      if (!child.visited) {
        // process first child first at each level
        traverseRecur(child); // go to next level, if children == null, return
      }
    }// can also mark (process) after recursive calls, backward recursion
  }

  static void traverseIterative(Node root) {
    // use a stack as buffer, nodes in the queue have not been processed
    // pop node to process it, get its children and push them
    Stack<Node> stack = new Stack<Node>;
    // push first, process node after pop
    // unlike BFS which process node first then enqueue
    stack.push(root);
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      if (!node.visited) {
        node.visited = true;
        node.print();
        for (Node child : node.children) {
          stack.push(node); // process last child first at each level
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