/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */

public class Solution {
  // you need to treat n as an unsigned value
  public int hammingWeight(int n) {
    int count=0;
    // NOTE: don't use condition n>0, since treat n as unsigned, the highest digit can be 1
    //while(n>0){
    for(int i=0; i<32; i++){
      count += n & 1;
      n = n>>1;
    }
    return count;
  }
}

/*
 use x & (x-1) to determine if an integer is a power of two? Well, there are even better uses for that!
 Remember every time you perform the operation x & (x-1), a single 1 bit is erased
 The following solution is machine independent, and is quite efficient. It runs in the order of the number of 1s.
 In the worst case, it needs to iterate 32 times (for a 32-bit integer), but a case such as the number ‘8’ would only need to iterate 1 time.
 */

public class Solution {
  // you need to treat n as an unsigned value
  public int hammingWeight(int n) {
    int total_ones = 0;
    while (n != 0) {
      n = n & (n - 1);
      total_ones++;
    }
    return total_ones;
  }
}