/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
 */

public class Solution {
  public String simplifyPath(String path) {
    String[] pathArr = path.split("/");
    Stack<String> stack = new Stack<String>();
    for(String s : pathArr){
      if(s.equals("..")) {
        // NOTE!!, don't put this condition with s.equals(".."), otherwise if isEmpty(), ".." will be pushed on stack in the else if statement
        if (!stack.isEmpty())
          stack.pop();
      }
      // if meet "...", push to stack as well
      else if(!s.equals(".") && s.length() >0) // in case "//home", there will be empty string
        stack.push(s);
    }
    ArrayList<String> p = new ArrayList<String>();
    while(!stack.isEmpty()) p.add(stack.pop());
    StringBuilder res = new StringBuilder();
    res.append("/");
    for(int i= p.size()-1; i >=1; i--){
      res.append(p.get(i));
      res.append("/");
    }
    if(p.size()!=0) // last path no ending "/", check size first!!
      res.append(p.get(0));
    return res.toString();
  }
}