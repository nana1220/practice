/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
 */

// has bugs
public class Solution {
  public String multiply(String num1, String num2) {
    char[] n1 = num1.toCharArray();
    char[] n2 = num2.toCharArray();
    int[] res = new int[n1.length+n2.length];
    for (int j = n1.length -1; j >=0; j--){
      int carry =0;
      int[] curr = new int[n1.length+n2.length];
      for(int i = n2.length-1; i>=0; i--){
        int num = (n1[j] -'0') * (n2[i]-'0') + carry;
        carry = num / 10;
        num = num &10;
        curr[i + j+1] = num;
      }
      curr[j] = carry;
      carry =0;
      for(int k = n2.length + n1.length -1; k>=0; k--){
        int num = curr[k] + res[k] +carry;
        num = num % 10;
        carry = num /10;
        res[k] = num;
      }
    }
    if (res[0] == 0) res = Arrays.copyOfRange(res, 1, res.length);
    StringBuilder sb = new StringBuilder();
    for (int v : res) sb.append(v);
    return sb.toString();
  }
}

// store multiply of two digit into one position first, then considier multiplication and carry
public class Solution {
  public String multiply(String num1, String num2) {
    num1 = new StringBuilder(num1).reverse().toString(); // reverse
    num2 = new StringBuilder(num2).reverse().toString();
    int[] res = new int[num1.length()+num2.length()];
    for(int i=0; i< num1.length(); i++) {
      for (int j=0; j< num2.length(); j++){
        res[i+j] += (num1.charAt(i) -'0') * (num2.charAt(j) -'0'); // put multiplication in one spot
      }
    }
    int carry=0;
    int num=0;
    for(int k =0; k<res.length;k++){ // handle multiplication and carry
      num = (res[k]+ carry) %10;
      carry = (res[k] + carry) /10;
      res[k] = num;
    }
    StringBuilder solu = new StringBuilder();
    for(int k=res.length-1; k >=0; k--){
      if(res[k] ==0 && solu.length()==0) continue; // remove heading zeros
      solu.append(res[k]);
    }
    if (solu.length()==0) solu.append("0"); // if result is zero
    return solu.toString();
  }
}