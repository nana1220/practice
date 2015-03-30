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
   * Time Limit Exceeded: TODO
   * use a map store words and their frequencies from L,
   * traverse S by the same step (Note: words are of same length), find a match substract its
   * frequency by 1, or remove it if frequency = 0, when the map is empty return index of first word
   * L can have duplicated words
   */
  public List<Integer> findSubstring(String S, String[] L) {
    List<Integer> res = new ArrayList<Integer>();
    Map<String, Integer> wordCount = new HashMap<String, Integer>();
    for (String w : L) {
      Integer freq = wordCount.get(w);
      if (freq == null) wordCount.put(w, 1);
      else wordCount.put(w, freq + 1);
    }
    int wlen = L[0].length();
    int num = L.length;
    if (S.length() < wlen * num) return res;
    for (int i = 0; i <= S.length() - wlen * num; ) {
      Map<String, Integer> copymap = new HashMap<String, Integer>(wordCount); // NOTE: make a copy
      for (int j = 0; j < wlen* num; j = j + wlen) {
        String word = S.substring(i + j, i +j + wlen);
        if (copymap.containsKey(word)) {
          if ((copymap.get(word) == 1)) {
            copymap.remove(word);
            if (copymap.isEmpty()) {
              res.add(i);
              break;
            }
          } else wordCount.put(word, copymap.get(word)-1);
        } else {
          i = i + 1; // NOTE: when doesn't find a match, step for i is 1 !!!!!!
          break;
        }
      }
    }
    return res;
  }
}