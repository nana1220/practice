package ch3stacksandqueues;

import java.lang.System;
import java.util.Stack;

public class HanoiTower {
  public static void main(String[] args) {
    Stack<Disk> diskStack = new Stack<Disk>();
    for (int i = 10; i > 0; i--) {
      diskStack.push(new Disk(i));
    }
    Tower orig = new Tower(diskStack);
    Tower dest = new Tower();
    Tower buffer = new Tower();
    moveDisks(10, orig, dest, buffer);
    dest.printDisks();
  }

  /*
   * recursive: move n from orig to dest
   * move n - 1 from orig to buffer, move 1 from orig to dest, move n - 1 from buffer to dest
   */
  static void moveDisks(int numDisks, Tower orig, Tower dest, Tower buffer) {
    if (numDisks == 1) {
      orig.moveDisk(dest);
      return; // Don't forget this return!!
    }
    moveDisks(numDisks - 1, orig, buffer, dest);
    moveDisks(1, orig, dest, buffer);
    moveDisks(numDisks - 1, buffer, dest, orig);
  }

  static class Disk {
    int val;

    Disk(int val) {
      this.val = val;
    }
  }

  static class Tower {
    Stack<Disk> diskStack;

    Tower() {
      diskStack = new Stack<Disk>();
    }

    Tower(Stack<Disk> stack) {
      diskStack = stack;
    }

    boolean moveDisk(Tower dest) {
      if (this.diskStack.isEmpty()) {
        System.out.println("Tower is empty");
        return false;
      }
      if (dest.diskStack.isEmpty() || this.diskStack.peek().val < dest.diskStack.peek().val) {
        dest.diskStack.push(this.diskStack.pop());
        return true;
      }
      System.out.println("Unable to move disk");
      return false;
    }

    void printDisks() {
      while (!diskStack.isEmpty()) {
        System.out.print(diskStack.pop().val + " ");
        System.out.println();
      }
    }
  }
}



