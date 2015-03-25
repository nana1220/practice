/*
Oh, no! You have just completed a lengthy document when you have an unfortu-
nate Find/Replace mishap. You have accidentally removed all spaces, punctuation,
and capitalization in the document. A sentence like "I reset the computer. It still
didn't boot!" would become "iresetthecomputeritstilldidntboot". You figure that you
can add back in the punctation and capitalization later, once you get the individual
words properly separated. Most of the words will be in a dictionary, but some strings,
like proper names, will not.
Given a dictionary (a list of words), design an algorithm to find the optimal way of
"unconcatenating" a sequence of words. In this case, "optimal" is defined to be the
parsing which minimizes the number of unrecognized sequences of characters.
For example, the string "jesslookedjustliketimherbrother" would be optimally parsed
as "JESS looked just like TIM her brother". This parsing has seven unrecognized char-
acters, which we have capitalized for clarity.
 */

package ch17moderate;

import java.lang.System;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class BestMinBreakWords {

  static HashSet<String> dict;
  static HashMap<String, Result> cache = new HashMap<String, Result>();

  static class Result {
    String words;
    int invalidCount;

    Result(String w, int i) {
      words = w;
      invalidCount = i;
    }

    // Use clone() for cache
    public Result clone() {
      return new Result(this.words, this.invalidCount);
    }
  }

  /*
   * Note!!: recursion expression: f(n) = 1 + f(n-1) + 2 + f(n-2) + ... + n + f(0)
   * It's really slow if not using DP
   *
   * Put clone in cache, and also need to get a clone when quering cache, so cache will not
   * be modified.
   * Be very careful in dynamic programming problems of how you cache objects. If the
   * value you are caching is an object and not a primitive data type, it is very likely that you
   * need to clone the object. If we don't clone it, Future calls will unintentionally
   * modify the values in the cache.
   */
  static Result findBest(String str) {
    // Note: store copy in cache for DP!!!!, and return copy from cache
    if (cache.containsKey(str)) return cache.get(str).clone();
    // Note: this base case
    // when split the whole string into its left part as a word, and right part is an empty string
    if (str.length() == 0) {
      return new Result("", 0);
    }
    Result bestRes = new Result("", Integer.MAX_VALUE);
    for (int i = 1; i <= str.length(); i++) {
      String word = str.substring(0, i);
      Result recurRes = findBest(str.substring(i, str.length())); // empty string when i = str.length()
      if (dict.contains(word)) {
        recurRes.words = word + " " + recurRes.words;
      } else {
        recurRes.words = word.toUpperCase() + " " + recurRes.words;
        recurRes.invalidCount = recurRes.invalidCount + word.length(); // Note: character count, so word length
      }
      if (bestRes.invalidCount > recurRes.invalidCount) bestRes = recurRes;
      if (bestRes.invalidCount == recurRes.invalidCount) {
        if(bestRes.words.length() > recurRes.words.length()) bestRes = recurRes;
      }
    }
    // Note!!! clone a new object to hold the cache result
    cache.put(str, bestRes.clone());
    // return the original result, so that cache will not be modified
    return bestRes;
  }

  public static void main(String[] args) {
    String str = "jesslookedjustliketimherbrother";
    String[] valid = new String[] { "looked", "just", "like", "her", "brother" };
    dict = new HashSet<String>(Arrays.asList(valid));
    System.out.println(findBest(str).words);
    System.out.println(cache.size());
  }
}
