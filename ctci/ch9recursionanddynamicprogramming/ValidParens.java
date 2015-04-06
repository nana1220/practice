package ch9recursionanddynamicprogramming;

import java.lang.String;
import java.util.ArrayList;

/*
 * Implement an algorithm to print all valid (i.e., properly opened and closed)
 * combinations of n-pairs of parentheses.
 */
public class ValidParens {

  /*
   * diff keeps track of the difference of number of left parens and right parens
   * if diff < 0 invalid because too many right parens
   * if diff > 2*n - parens.length() invalid bacause there are not enough remaining right parens
   * to complete a n pair valid parens
   */
  static void printParens(String parens, int diff, int n) {
    // pruning case
    if (diff < 0 || diff > (2 * n - parens.length())) {
      return;
    }
    // base case
    if (parens.length() == 2 * n) {
      System.out.println(parens);
      return;
    }
    printParens(parens + "(", diff + 1, n);
    printParens(parens + ")", diff - 1, n);
  }

  /*
   * Note: use array for backtracking, it's memory effecient since the element can be overwritten.
   * use char array to store parens string, so that if one subtree is complete, the new subtree
   * can override the char stored in the array to generate a new parens string
   */
  static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
    if (leftRem < 0 || rightRem < leftRem) return; // invalid state

    if (leftRem == 0 && rightRem == 0) { // no more parens left
      String s = new String(str); // Note: char array to String
      list.add(s);
    } else {
      // go left subtree if valid
      if (leftRem > 0) { // try a left paren, if there are some available, Note: this is equavlent to a pruning check
        str[index] = '(';
        addParen(list, leftRem - 1, rightRem, str, index + 1);
      }
      // backtrack right subtree if valid
      if (rightRem > leftRem) { // try a right paren, if there’s a matching left, Note: this is equavlent to a pruning check
        str[index] = ')';
        addParen(list, leftRem, rightRem - 1, str, index + 1);
      }
    }
  }
  static ArrayList<String> generateParens(int count) {
    char[] str = new char[count*2];
    ArrayList<String> list = new ArrayList<String>();
    addParen(list, count, count, str, 0);
    return list;
  }

  public static void main(String[] args) {
    printParens("", 0, 3);

    System.out.println();
    ArrayList<String> list = generateParens(3);
    for (String s : list) {
      System.out.println(s);
    }
  }
}