/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
 */

// dfs: TLE
public class Solution {
  public String getPermutation(int n, int k) {
    dfs(n,k,new StringBuilder(), new boolean[10]);
    return res;
  }
  String res;
  int count =0;
  void dfs(int n, int k, StringBuilder sb, boolean[] marker){
    if(sb.length()==n){
      if (++count == k)
        res = sb.toString();
      return;
    }
    for(int i=1; i <10; i++){
      if(!marker[i]) {
        sb.append(i);
        marker[i]=true;
        dfs(n, k, sb, marker);
        if(count==k) break;
        sb.deleteCharAt(sb.length()-1);
        marker[i]=false;
      }
    }

  }
}

/*
 This is actually a n! base conversion
对于n个数可以有n!种排列；那么n-1个数就有(n-1)!种排列。

那么对于n位数来说，如果除去最高位不看，后面的n-1位就有 (n-1)!种排列。

所以，还是对于n位数来说，每一个不同的最高位数，后面可以拼接(n-1)!种排列。

所以你就可以看成是按照每组(n-1)!个这样分组。

利用 k/(n-1)! 可以取得最高位在数列中的index。这样第k个排列的最高位就能从数列中的index位取得，此时还要把这个数从数列中删除。

然后，新的k就可以有k%(n-1)!获得。循环n次即可。

 同时，为了可以跟数组坐标对其，令k先--。
 */
public class Solution {
  public String getPermutation(int n, int k) {
    int base =1;
    ArrayList<Integer> nums = new ArrayList<Integer>();
    for(int i=1; i<=n; i++){
      nums.add(i);
      base *= i;

    }
    StringBuilder res = new StringBuilder();
    k--; // 0-th corresponds to "123", that is, the 1st
    while(n>0){
      base /= n;
      int idx = k /base; // 0 corresponds to smallest num
      res.append(nums.get(idx));
      nums.remove(idx);
      k %= base;
      n--;
    }
    return res.toString();
  }
}