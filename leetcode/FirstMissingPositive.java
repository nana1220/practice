/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
 */

/*
need O(n): so linear scan and swap
每次当A[i]!= i的时候，将A[i]与A[A[i]]交换，直到无法交换位置。终止条件是 A[i]== A[A[i]]。 然后i -> 0 到n走一遍就好了。
不能按照A[i] = i来存，因为题目要求寻找正数，如果A[0]用来存储0的话，会影响数据处理。比如当A = {1}的时候，如果A[0] = 0的话，根本没地方存1的值了。所以正确的存储方式应该是A[i]= i+1.
 */

public class Solution {
  public int firstMissingPositive(List<Integer> nums) {
    int N = nums.size();
    if (nums.size()==0) return 1;
    for (int i=0; i<nums.size(); i++){
      // keep swapping the value in A[i] to its legal place, until the value in A[i] is legal, either it's i+1 or it's out of range [1, N]
      // NOTE!!! the 4th condition in while loop, in the case of duplicated values, the value in target position might be already valid
      // in this case no swap, otherwise infinite loop
      while(nums.get(i) != i+1 && nums.get(i) > 0 && nums.get(i) <=N && nums.get(nums.get(i) -1) != nums.get(i) ){
        swap(nums, i, nums.get(i)-1);
      }
    }
    for(int i=0;i<N; i++){
      if(nums.get(i) != i+1) return i+1;
    }
    return N+1;
  }
  void swap(List<Integer> nums, int i, int j){
    int tmp = nums.get(i);
    nums.set(i, nums.get(j));
    nums.set(j,tmp);
  }
}

