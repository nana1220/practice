/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
 */

//  define two local variables, one tracks the maximum and the other tracks the minimum.
public class Solution {
  public int maxProduct(int[] A) {
    if(A==null || A.length==0)
      return 0;

    int maxLocal = A[0];
    int minLocal = A[0];
    int global = A[0];

    for(int i=1; i<A.length; i++){
      int temp = maxLocal;
      maxLocal = Math.max(Math.max(A[i]*maxLocal, A[i]), A[i]*minLocal); // always compute the max(max*curr, curr, min*curr)
      minLocal = Math.min(Math.min(A[i]*temp, A[i]), A[i]*minLocal); // min(max*curr, curr, min*curr)
      global = Math.max(global, maxLocal);
    }
    return global;
  }

}