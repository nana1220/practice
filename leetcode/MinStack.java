/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Show Tags

 */

// maintain another stack for min value
class MinStack {
  Stack<Integer> stack = new Stack<Integer>();
  Stack<Integer> min = new Stack<Integer>();
  public void push(int x) {
    stack.push(x);
    if(min.isEmpty() || x<=min.peek()) min.push(x);
  }

  public void pop() {
    if(!stack.isEmpty()){
      int val = stack.pop();
      if(val==min.peek()) min.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return min.peek();
  }
}


// or bind each value with its min value
class MinStack {
  Node top = null;

  public void push(int x) {
    if (top == null) {
      top = new Node(x);
      top.min = x;
    } else {
      Node temp = new Node(x);
      temp.next = top;
      top = temp;
      top.min = Math.min(top.next.min, x);
    }
  }

  public void pop() {
    top = top.next;
    return;
  }

  public int top() {
    return top == null ? 0 : top.val;
  }

  public int getMin() {
    return top == null ? 0 : top.min;
  }
}

class Node {
  int val; //value
  int min; // and min value
  Node next;

  public Node(int val) {
    this.val = val;
  }
}
