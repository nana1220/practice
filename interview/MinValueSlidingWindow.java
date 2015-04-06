/*
A long array A[] is given to you. There is a sliding window of size w which is moving from
the very left of the array to the very right. You can only see the w numbers in the window.
Each time the sliding window moves rightwards by one position. Following is an example:
The array is [1 3 -1 -3 5 3 6 7], and w is 3.

Input: A long array A[], and a window width w
Output: An array B[], B[i] is the maximum value of from A[i] to A[i+w-1]
 */

/*
 * heap O(nlog(k))
 */

/*solution2
http://codercareer.blogspot.com/2012/02/no-33-maximums-in-sliding-windows.html
The double-ended queue is the perfect data structure for this problem. It supports insertion/deletion
from the front and back. The trick is to find a way such that the largest element in the window
would always appear in the front of the queue.
A natural way most people would think is to try to maintain the queue size the same as the window’s
size. Try to break away from this thought and try to think outside of the box. Removing redundant
elements and storing only elements that need to be considered in the queue is the key to achieve
the efficient O(n) solution below.
 algorithm could be proven to have run time complexity of O(n). This is because each element in the
 list is being inserted and then removed at most once. Therefore, the total number of insert + delete
 operations is 2n.

use deque (doubly linked list), push max value to front, push smaller value to back
if new value bigger than front, clear deque, only keep the new max at front
 Rather than storing numbers in the queue directly, we can store indices instead. If the distance
 between the index at the front of queue and the index of the current number to be pushed is
 greater than or equal to the window size, the number corresponding to be the index at the font
 of queue is out of sliding window.
 */
public class MaxValueSlidingWindow {
  void maxSlidingWindow(int A[], int n, int k, int B[]) {
    deque<int> Q;
    for (int i = 0; i < k; i++) { // fill window
      while (!Q.empty() && A[i] >= A[Q.back()])
        Q.pop_back();
      Q.push_back(i);
    }
    for (int i = k; i < n; i++) { // move window
      B[i - k] = A[Q.front()]; // peek(), take max value
      while (!Q.empty() && A[i] >= A[Q.back()])
        Q.pop_back();  // pollLast() until last is bigger than current value
      while (!Q.empty() && Q.front() <= i - k)
        Q.pop_front(); // poll() if front's index shows that front should be removed from window,
      Q.push_back(i); // add() current value to the end of queue
    }
    B[n - k] = A[Q.front()];
  }
}