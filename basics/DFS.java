package basics;

import java.util.Stack;

/*
 * parent recursion pass information to child recursion through parameters
 * child recursion pass information to parent through return value
 * parent and child share information through reference parameters or static/global variables
 *
 * DFS impl. recursive and iterative
 */
public class DFS {

  static void traverseRecursive(Node root) {
    if (root == null) {
      return;
    }
    // mark visited (process current node)
    // forward recursion can manipulate variables and pass to method parameters for next recursion
    root.visited = true;
    root.print(); // process node
    for (Node child : root.children) {
      if (!child.visited) {
        // process first child first at each level
        traverseRecur(child); // go to next level, if children == null, return
      }
    }
    // can also mark (process) after recursive calls
    // backward recursion can manipulate return values and pass to previous recursion
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