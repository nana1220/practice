/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
 */

public class Solution {
  public String addBinary(String a, String b) {
    char[] A = a.toCharArray();
    char[] B = b.toCharArray();
    int[] C = new int[Math.max(A.length, B.length) +1];
    int i = A.length-1;
    int j = B.length-1;
    int k= C.length-1;
    while(i>=0 && j>=0){
      C[k--] = (A[i--] -'0') + (B[j--]-'0');

    }
    while (i>=0) {
      C[k--] = A[i--]-'0';
    }
    while(j>=0){
      C[k--]=B[j--] -'0';
    }
    int carry=0;
    for(k=C.length-1; k>=0; k--) {
      int val =C[k] + carry;
      carry = val/2;
      C[k] = val %2;
    }
    StringBuilder res= new StringBuilder();
    boolean headZero =true;
    for(k=0; k< C.length; k++){
      while(headZero && k<C.length && C[k] ==0) k++;
      headZero=false;
      if (k==C.length){ // NOTE: edge case!! all zeros
        res.append(0);
      } else{
        res.append(C[k]);
      }
    }
    return res.toString();
  }
}