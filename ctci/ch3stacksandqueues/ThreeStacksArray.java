package ch3stacksandqueues;

import java.lang.Exception;

/*
 * The spaces of stacks can be either fixed or dynamic allocated in a circular indexed array.
 */
public class ThreeStacksArray {

  int stackSize = 100;
  int[] data = new int[stackSize * 3];
  int[] stackPoints = {-1, -1, -1}; // pointers to top element

  void push(int stackNum, int data) throws Exception { // stack number 0, 1, 2
    if (stackPoints[stackNum] == 99) {
      throw new Exception("stack is full");
    }
    stackPoints[stackNum]++;
    this.data[stackNum * stackSize + stackPoints[stackNum]] = data;
  }

  int pop(int stackNum) throws Exception {
    if (stackPoints[stackNum] == -1) {
      throw new Exception("stack is empty");
    }
    int point = stackPoints[stackNum]--;
    return data[stackNum * stackSize + point];
  }

  int peak(int stackNum) throws Exception {
    if (stackPoints[stackNum] == -1) {
      throw new Exception("stack is empty");
    }
    int point = stackPoints[stackNum];
    return data[stackNum * stackSize + point];
  }
}

