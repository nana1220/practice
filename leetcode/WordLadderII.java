/*
    Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
    Only one letter can be changed at a time
    Each intermediate word must exist in the dictionary
    For example,
    Given:
    start = "hit"
    end = "cog"
    dict = ["hot","dot","dog","lot","log"]
    Return
      [
        ["hit","hot","dot","dog","cog"],
        ["hit","hot","lot","log","cog"]
      ]
    Note:
    All words have the same length.
    All words contain only lowercase alphabetic characters.
*/

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

// TLE, but algorithm is right
public class Solution {
  public ArrayList<ArrayList<String>> findLadders(String start, String end, Set<String> dict) {
    ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    Set<String> visited = new HashSet<String>();
    visited.add(start);
    // use set as queue, because same level can visit same word multiple times, but only need to add it to the queue one time
    HashSet<String> currentLevel = new HashSet<String>();
    currentLevel.add(start);
    // use set for path backtrack because one word can have multiple prev words on its transformation path
    HashMap<String, HashSet<String>> backtrack = new HashMap<String, HashSet<String>>();
    // can also check if queue of next level contains end to denote end has been found
    boolean hasFound = false;
    while(!currentLevel.isEmpty() && !hasFound){
      HashSet<String> nextLevel = new HashSet<String>();
      for(String w : currentLevel){
        // NOTE!!!!!!!!!!!: add to set only after done processing the whole level, to allow same word being visited multiple times when processing this level
        // add word of processed level to visited, different levels don't have same word, because deeper level has same word must have longer path
        // same level can have same word multiple times, this word can be transformed from different words of prev level
        visited.add(w);
        Set<String> neighbours = getNeighbours(w, dict);
        for(String n : neighbours){
          if(!visited.contains(n)){ // next level don't visit words that have been visited on previous level
            if(n.equals(end)) {
              hasFound = true;
            }
            if (!backtrack.containsKey(n)){
                HashSet<String> set = new HashSet<String>();
                backtrack.put(n, set);
            }
            backtrack.get(n).add(w);
            // note!!! DONT add word to visited here, different words at current level can transform to same word at next level
            nextLevel.add(n);
          }
        }
      }
      currentLevel = nextLevel;
    }
    if(hasFound) {
      buildPaths(new ArrayList<String>(), res, backtrack, start, end);
    }
    return res;
  }
  // DFS build paths
  void buildPaths(ArrayList<String> path, ArrayList<ArrayList<String>> paths, HashMap<String, HashSet<String>> backtrack, String start, String end){
    if(end.equals(start)){
      path.add(end);
      ArrayList<String> forwardPath = new ArrayList<String>(path);
      Collections.reverse(forwardPath);
      paths.add(forwardPath);
      path.remove(path.size()-1);
      return;
    }
    path.add(end);
    for(String prev : backtrack.get(end)){
      buildPaths(path, paths, backtrack, start, prev);
    }
    path.remove(path.size()-1);
  }

  Set<String> getNeighbours(String word, Set<String> dict){
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


// similar to print the shortest path between two nodes on a graph
// BFS + map(store previous ndoes)
public class Solution {
  public static ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
    Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    ArrayList<Set<String>> layers = new ArrayList<Set<String>>();
    layers.add(new HashSet<String>());
    layers.add(new HashSet<String>());
    int curr = 0, next = 1;
    dict.add(start);
    ArrayList<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
    layers.get(curr).add(end);// start from end, so that no need to reverse the path when
    // buildPaths
    while (true) {
      for (String w : layers.get(curr))
        dict.remove(w);
      Iterator<String> it = layers.get(curr).iterator();
      while (it.hasNext()) {
        String word = it.next();
        for (int i = 0; i < word.length(); i++) {
          for (char j = 'a'; j <= 'z'; j++) {
            StringBuilder sb = new StringBuilder(word);
            sb.setCharAt(i, j);
            if (sb.equals(word))
              continue;
            String adj = sb.toString();
            if (dict.contains(adj)) {
              layers.get(next).add(adj);
              if (!map.containsKey(adj))
                map.put(adj, new HashSet<String>());
              map.get(adj).add(word);
            }
          }
        }
      }
      if (layers.get(next).isEmpty() || layers.get(next).contains(start))
        break;
      int tmp = curr;
      curr = next;
      next = tmp;
      layers.get(next).clear();
    }
    if (!map.isEmpty())
      buildPaths(start, end, map, paths, new ArrayList<String>());
    return paths;
  }

  public static void buildPaths(String curr, String end, Map<String, Set<String>> map, ArrayList<ArrayList<String>> paths, ArrayList<String> path) {
    path.add(curr);
    if (curr.equals(end)) {
      paths.add(new ArrayList<String>(path));
    }else{
      for (String w : map.get(curr))
        buildPaths(w, end, map, paths, path);
    }
    path.remove(path.size() - 1);
  }
}


public class Solution {
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if (start == null || end == null) return res;
        ArrayList<String> tmparray = new ArrayList<String>();

        //如果start与end相等，直接返回
        if (start.equals(end)) {
            tmparray.add(start);
            tmparray.add(end);
            res.add(tmparray);
            return res;
        }

        //新建一个hashmap，保存每个节点的所有前驱。
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        map.put(end, new ArrayList<String>());
        map.put(start, new ArrayList<String>());
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }

        //利用bfs 层序遍历 如果队列中有end 那么结束遍历（到最短的一层就结束）
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        ArrayList<String> currentlevel = new ArrayList<String>();
        while (!queue.isEmpty()) {
            int level = queue.size();
            currentlevel.clear();
            for (int i = 0; i < level; i++) {
                String top = queue.poll();
                if (dict.contains(top)) dict.remove(top);
                currentlevel.add(top);
            }

            //循环每个String的每个char 从a到z，在dict里面找是否有
            for (String curs : currentlevel) {
                for (int i = 0; i < curs.length(); ++i) {
                    for (char j = 'a'; j <= 'z'; ++j) {
                        char[] tmpchar = curs.toCharArray();
                        tmpchar[i] = j;
                        String tmps = new String(tmpchar);
                        if (!curs.equals(start) && tmps.equals(end)) {
                            map.get(end).add(curs);
                            queue.offer(tmps);
                        }
                        else if (!tmps.equals(curs) && dict.contains(tmps)) {
                            if (!queue.contains(tmps)) queue.offer(tmps); // don't add dup to the queue
                            map.get(tmps).add(curs);
                        }
                    }
                }
            }
            if (queue.contains(end))
                break;
        }
        tmparray.add(end);
        buildpath(start, end, map, tmparray, res);
        return res;
    }

    //根据节点的前驱 生成路径
    public void buildpath(String start, String end,
            HashMap<String, ArrayList<String>> map, ArrayList<String> tmparray,
            ArrayList<ArrayList<String>> res) {
        ArrayList<String> pre = map.get(end);
        if (end.equals(start)) {
            ArrayList<String> tmparray2 = new ArrayList<String>(tmparray);
            Collections.reverse(tmparray2);
            res.add(tmparray2);
            return;
        }
        for (String s: pre) {
            tmparray.add(s);
            buildpath(start, s, map, tmparray, res);
            tmparray.remove(tmparray.size() - 1);
        }

    }

}
