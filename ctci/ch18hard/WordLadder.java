/*
Given two words of equal length that are in a dictionary, write a method to trans-
form one word into another word by changing only one letter at a time. The new
word you get in each step must be in the dictionary.
 */

package ch18hard;

import java.lang.System;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;

/*
 * Breadth first search. Each word in our "graph" branches to all words in the dictionary
 * that are one edit awayy, Should we build a graph as we go?
 * We could, but there's an easier way. We can instead use a "backtrack map",
 * if B[v] = w, then you know that you edited v to get w. When we reach our
 * end word, we can use this backtrack map repeatedly to reverse our path
 */

public class WordLadder {
  static HashSet<String> dict;

  static HashSet<String> oneEditedWords(String word) {
    HashSet<String> words = new HashSet<String>();
    for (int i = 0; i < word.length(); i++) {
      char[] chars = word.toCharArray();
      for (char c = 'a'; c <= 'z'; c++) { // iterate characters
        if (c != word.charAt(i)) {
          chars[i] = c;
          words.add(new String(chars));
        }
      }
    }
    return words;
  }

  static LinkedList<String> wordLadder(String startWord, String stopWord) {
    if (startWord.isEmpty() || stopWord.isEmpty() || startWord == null || stopWord == null)
      return null;
    startWord = startWord.toLowerCase();
    stopWord = stopWord.toLowerCase();
    HashSet<String> visited = new HashSet<String>();
    LinkedList<String> queue = new LinkedList<String>();
    HashMap<String, String> backtrack = new HashMap<String, String>(); // this backtrack map function like a stack
    visited.add(startWord);
    queue.add(startWord);
    while (!queue.isEmpty()) {
      String word = queue.poll();
      for (String w : oneEditedWords(word)) {
        if (w.equals(stopWord)) {
          LinkedList<String> wordladder = new LinkedList<String>();
          wordladder.add(w);
          String prevWord = word;
          while (true) {
            wordladder.addFirst(prevWord);
            if (prevWord.equals(startWord)) return wordladder;
            prevWord = backtrack.get(prevWord);
          }
        } else if (dict.contains(w)) {
          if (!visited.contains(w)) { // NOTE!!: don't forget to check visited first
            visited.add(w);
            queue.add(w);
            backtrack.put(w, word); // store transform path in back track map
          }
        }
      }
    }
    return null; // no ladder from one to another
  }

  public static HashSet<String> setupDictionary(String[] words) {
    HashSet<String> hash = new HashSet<String>();
    for (String word : words) {
      hash.add(word.toLowerCase());
    }
    return hash;
  }

  public static void main(String[] args) {
    String[] words = {"maps", "tan", "tree", "apple", "cans", "help", "aped", "free", "apes", "flat", "trap", "fret", "trip", "trie", "frat", "fril"};
    dict = setupDictionary(words);
    LinkedList<String> list = wordLadder("tree", "flat");
    for (String word : list) {
      System.out.println(word);
    }
  }

}



