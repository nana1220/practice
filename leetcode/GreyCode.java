/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */

/*
000
001
011
010
110
111
101
100
n 时的Gray Code，相当于n-1时的Gray Code的逆序 加上 1<<(n-1)
 */
public class Solution {
  public List<Integer> grayCode(int n) {
    List<Integer> rst = new ArrayList<Integer>();
    if(n==0){
      rst.add(0);
      return rst;
    }
    rst.add(0);
    rst.add(1);
    for(int i=1; i<n;i++){
      int size = rst.size(); //NOte!!: use size to get start point
      int one = 1<<i;
      for(int j=size-1; j>=0; j--){
        rst.add(rst.get(j)+one);
      }
    }
    return rst;
  }
}