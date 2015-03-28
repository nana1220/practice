/*
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted
arrays.The overall run time complexity should be O(log (m+n)).
 */


/*
 * This is a special case of find k-th smallest element of two sorted array.
 * if (m + n) is odd median is ((m + n) / 2 + 1)-th element,
 * if (m + n) is even need to find ((m + n) / 2)-th element and ((m + n) / 2 + 1)-th
 * write a recursive function that find k-th smallest element
 * binary search maitain pointer to first and last element
 */
public class MedianOfTwoSortedArrays {

  int findKthSmallest(int k, int[] A, int[] B, int aFirst, int aLast, int bFirst, int bLast) {
    if (aFirst > aLast) return B[bFirst + k - 1];
    if (bFirst > bLast) return A[aFirst + k - 1];
    int aMid = (aFirst + aLast) / 2;
    int bMid = (bFirst + bLast) / 2;
    if (A[aMid] > B[bMid]) {
      // NOTE: don't use mid only, need to substract aFisrt, bFisrt, since first and last change at each recursion
      // + 2 makes both aMid and bMid belong to left part
      if ((aMid - aFirst + bMid - bFirst + 2) > k) { // target cannot be at right part of aMid, aMid belongs to right part, bMid belongs to left part
        // discard right part of A including aMid
        return findKthSmallest(k, A, B, aFirst, aMid - 1, bFirst, bLast);
      } else { // target cannot be at left part of B
        // discard left part of B including bMid
        // NOTE: don't forget substract bFirst
        return findKthSmallest(k - (bMid - bFirst + 1), A, B, aFirst, aLast, bMid + 1, bLast);
      }
    } else {
      if ((aMid - aFirst + bMid - bFirst + 2) > k) { //target cannot be at right part of B
        return findKthSmallest(k, A, B, aFirst, aLast, bFirst, bMid - 1);
      } else { // target cannot be at left part of A
        return findKthSmallest(k - (aMid - aFirst + 1), A, B, aMid + 1, aLast, bFirst, bLast);
      }
    }
  }

  double findMedian(int[] A, int[] B) {
    if ((A.length + B.length) % 2 != 0) { // odd
      return (double) findKthSmallest((A.length + B.length) / 2 + 1, A, B, 0, A.length - 1, 0, B.length - 1);
    } else {
      double midLeft = (double) findKthSmallest((A.length + B.length) / 2, A, B, 0, A.length - 1, 0, B.length - 1);
      double midRight = (double) findKthSmallest((A.length + B.length) / 2 + 1, A, B, 0, A.length - 1, 0, B.length - 1);
      return (midLeft + midRight) / 2;
    }
  }

}