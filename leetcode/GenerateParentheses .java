/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
 */

public class GenerateParentheses {
  // this is a binary tree, tree depth is 2n
  // can treat each node’s left child is add left paren, right child as add right paren
  // use DFS search each path
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<String>();
    generate(n, 0, 0, "", res);
    return res;
  }

  void generate(int n, int lcount, int rcount, String parens, List<String> res) {
    if (lcount == n) {
      StringBuilder sb = new StringBuilder(parens);
      while (rcount < n) { // NOTE: < not <=
        sb.append(")");
        rcount++;
      }
      res.add(sb.toString());
      return;
    }
    generate(n, lcount + 1, rcount, parens + "(", res);
    if (lcount - rcount > 0) { // NOTE: lcount - rcount > 0, not lcount > 0
      generate(n, lcount, rcount + 1, parens + ")", res);
    }
  }
}