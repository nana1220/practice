/*
Given a list of words, write a program to find the longest word made of other words
in the list.
 */

package ch18hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Comparator;

public class WordMadeOfOtherWords {
  /*
   * split word into left part and right part, recursively split right part
   * first call pass true so left part cannot be the whole word,
   * recursive call pass false so left part can be the whole word.
   *
   * DP: can use cache for recording if splited part can be made of other words,
   * but Note don't cache the original word, because composed by itself doesn't count
   */
  static boolean isMadeOfByOthers(String word, HashSet<String> dict, boolean isOriginal) {
    // base case: when left part is the whole word and right part is empty
    if (word.length() == 0) return true;
    boolean res = false;
    // Note!!!: original word composed solely of itself doesn't count,
    // so for original word, its left part cannot be itself while leave right part empty
    // but in the recursion, left part is allowed to be the whole word while leave right part empty
    // that is i = word.length() should be only exluded from the first call
    int offset = isOriginal ? 0 : 1;
    for (int i = 1; i < word.length() + offset; i++) {
      String left = word.substring(0, i);
      String right = word.substring(i);
      res = res || (dict.contains(left) && isMadeOfByOthers(right, dict, false));
      if (res) break;
//      if (dict.contains(left) && isMadeOfByOthers(right, dict)) return true;
    }
    return res;
//    return false;
  }

  static String getLongest(String[] words) {
    HashSet<String> dict = new HashSet<String>();
    // put into hash table for easy look up
    for (String word : words) {
      dict.add(word);
    }
    // Note!!: sort words by length, iterate from the longest word until find the first word
    // that is made of other words, no need to go though and check every word to find the longest
    Arrays.sort(words, new Comparator<String>() {
      public int compare(String word1, String word2) { // don't forget public
        return word2.length() - word1.length(); // inverse natrual-ordering, max first
      }
    });
//    int maxSize = Integer.MIN_VALUE;
    for (String word : words) {
      if (isMadeOfByOthers(word, dict, true)) {
//        maxSize = Math.max(word.length(), maxSize);
        return word; // Note: this return in the condition statement, need another return in the end
      }
    }
    return null; // if not find
  }

  public static void main(String[] args) {
    String[] words = new String[]{"del", "ding", "de", "ing", "delding", "erliding", "erli"};
    System.out.println(getLongest(words));
  }
}
