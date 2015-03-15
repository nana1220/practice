package ch3stacksandqueues;

import java.lang.Integer;
import java.lang.Override;
import java.util.Stack;

/*
 * couple each element with the min value at and below its level
 */
class MinvalueStack1 extends Stack<MinvalueStack1.Node> { // stored data type is Node

  public void push(int data) { // input parameter is still int, Node is not exposed
    int min = peek().min;
    if (data < min) {
      min = data;
    }
    push(new Node(data, min));
  }

  static class Node {
    int val;
    int min;

    Node(int val, int min) {
      this.val = val;
      this.min = min;
    }
  }
}

/*
 * use another stack keeping track of min
 */
class MinvalueStack2 extends Stack<Integer> {

  Stack<Integer> minStack = new Stack<Integer>();

  @Override
  public Integer push(Integer data) {
    int min = minStack.peek();
    if (data <= min) {
      // Note: if data = min, which means there are multiple min values in the stack
      // so mutiple min values should also be pushed to minStack
      // min value doesn't change until they are all gone
      minStack.push(data);
    }
    return super.push(data);
  }

  @Override
  public Integer pop() {
    int val = super.pop();
    if (val == minStack.peek()) {
      minStack.pop();
    }
    return val;
  }

}