/*
 * implement a sliding window api
 */

import java.lang.Double;
import java.util.LinkedList;

class SlidingWindow {
  // doubly linked list
  LinkedList<Double> data;
  int windowSize;
  Iterator<Double> stream;
  double runningSum = 0;

  // return average value for numbers in the window
  double next() {
    if (stream.hasNext()) {
      if (data.size() >= windowSize) {

      }
    }
  }

  boolean hasNext() {
    return stream.hasNext();
  }

}