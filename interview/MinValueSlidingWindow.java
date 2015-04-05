/*
Giving a windows size K and an array of size N, find the maximum of each window as it slides through the array
 */

/*
 * heap O(nlog(k))
 */

/*solution2
http://codercareer.blogspot.com/2012/02/no-33-maximums-in-sliding-windows.html
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
    for (int i = 0; i < k; i++) {
      while (!Q.empty() && A[i] >= A[Q.back()])
        Q.pop_back();
      Q.push_back(i);
    }
    for (int i = k; i < n; i++) {
      B[i - k] = A[Q.front()];
      while (!Q.empty() && A[i] >= A[Q.back()])
        Q.pop_back();
      while (!Q.empty() && Q.front() <= i - k)
        Q.pop_front();
      Q.push_back(i);
    }
    B[n - k] = A[Q.front()];
  }
}