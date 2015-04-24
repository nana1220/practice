/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */

/*
(1) 如果当前栈为空，则说明加上当前右括号没有合法序列（有也是之前判断过的）；
(2) 否则弹出栈顶元素，如果弹出后栈为空，则说明当前括号匹配，我们会维护一个合法开始的起点start，合法序列的长度即为当前元素的位置-start+1；否则如
    果栈内仍有元素，则当前合法序列的长度为当前栈顶元素的位置下一位到当前元素的距离，因为栈顶元素后面的括号对肯定是合法的，而且左括号出过栈了.
这种用剩余栈的栈顶元素位置信息作为当前合法数据的判断依据是比较重要的技巧，在Largest Rectangle in Histogram这道题里面也用到了.
因为只需要一遍扫描，算法的时间复杂度是O(n)，空间复杂度是栈的空间，最坏情况是都是左括号，所以是O(n)。
 */
public class Solution {
  public int longestValidParentheses(String s) {
    int maxlen = 0;
    int start = 0;
    Stack<Integer> idxStack = new Stack<Integer>();
    char[] sarr =  s.toCharArray();
    for (int i = 0; i < sarr.length; i++) {
      if (sarr[i] == '(') {
        idxStack.push(i);
      }
      if (sarr[i] == ')')  {
        if (!idxStack.isEmpty()) {
          idxStack.pop();
          if (idxStack.isEmpty()) maxlen = Math.max(maxlen, i - start + 1);
          else maxlen = Math.max(maxlen, i - idxStack.peek()); // idx.peek() + 1 must be a '(', it might have been mathced by a ')' and poped out
        } else {
          start = i + 1;
        }
      }
    }
    return maxlen;
  }
}