/*
Numbers are randomly generated and passed to a method. Write a program to find
and maintain the median value as new values are generated.
 */

package ch18hard;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RunningMedian {

  static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
  static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
    public int compare(Integer a, Integer b) {
      return b - a;
    }
  });

  /*
   * The heap sizes can be equal, or the maxHeap may have one extra element.
   */
  static double median(int newValue) {
    int median = maxHeap.peek();
    if (newValue <= median) {
      maxHeap.add(newValue);
    } else {
      minHeap.add(newValue);
    }
    if (maxHeap.size() == minHeap.size()) {
      return (maxHeap.peek() + minHeap.peek()) / 2;
    }
    if (maxHeap.size() == minHeap.size() + 1) {
      return maxHeap.peek();
    }
    if (maxHeap.size() > minHeap.size()) {
      minHeap.add(maxHeap.poll());
      return maxHeap.peek();
    }
    maxHeap.add(minHeap.poll());
    return (maxHeap.peek() + minHeap.peek()) / 2;
  }

  static void addNewNumber(int randomNumber) {
    if (maxHeap.size() == minHeap.size()) {
      // Note!!!: check if heap is empty, consider if can add to min heap first
      if ((minHeap.peek() != null) &&
          randomNumber > minHeap.peek()) {
        maxHeap.offer(minHeap.poll());
        minHeap.offer(randomNumber);
      } else {
        maxHeap.offer(randomNumber);
      }
    } else {
      if (randomNumber < maxHeap.peek()) {
        minHeap.offer(maxHeap.poll());
        maxHeap.offer(randomNumber);
      } else {
        minHeap.offer(randomNumber);
      }
    }
  }

  /*
   * Note: return value is double, since median can be (maxHeap.poll() + minHeap.poll()) / 2
   * Check if heap is empty
   */
  static double getMedian() {
    /* maxHeap is always at least as big as minHeap. So if maxHeap
* is empty, then minHeap is also. */
    if (maxHeap.isEmpty()) {
      return 0;
    }
    if (maxHeap.size() == minHeap.size()) {
      return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2; // convert to double
    } else {
      return maxHeap.peek();
    }
  }
}