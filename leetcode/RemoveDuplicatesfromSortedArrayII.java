/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
 */


// Maintain a counter to track the occurrence of the value
// time: O(n); space: (1)
public class RemoveDuplicatesfromSortedArrayII {
  public int removeDuplicates(int[] A) {
    if (A==null || A.length<=0)
      return 0;
    int i = 1, j = 1, N = A.length, count = 1;
    while (j < N){
      if (A[j]!=A[j-1]){
        A[i++] = A[j++];
        count =1;
      }else{
        if (count < 2)
          A[i++] = A[j++];
        else  // count>=2, only move forward j
          j++;
        count++;
      }
    }
    return i;
  }
}
