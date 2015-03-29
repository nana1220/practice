/*
Given a sorted array, remove the duplicates in place such that each element appear only once and
return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
 */

public class RemoveDuplicatesFromSortedArray {
  public int removeDuplicates(int[] A) {
    int len = A.length;
    // from end to start
    for (int i = A.length - 1; i > 0 ; i--) {
      if (A[i] == A[i - 1]) {
        System.arraycopy(A, i, A, i - 1, len - i);
        len--; // NOTE: copy first then length--
      }
    }
    return len;
  }
}