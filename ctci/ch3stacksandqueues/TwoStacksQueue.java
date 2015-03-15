package ch3stacksandqueues;

import java.lang.Integer;
import java.util.Stack;

public class TwoStacksQueue {
  Stack<Integer> stack1 = new Stack<Integer>();
  Stack<Integer> stack2 = new Stack<Integer>();

  void shiftElems(Stack<Integer> stack1, Stack<Integer> stack2) {
    while (!stack1.isEmpty()) {
      stack2.push(stack1.pop());
    }
  }

  int size() {
    return stack1.isEmpty() ? stack2.size() : stack1.size();
  }

  void add(Integer elem) {
    if (!stack2.isEmpty()) {
      shiftElems(stack2, stack1);
    }
    stack1.push(elem);
  }

  Integer remove(Integer elem) {
    if (!stack1.isEmpty()) {
      shiftElems(stack1, stack2);
    }
    return stack2.pop();
  }
}