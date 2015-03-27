/*
 * Given a string S and an array of smaller strings T, design a method to search S for each small
 * string in T
 */

package ch18hard;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * For a string with length n, there are n(n+1)/2 substrings. suffix tree can be built in O(n) time
 */
public class SearchSuffixTree {

  static class SuffixTreeNode {
    char value;
    HashMap<Character, SuffixTreeNode> children = new HashMap<Character, SuffixTreeNode>();
    ArrayList<Integer> indexes = new ArrayList<Integer>();

    public SuffixTreeNode() {
    }

    public void insertString(String s, int index) {
      indexes.add(index);
      if (s != null && s.length() > 0) {
        value = s.charAt(0);
        SuffixTreeNode child = null;
        if (children.containsKey(value)) {
          child = children.get(value);
        } else {
          child = new SuffixTreeNode();
          children.put(value, child);
        }
        String remainder = s.substring(1);
        child.insertString(remainder, index);
      }
    }

    /*
     * O(n)
     */
    public ArrayList<Integer> search(String s) {
      if (s == null || s.length() == 0) {
        // until search reach the last char of this string, return the last node's index
        // which is also the first node's index, because they are on the same suffix
        return indexes;
      } else {
        char first = s.charAt(0);
        if (children.containsKey(first)) {
          String remainder = s.substring(1);
          return children.get(first).search(remainder);
        }
      }
      return null;
    }
  }

  static class SuffixTree {
    SuffixTreeNode root = new SuffixTreeNode();

    /*
     * this build implementation is O(n^2)
     */
    public SuffixTree(String s) {
      for (int i = 0; i < s.length(); i++) {
        String suffix = s.substring(i);
        // insert all suffixes into root, each index corresponds a char of this string
        // root has all indexes, other node each has one char and if have multiple indexes,
        // which means the string has duplicated of that char, the index also means
        // the start position of this suffix and all the following node with same index complelte
        // this suffix
        root.insertString(suffix, i);
      }
    }

    public ArrayList<Integer> search(String s) {
      return root.search(s);
    }
  }

  public static void main(String[] args) {
//    String testString = "mississippi";
    String testString = "bibs";
//    String[] stringList = {"is", "sip", "hi", "sis"};
    String[] stringList = {"b", "bi", "s", "bsb"};
    SuffixTree tree = new SuffixTree(testString);
    for (String s : stringList) {
      ArrayList<Integer> list = tree.search(s);
      if (list != null) {
        System.out.println(s + ": " + list.toString());
      }
    }
  }
}