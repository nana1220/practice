package ch3stacksandqueues;

import java.lang.Integer;
import java.lang.Override;
import java.util.ArrayList;
import java.util.Stack;

class SetOfStacks extends Stack<Integer>{
  ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>(1);
  int maxSize = 100;
  int stackIdx = 0;
  int stackHeight = 0;

  @Override
  public Integer push(Integer val) {
    if (stackHeight == maxSize) {
      stacks.add(new Stack<Integer>());
      stackIdx++;
      stackHeight = 0;
    }
    stackHeight++;
    return stacks.get(stackIdx).push(val);
  }

  @Override
  public Integer pop() {
    Integer val = stacks.get(stackIdx).pop();
    if (--stackHeight == 0) {
      stacks.remove(stackIdx--);
      stackHeight = maxSize;
    }
    return val;
  }
}