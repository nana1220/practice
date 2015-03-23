package ch11sortingandsearching;

import java.lang.Integer;
import java.lang.System;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

/*
 * A circus is designing a tower routine consisting of people standing atop of one
 * another's shoulder. Each person should be both lighter and shorter than the person below him
 * or her. Given the heights and weights of each person, write a method to compute the largest possible
 * number of people in such a tower
 */

public class LongestIncreasingSubsequence {

  /*
   * recursive solution.
   * find longest subsequence that current end can be appended
   * each call returns longest subsequence complete with specified end
   * add end element to prior longest subsequence and return
   * time: O(n^2)
   */
  public static ArrayList<Integer> getSubsequence(ArrayList<Integer> sequence, int end) {
    if (end == -1) return new ArrayList<Integer>();
    int prev = end - 1;
    ArrayList<Integer> longestSeq = new ArrayList<Integer>();
    while (prev >= 0) {
      if (sequence.get(prev) < sequence.get(end)) { // find subsequence that that can be appended
        ArrayList<Integer> seq = getSubsequence(sequence, prev); // recurse to get previous longest subsequence
        if (seq.size() > longestSeq.size()) { // find the longest subsequence
          longestSeq = seq;
        }
      }
      prev--;
    }
    longestSeq.add(sequence.get(end)); // add to the longest subsequence
    return longestSeq; // Note: don't return .add()!!!!
  }

  /*
   * compare longest subsequences at each postition and return the longest one
   */
  public static ArrayList<Integer> findLongestSubsequence(ArrayList<Integer> sequence) {
    ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>(sequence.size());
    for (int i = 0; i < sequence.size(); i++) {
      results.add(getSubsequence(sequence, i)); // get longest subsequence for each position
    }
    ArrayList<Integer> longest = results.get(0);
    for (int i = 1; i < sequence.size(); i++) {
      if (results.get(i).size() > longest.size()) {
        longest = results.get(i);
      }
    }
    return longest;
  }

  /*
   * a tail recursion, so this is a iterative solution, store result of every recursion call into cache
   * when iterate to next element, get the previous results from cache
   */
  static void longestIncreasingSubsequence(ArrayList<Integer> array, int current_index,
                                                         ArrayList<ArrayList<Integer>> cache) {
    if (current_index >= array.size() || current_index < 0) {
      return;
    }
    int current_element = array.get(current_index);
    // Find longest sequence that we can append current_element to
    ArrayList<Integer> best_sequence = new ArrayList<Integer>();
    for (int i = 0; i < current_index; i++) {
      if (array.get(i) < current_element) { // If current_element is bigger than list tail
        if (cache.get(i).size() > best_sequence.size())
          best_sequence = cache.get(i); // Set best_sequence to our new max
      }
    }
    // Append current_element
    ArrayList<Integer> new_solution = new ArrayList<Integer>();
    new_solution.addAll(best_sequence);
    new_solution.add(current_element);

    // Add to list and recurse
    cache.add(current_index, new_solution);
    longestIncreasingSubsequence(array, current_index + 1, cache);
  }

  static ArrayList<Integer> longestIncreasingSubsequence(ArrayList<Integer> array) {
    ArrayList<ArrayList<Integer>> cache = new ArrayList<ArrayList<Integer>>(array.size());
    longestIncreasingSubsequence(array, 0, cache);
    ArrayList<Integer> best_sequence = cache.get(0);
    for (int i = 1; i < array.size(); i++) {
      if (best_sequence.size() < cache.get(i).size())
        best_sequence = cache.get(i);
    }
    return best_sequence;
  }


  public static ArrayList<Person> sortWeight(ArrayList<Person> sequence, int end) {
    if (end == -1) return new ArrayList<Person>();
    int prev = end - 1;
    while (prev >= 0 && sequence.get(prev).weight >= sequence.get(end).weight) prev--;
    // Wrong: Must to compare longest subsequence end with all previous positions
    // and append to the longgest one, here only find the first subsequence that can be appended
    ArrayList<Person> res = sortWeight(sequence, prev); // Note: don't return .add()!!!!
    res.add(sequence.get(end));
    return res;
  }

  /*
   * can also use Comparable: class Person implements Comparable { public int compareTo(object person){} }
   * then Collections.sort can automatically sort Person
   */
  static class HeightComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      if (p1.height == p2.height) return 0;
      else if (p1.height < p2.height) return -1;
      else return 1;
    }
  }

  public static ArrayList<Person> findMaxPersons(ArrayList<Person> persons) {
    Collections.sort(persons, new HeightComparator()); // sort by height
    return sortWeight(persons, persons.size() - 1); // find longest subsequence by weight
  }

  static class Person {
    int height;
    int weight;

    public Person(int h, int w) {
      height = h;
      weight = w;
    }
  }

  public static void main(String[] args) {
    ArrayList<Integer> integers = new ArrayList<Integer>();
    integers.add(4);
    integers.add(5);
    integers.add(6);
    integers.add(7);
    integers.add(2);
    integers.add(3);
    integers.add(4);
    ArrayList<Integer> integers1 = findLongestSubsequence(integers);
    System.out.println(integers1.toString());
    ArrayList<Integer> integers2 = longestIncreasingSubsequence(integers);
    System.out.println(integers2.toString());

    ArrayList<Person> persons = new ArrayList<Person>();
    Person p1 = new Person(3, 3);
    Person p2 = new Person(4, 4);
    Person p3 = new Person(5, 5);
    Person p4 = new Person(1, 1);
    Person p5 = new Person(2, 6);
    persons.add(p1);
    persons.add(p2);
    persons.add(p3);
    persons.add(p4);
    persons.add(p5);
    ArrayList<Person> res = findMaxPersons(persons);
    for (Person p : res) {
      System.out.println("h:" + p.height + " w:" + p.weight);
    }
  }
}