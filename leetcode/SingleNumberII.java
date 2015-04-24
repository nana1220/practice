/*
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

public class Solution {
  // 除了一个数字以外，其他的都出现了3次，如果我们把那个特殊的数剔除，并把剩下的数于每一位来加和的话，每一位上1出现的次数必然都是3的倍数。所以，解法就     // 在这里，将每一位数字分解到32个bit上，统计每一个bit上1出现的次数。最后对于每一个bit上1出现的个数对3取模，剩下的就是结果。
  public int singleNumber(int[] A) {
    int res=0;
    int base = 1;
    for(int i=0; i<32; i++){
      int count=0;
      for(int num : A){
        count += (num >> i) & 1;
      }
      res += count %3 * base;
      base *= 2;
    }
    return res;
  }
}

/*
对于除出现一次之外的所有的整数，其二进制表示中每一位1出现的次数是3的整数倍，将所有这些1清零，剩下的就是最终的数。
用ones记录到当前计算的变量为止，二进制1出现“1次”（mod 3 之后的 1）的数位。用twos记录到当前计算的变量为止，二进制1出现“2次”（mod 3 之后的 2
的数位。当ones和twos中的某一位同时为1时表示二进制1出现3次，此时需要清零。即用二进制模拟三进制计算。最终ones记录的是最终结果。
 */
  int singleNumber(int A[], int n) {
    int ones = 0, twos = 0, xthrees = 0;
    for(int i = 0; i < n; ++i) {
      twos |= (ones & A[i]);
      ones ^= A[i];
      xthrees = ~(ones & twos);
      ones &= xthrees;
      twos &= xthrees;
    }

    return ones;
  }