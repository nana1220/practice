/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
 */

public class Solution {
  public int findMin(int[] nums) {
    int l=0;
    int r=nums.length-1;
    int min = Integer.MAX_VALUE;
    while(l<=r){
      int mid =(l+r)/2;
      // NOTE!!!!: must <= not just <, because (l+r)/2 makes l=mid when length of array is two,
      // in which case nums[l]=nums[mid], we should compare min with nums[l] and l=mid+1 to make l=r and check with last element
      if(nums[l] <= nums[mid]){
        min = Math.min(min, nums[l]);
        l =mid+1;
      } else{
        min= Math.min(min, nums[mid]);
        r=mid-1;
      }
    }
    return min;
  }
}