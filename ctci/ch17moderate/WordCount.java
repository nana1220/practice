/*
 Design a method to find the frequency of occurrences of any given word in a book.
 */

package ch17moderate;

import java.util.HashMap;

public class WordCount {
  HashMap<String, Integer> table;

  void wordCount(String[] book) {
    for (String word : book) {
      word = word.toLowerCase(); // Note: ask if case in-sensitive or not
      if (table.containsKey(word)) {
        int count = table.get(word) + 1;
        table.put(word, count);
      } else table.put(word, 1);
    }
  }

  int getCount(String word) {
    return table.get(word);
  }
}