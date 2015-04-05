/*
Wiggle Sort
Given an unordered array A[]={1,2,3,7,3,2,5}, let it be A[0]<=A[1]>=A[2]<=A[3].., e.g.[]={1,7,3,5,2,2,1}
 */

public class SmallBigSmallBigArray {

  /*
   * solu 1: A very fast solution is to find the minimum from the array and the maximum and place
   * them on the first two positions, and then restart from index 2.
   *
   * solu 2:
   * use quick select to find median
   * first find median value, then traverse array maintain tow pointers, odd and even,
   * if value <= median, put it in index[odd], else put it in index[median]
   */
  int[] wigleSort(int[] arr) {

  }

  int findMedian(int[] arr) {
    int mid = arr.length / 2;

  }

  /*
   * best solution O(n)
   */
  void shuffle(int A[], int n) {
    int increase = 1;
    for (int i = 1; i < n; i++) {
      int local = A[i] >= A[i - 1] ? 1 : 0;
      if (increase != local) swap(A[i], A[i - 1]);
      increase ^= 1;
    }
  }

  public int[] arrangeZigZag(int[] arr) {
    int length = arr.length;
    int temp;
    for (int i = 0; i < length - 1; i++) {
      if ((i & 1) > 0) {
        if (arr[i] < arr[i + 1]) {
          temp = arr[i];
          arr[i] = arr[i + 1];
          arr[i + 1] = temp;

        }
      } else {
        if (arr[i] > arr[i + 1]) {
          temp = arr[i];
          arr[i] = arr[i + 1];
          arr[i + 1] = temp;
        }
      }
    }
    return arr;
  }
}