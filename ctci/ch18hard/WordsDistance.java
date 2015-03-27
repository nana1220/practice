/*
You have a large text file containing words. Given any two words, find the shortest
distance (in terms of number of words) between them in the file. If the operation
will be repeated many times for the same file (but different pairs of words), can you
optimize your solution?
 */

package ch18hard;

import java.lang.System;
import java.util.Collections;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsDistance {
  /*
   * one pass from the word array, save the last position of word1 or word2,
   * when reach a new word1 or word2 compute distance update last position
   */
  static int distance(String word1, String word2, String[] words) {
    int distance = Integer.MAX_VALUE;
    int lastPos = -1;
    boolean isWord1 = true;
    for (int i = 0; i < words.length; i++) {
      if (words[i].equals(word1)) {
        if (lastPos != -1 && !isWord1) {
          if (Math.abs(lastPos - i) < distance) distance = Math.abs(lastPos - i);
        }
        lastPos = i;
        isWord1 = true;
      }
      if (words[i].equals(word2)) {
        if (lastPos != -1 && isWord1) {
          int dis = Math.abs(lastPos - i);
          if (dis < distance) distance = dis;
        }
        lastPos = i;
        isWord1 = false;
      }
    }
    return distance;
  }

  /*
   * Use hash table
   * save all words with their distance to start of file in a cache
   */
  static HashMap<String, ArrayList<Integer>> cacheDist(String[] words) {
    HashMap<String, ArrayList<Integer>> cache = new HashMap<String, ArrayList<Integer>>();
    for (int i = 0; i < words.length; i++) {
      if (!cache.containsKey(words[i])) {
        ArrayList<Integer> dists = new ArrayList<Integer>();
        dists.add(i);
        cache.put(words[i], dists);
      } else {
        cache.get(words[i]).add(i);
      }
    }
    return cache;
  }

  /*
   * Note!!!!: merge and sort then find shortest distance
   * meage two distance list into one list, tag distance with w1 and w2
   * sort list, go one pass through list, compute the distance of two consecutive
   * words with different tags, find the shortest one
   */
  static int distance(String word1, String word2, HashMap<String, ArrayList<Integer>> cache) {
    ArrayList<Integer> d1 = cache.get(word1);
    ArrayList<Integer> d2 = cache.get(word2);
    ArrayList<WordWithDist> list = new ArrayList<WordWithDist>();
    for (Integer i : d1) list.add(new WordWithDist("w1", i));
    for (Integer i : d2) list.add(new WordWithDist("w2", i));
    Collections.sort(list);
    int distance = Integer.MAX_VALUE;
    for (int i = 1; i < list.size(); i++) {
      if (!list.get(i - 1).word.equals(list.get(i).word)) {
        int dist = Math.abs(list.get(i - 1).dist - list.get(i).dist);
        if (dist < distance) distance = dist;
      }
    }
    return distance;
  }

  static class WordWithDist implements Comparable<WordWithDist> { // generics
    String word;
    int dist;

    WordWithDist(String w, int d) {
      word = w;
      dist = d;
    }

    public int compareTo(WordWithDist thatWord) {
      return (this.dist < thatWord.dist) ? -1 : ((this.dist > thatWord.dist) ? 1 : 0);
    }
  }

  public static void main(String[] args) {
    String[] words = new String[]{"del", "haha", "ding", "wahaha", "delete", "gaga", "lili", "haha"};
    System.out.println(distance("haha", "gaga", words));
    HashMap<String, ArrayList<Integer>> cache = cacheDist(words);
    System.out.println(distance("haha", "gaga", cache));
  }
}