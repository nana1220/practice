/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 */

// O(kn)
public class Solution {
  public void rotate(int[] nums, int k) {
    k = k % nums.length; // Note
    while (k > 0) {
      rotateOne(nums);
      k--;
    }

  }

  void rotateOne(int[] nums) {
    int tmp = nums[nums.length - 1];
    System.arraycopy(nums, 0, nums, 1, nums.length - 1);
    nums[0] = tmp;
  }


  /*
      1. Divide the array two parts: 1,2,3,4 and 5, 6
      2. reverse first part: 4,3,2,1,5,6
      3. reverse second part: 4,3,2,1,6,5
      4. reverse the whole array: 5,6,1,2,3,4
   */
  public static void rotate(int[] arr, int order) {
    order = order % arr.length;

    if (arr == null || order < 0) {
      throw new IllegalArgumentException("Illegal argument!");
    }

    //length of first part
    int a = arr.length - order;

    reverse(arr, 0, a - 1);
    reverse(arr, a, arr.length - 1);
    reverse(arr, 0, arr.length - 1);

  }

  public static void reverse(int[] arr, int left, int right) {
    if (arr == null || arr.length == 1)
      return;

    while (left < right) {
      int temp = arr[left];
      arr[left] = arr[right];
      arr[right] = temp;
      left++;
      right--;
    }
  }
}