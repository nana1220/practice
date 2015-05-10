/*
Given two words (beginWord and endWord), and a dictionary, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
 */
import java.lang.String;
import java.lang.System;
import java.util.*;
import java.util.HashSet;

public class WordLadder {
  // BFS
  public int ladderLength(String start, String end, Set<String> dict) {
    Set<String> visited = new HashSet<String>();
    visited.add(start);
    LinkedList<String> queue = new LinkedList<String>();
    queue.add(start);
    Map<String, String> backtrack = new HashMap<String, String>();
    while(!queue.isEmpty()){
      String word = queue.poll();
      for (String w : getNeighbours(word, dict)){
        if (w.equals(end)) {
          int len =2; // word and end
          while(!word.equals(start)){
            len++;
            word = backtrack.get(word);
          }
          return len;
        } else{
          if (!visited.contains(w)){
            visited.add(w);
            backtrack.put(w, word);
            queue.add(w);
          }
        }
      }
    }
    return 0;
  }
  // BFS level order traversal, count++ when enter next level
  // when usa a word in dict, remove it from dict, same effect as mark visited
  public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
      int count = 2;
      ArrayList<String> prev = new ArrayList<String>();
      prev.add(start);
      while (!prev.isEmpty()){
        ArrayList<String> curr = new ArrayList<String>();
        for (String w : prev){
          for (int i=0; i<w.length(); i++){
            StringBuilder sb = new StringBuilder(w);
            for (char j='a'; j<='z'; j++){
              sb.setCharAt(i, j);
              String adj = sb.toString();
              if (end.equals(adj))
                return count;
              if (dict.contains(adj)){
                curr.add(adj);
                dict.remove(adj);
              }
            }
          }
        }
        count++;
        prev = curr;
      }
      return 0;
    }
  }


  // DFS: wrong answer, because once a node in the long path is marked visited
  // a shorter path containing this node can not be counted
  static public int ladderLength(String start, String end, Set<String> dict) {
    Set<String> visited = new HashSet<String>();
    visited.add(start);
    return ladderLength(start, end, dict, visited, 1);
  }
  static public int ladderLength(String start, String end, Set<String> dict, Set<String> visited, int len) {
    int minLen = Integer.MAX_VALUE;
    if (start.equals(end)) return len;
    for (String w : getNeighbours(start, dict)) {
      if (w.equals(end)) return len +1; // NOTE: need to check here, so end will not be added to the visited
      if (!visited.contains(w)) {
        visited.add(w);
        int length = ladderLength(w, end, dict, visited, len + 1);
        minLen = Math.min(length, minLen);
      }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
  }

  static Set<String> getNeighbours(String word, Set<String> dict){
    Set<String> neighbours = new HashSet<String>();
    for(int i =0; i<word.length(); i++){
      for(int j=0; j<26; j++){
        char c = (char) ('a' + j);
        if (c != word.charAt(i)) {
          String w =  word.substring(0,i) + c + word.substring(i+1);
          if(dict.contains(w)) neighbours.add(w);
        }
      }
    }
    return neighbours;
  }

}