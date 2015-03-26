/*
Design an algorithm to find the kth number such that the only prime factors are 3,
5, and 7.
 */

package ch7mathematicsandprobability;

import java.lang.Integer;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthMagicNumber {
  /*
   * time: O(k^2)
   */
  static int findKthNumber1(int k) {
    int[] number = new int[k];
    number[0] = 1;
    int index = 0;
    while (true) {
      int min = Integer.MAX_VALUE;
      for (int i = 0; i < index + 1; i++) {
        if (number[i] * 3 > number[index] && number[i] * 3 < min) {
          min = number[i] * 3;
        }
        if (number[i] * 5 > number[index] && number[i] * 5 < min) {
          min = number[i] * 5;
        }
        if (number[i] * 7 > number[index] && number[i] * 7 < min) {
          min = number[i] * 7;
        }
      }
      number[++index] = min;
      if (index == k - 1) {
        break;
      }
    }
    return number[k - 1];
  }

  static class Cache {
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

    void save(int i) {
      minHeap.add(i * 3);
      minHeap.add(i * 5);
      minHeap.add(i * 7);
    }

    int getMin() {
      int res = minHeap.poll();
      // Note:!!!! must remove dulipcated min values e.g. 3 * 5 and 5 * 3
      while (minHeap.contains(res)) {
        minHeap.poll();
      }
      return res;
    }
  }

  /*
   * each time generate a number ,put number * 3, *5, *7 into cache,
   * get the min from cache as next number
   * that is , generate 3k numbers first, and then sort and get the first k
   * time: O(3klgk + 3klg3k) space O(3k)
   */
  static int findKthNumber2(int k) {
    Cache cache = new Cache();
    cache.save(1);
    int min = -1;
    for (int i = 0; i < k - 1; i++) {
      min = cache.getMin();
      cache.save(min);
    }
    return min;
  }

 /*
  * Maintain 3 queues, when we get A[k], we push A[k]*3, A[k]*5, A[k]*7 into specific queues,
  * depending on the value of A[k].
  * eg, if A[k] comes from queue5, we only push A[k]*5 and A[k]*7. We skip A[k]*3 because the value
  * should have been pushed into queue3 before
  * time: O(k); space: O(3k)
  */
  static int findKthNumber3(int k) {
    if (k < 0)
      return 0;
    int val = 0;
    Queue<Integer> qu3 = new LinkedList<Integer>();
    Queue<Integer> qu5 = new LinkedList<Integer>();
    Queue<Integer> qu7 = new LinkedList<Integer>();
    qu3.add(1);
    for (int i = 0; i < k; i++) {
      int v3 = qu3.isEmpty() ? Integer.MAX_VALUE : qu3.peek();
      int v5 = qu5.isEmpty() ? Integer.MAX_VALUE : qu5.peek();
      int v7 = qu7.isEmpty() ? Integer.MAX_VALUE : qu7.peek();
      val = Math.min(Math.min(v3, v5), v7);
      if (val == v3) {
        qu3.poll();
        qu3.add(val * 3);
        qu5.add(val * 5);
        qu7.add(val * 7);
      } else if (val == v5) {
        qu5.poll();
        qu5.add(val * 5);
        qu7.add(val * 7);
      } else {
        qu7.poll();
        qu7.add(val * 7);
      }
    }
    return val;
  }

  public static void main(String[] args) {
    System.out.println(findKthNumber1(10));
    System.out.println(findKthNumber2(10));
    System.out.println(findKthNumber3(10));
  }
}