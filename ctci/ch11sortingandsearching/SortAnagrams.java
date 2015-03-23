package ch11sortingandsearching;

/*
 * Write a method to sort an array of strings so that all the anagrams are next to each
 * other.
 */

import java.util.Comparator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class SortAnagrams {
  static class AnagramComparator implements Comparator<String> {
    static String sortString(String str) {
      char[] res = str.toCharArray();
      Arrays.sort(res); // sort char array
      return String.valueOf(res);
    }
    public int compare(String str1, String str2) { // super method is public, so must declare public
      str1 = sortString(str1);
      str2 = sortString(str2);
      return str1.compareToIgnoreCase(str2); // case instensive compareTo
    }
  }

  /*
   * use a modified comparator to compare anagrams as equal, then sort
   * time: O(nlog(n))
   */
  static void sortAnagrams(String[] strings) {
    Arrays.sort(strings, new AnagramComparator()); // Note: need a instance: new Comparator()
  }

  /*
   * group strings by anagram: bucket sort
   * use hash table store sorted string as key and its anagrams as value
   * put those values back to array group by their keys
   * time: O(n)
   */
  static String[] sortAnagrams2(String[] strings) {
    HashMap<String, ArrayList<String>> table = new HashMap<String, ArrayList<String>>();
    for(String str : strings) {
      addToAnagramTable(str, table);
    }
    int idx = 0;
    // Note: iterate key is enought, for (String key : table.keySet()) {table.get(key));}
    for(Map.Entry<String, ArrayList<String>> entry : table.entrySet()) { // Note: iterate through hashmap
      // Note: java pass reference by value!!
      // So below assign new value to element in array will not modify those strings stored in hashmap before
      for(String str : entry.getValue()) strings[idx++] = str;
    }
    return strings;
  }

  // sorted string as key, its anagrams together as value
  static void addToAnagramTable(String str, HashMap<String, ArrayList<String>> table) {
    String sorted = AnagramComparator.sortString(str);
    if (!table.containsKey(sorted)) {
      ArrayList<String> strList= new ArrayList<String>();
      strList.add(str);
      table.put(sorted, strList);
    } else {
      table.get(sorted).add(str);
    }
  }

  public static void main(String[] args) {
    String[] strings = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
    System.out.println(stringArrayToString(strings));

    sortAnagrams(strings);
    System.out.println(stringArrayToString(strings));

    String[] strings2 = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
    System.out.println(stringArrayToString(sortAnagrams2(strings)));
  }

  static String stringArrayToString(String[] array) {
    StringBuilder sb = new StringBuilder();
    for (String v : array) {
      sb.append(v + ", ");
    }
    return sb.toString();
  }
}