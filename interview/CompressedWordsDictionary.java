/*
apple -> a3e，banana -> b4a, however accessbility and automobility both -> a11y
given a dictionary and a word, see if the compressed word has already existed in dictionary
 */


// build a hash table for dictionary
class Solution {
  HashMap<String, String> buildTable(String[] dict) {
    HashMap<String, String> map = new HashMap<String, String>();
    for (String word : dict) {
      String key = word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
      map.put(key, word);
    }
  }
}