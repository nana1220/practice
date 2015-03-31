/*
Decompose a number into the sum of minimum number of squared numbers.

Example:
input: 14    output:  9 ,4 , 1
input: 50    output :  25, 25
 */


import java.lang.Integer;
import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class SumOfSquaredNumbers {
  static List<Integer> decompose1(int num) {
    Map<Integer, ArrayList<Integer>> cache = new HashMap<Integer, ArrayList<Integer>>();
    return decompose1(num, cache);
  }

  /*
   * Wrong,
   * This is impl is greedy algorithm, each time find its maximum part that has squared root
   * not trying to find the minimun number
   * 74 = 49 + 25 but this impl has 74 = 64 + 9 + 1
   */
  static List<Integer> decompose1(int num, Map<Integer, ArrayList<Integer>> cache) {
    if (cache.containsKey(num)) return new ArrayList<Integer>(cache.get(num));
    ArrayList<Integer> res = new ArrayList<Integer>();
    int squaredRoot = 1;
    while (squaredRoot * squaredRoot <= num) {
      squaredRoot++;
    }
    int square = (squaredRoot - 1) * (squaredRoot - 1);
    res.add(square);
    if (square == num) { // base case
      cache.put(num, res);
      // even in worst case when number comes down to one, this will return,
      // no need an extra base case, e.g. if (num == 0) return new ArrayList<Integer>();;
      return res;
    } else {
      res.addAll(decompose1(num - square, cache)); // dont return directly
      return res; // need a separate statement
    }
  }

  static LinkedList<Integer> decompose2(int num) {
    Map<Integer, LinkedList<Integer>> cache = new HashMap<Integer, LinkedList<Integer>>();
    return decompose2(num, cache);
  }

  /*
   * TODO: has bug
   */
  static LinkedList<Integer> decompose2(int num, Map<Integer, LinkedList<Integer>> cache) {
    if (cache.containsKey(num)) {
      return new LinkedList<Integer>(cache.get(num)); // don't use clone, clone return object
    }
    if (num == 0) {
      return new LinkedList<Integer>();
    }
    int minSize = Integer.MAX_VALUE;
    LinkedList<Integer> result = null;
    int squaredRoot = 1;
    while (squaredRoot * squaredRoot <= num) {
      LinkedList<Integer> res = decompose2(num - squaredRoot * squaredRoot, cache);
      if (res.size() < minSize) {
        minSize = res.size();
        result = res;
      }
      squaredRoot++;
    }
    result.addFirst(squaredRoot * squaredRoot);
    cache.put(num, new LinkedList<Integer>(result)); // don't use clone
    return result;
  }

  /*
   * split and merge
   * can usd an ArrayList as cache, use index represents number,
   * decompose i into i - j and j, compute i - j and j separately, and merge.
   */
  static LinkedList<Integer> decompose3(int num) {
    ArrayList<LinkedList<Integer>> cache = new ArrayList<LinkedList<Integer>>();
    cache.add(new LinkedList<Integer>()); // 0, just fill the blank, useless
    LinkedList<Integer> one = new LinkedList<Integer>();
    one.add(1); // 1 = 1 * 1
    cache.add(one); // 1
    for (int i = 2; i <= num; i++) { // compute decompisition from 2 to num
      LinkedList<Integer> result = new LinkedList<Integer>();
      if (i == (int) Math.sqrt(i) * (int) Math.sqrt(i)) { // this is base case
        result.add(i); // i = sqrt(i) * sqrt(i)
      } else {
        int size = Integer.MAX_VALUE;
        for (int j = 1; j <= i / 2; j++) { // split, this iteration can be treated as one level BFS
          // i = (i - 1) + 1 or (i - 2) + 2 ... (i - j) + j = decompose(i - j) + decompose(j)
          LinkedList<Integer> resLeft = new LinkedList<Integer>(cache.get(j)); // merge
          LinkedList<Integer> resRight = new LinkedList<Integer>(cache.get(i - j));
          resLeft.addAll(resRight); // NOTE: addAll return boolean
          if (resLeft.size() == 2) { // the smallest size is 2, if size is 2, we are done
            result = resLeft;
            break;
          }
          if (resLeft.size() < size) {
            size = resLeft.size();
            result = resLeft;
          }
        }
      }
      cache.add(result);
    }
    return cache.get(num);
  }

  /*
   * still DP, only compute min number
   */
  public int minNumber(int n){
    int [] f = new int[n+1];
    f[0] = 0;
    f[1] = 1;
    for (int i = 2; i <= n ; i++){
      int j = 1;
      int min = Integer.MAX_VALUE;
      while(j*j <= i) {.// decompose i
        int cur = 1 + f[i-j*j]; // NOTE: DP!!!, 1 means j * j, call cache get f[i- j*j]
        min = Math.min(min, cur);
        j++;
      }
      f[i] = min;
    }
    return f[n];
  }

  public static void main(String[] args) {
    System.out.println(decompose1(74).toString());
    System.out.println(decompose2(74).toString());
    System.out.println(decompose3(74).toString());
  }
}