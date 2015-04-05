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
   * Two maps, found map and target map
   */
  public List<Integer> findSubstring(String S, String[] L) {
    List<Integer> res = new ArrayList<Integer>();
    Map<String, Integer> found = new HashMap<String, Integer>();
    Map<String, Integer> target = new HashMap<String, Integer>();
    for (String w : L) {
      found.put(w, 0);
      Integer freq = target.get(w);
      if (freq == null) target.put(w, 1);
      else target.put(w, freq + 1);
    }
    int wlen = L[0].length();
    int num = L.length;
    if (S.length() < wlen * num) return res;

    // NOTE!!!!!: i from 0 to wlen - 1, then j start from i to end with step wlen
    for (int i = 0; i < wlen; i++) {
      int count = 0;
      int start = i;
      for (int j = i; j <= S.length() - wlen; j += wlen) {
        String word = S.substring(j, j + wlen);
        if (!target.containsKey(word)) {
          for (String w : found.keySet()) found.put(w, 0);
          count = 0;
          start = j + wlen;
          continue;
        } else {
          if (found.get(word) < target.get(word)) {
            found.put(word, found.get(word) + 1);
            count++;
            // there are enough current words in the window, move start until skip the word same as current word
            // the second solution doesn't consider this case, use i from start to end cover this case
          } else {
            while (!S.substring(start, start + wlen).equals(word)) {
              String startword = S.substring(start, start + wlen);
              found.put(startword, found.get(startword) - 1);
              count--;
            }
            start += wlen;
          }
        }
        if (count == num) {
          res.add(start);
          String startword = S.substring(start, start + wlen);
          found.put(startword, found.get(startword) - 1); // remove start, keep find another valid string
          start = j + wlen;
          count--;
        }
      }
    }
    return res;
  }
}

// PASS
public class Solution {
  public static ArrayList<Integer> findSubstring(String S, String[] L) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    if (S==null || S.length()==0 || L==null || L.length==0) return res;
    int n = L[0].length(), m=S.length();
    if (m < L.length * n)  return res;
    Map<String, Integer> dict = new HashMap<String, Integer>();
    for (String w : L){
      if (dict.containsKey(w))    dict.put(w, dict.get(w)+1);
      else dict.put(w, 1);
    }
    for (int i=0; i <= m-n*L.length; i++){          // i<= m - n*L.length, important here; if i<m, TLE
      Map<String, Integer> tmp = new HashMap<String, Integer>(dict);
      int j=i;
      while (j+n <= m){
        String w = S.substring(j, j+n);
        // if a word is too many, also break, don't consider move head, too complicated
        // instead i covers all range!!!!!!!!!!!!!!
        if (!tmp.containsKey(w)) break;
        tmp.put(w, tmp.get(w)-1);
        if (tmp.get(w) == 0)    tmp.remove(w);
        if (tmp.isEmpty()){
          res.add(i); break;
        }
        j += n;
      }
    }
    return res;
  }
}