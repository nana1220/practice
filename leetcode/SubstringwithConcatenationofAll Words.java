/*
You are given a string, S, and a list of words, L, that are all of the same length. Find all
starting indices of substring(s) in S that is a concatenation of each word in L exactly once and
without any intervening characters.

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
 */


public class SubstringwithConcatenationofAllWords {
  /*
   * TODO
   * use a map store words and their frequencies from L,
   * traverse S by the same step (Note: words are of same length), find a match substract its
   * frequency by 1, or remove it if frequency = 0, when the map is empty return index of first word
   */
  public List<Integer> findSubstring(String S, String[] L) {

  }


}