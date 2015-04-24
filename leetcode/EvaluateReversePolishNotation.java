/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

// Stack
public class Solution {
  public int evalRPN(String[] tokens) {
    String op = "+-*/"; // use indexOf check substring
    Stack<String> stack = new Stack<String>();
    for(String str : tokens){
      if(op.indexOf(str) == -1){
        stack.push(str);
      } else{
        String rightval=  stack.pop();
        String leftval = stack.pop();
        stack.push(compute(leftval, rightval, str));
      }
    }
    return Integer.parseInt(stack.pop());
  }
  String compute(String left, String right, String op){
    int l = Integer.parseInt(left);
    int r = Integer.parseInt(right);
    int res=0;
    switch(op){
      case "+": res = l+r;
        break;
      case "-": res=l-r;
        break;
      case "*": res=l*r;
        break;
      case "/": res=l/r;
        break;
    }
    return ""+res;
  }
}
