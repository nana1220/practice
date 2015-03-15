package ch3stacksandqueues;

import java.lang.System;
import java.util.Stack;
import java.lang.Integer;
/*
 * biggest item on top
 */
public class SortStack {

  /*
   * sort and return origin stack
   * time: O(n^2); space: O(n)
   */
  static void sort1(Stack<Integer> stack) {
    Stack<Integer> buffer = new Stack<Integer>();
    int stackHeight = 0; // the height of sorted elements
    while (stack.size() > stackHeight) { // if there are still unsorted elements
      Integer min = stack.peek();
      // can also sort only one elem a time and push other equal valued elems to buffer
      int repNum = 0;
      // push unsorted elements to buffer while find the min value
      while (stack.size() > stackHeight) {
        Integer val = stack.pop();
        if (val == min) {
          repNum++;
        } else if (val < min) {
          while (repNum-- > 0) {
            buffer.push(min);
          }
          min = val;
        } else {
          buffer.push(val);
        }
      }
      // push min value back
      while (repNum > 0) {
        stack.push(min);
        stackHeight++;
        repNum--;
      }
      // push rest elements back
      while (!buffer.isEmpty()) {
        stack.push(buffer.pop());
      }
    }
  }

  /*
   * sort and return buffer stack
   * time: O(n^2); space: O(n)
   */
  static Stack<Integer> sort2(Stack<Integer> stack) {
    Stack<Integer> buffer = new Stack<Integer>();
    while (!stack.isEmpty()) {
      Integer val = stack.pop();
      while (!buffer.isEmpty() && buffer.peek() > val) { // check if empty before peek()
        stack.push(buffer.pop());
      }
      buffer.push(val);
    }
    return buffer;
  }


  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<Integer>();
    for (int i = 10; i > 0 ; i--) {
      stack.push(i);
    }
    sort1(stack);
    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
    }

    stack.clear();
    for (int i = 10; i > 0 ; i--) {
      stack.push(i);
    }
    stack = sort2(stack);
    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
    }
  }
}