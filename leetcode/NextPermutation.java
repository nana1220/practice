/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */

public class NextPermutation {
  // brute force 
  // traverse from the end to start, find the first value, that is not in ascending order
  // traverse from end to this number find the first value that is bigger than it,
  // swap these two values.
  // And NOTE: need to sort subarray in ascending order, to make it as small as possible
  public void nextPermutation1(int[] num) {
    int len = num.length;
    int bar = num[len - 1];
    for (int i = len - 1; i > 0; i--) {
      for (int j = i -1; j > -1; j--) {
        if (num[j] >= bar) bar = num[j];
        else {
          for (int k = len - 1; k > j; k--) {
            if (num[k] > num[j]) {
              int temp = num[k];
              num[k] = num[j];
              num[j] = temp;
              Arrays.sort(num, j+1, len); // NOTE: Don't forget to sort subarray
              return;
            }
          }
        }
      }
    }
    Arrays.sort(num); // reach here means array is in descending order, don't have next bigger value
    return;
  }

  // optimized: O(nlog(n))
  // traverse from the end to start, find the first value, that is not in ascending order
  // sort the subarray starting from the element after this value, then swap this element
  // with first one in the subarray which is strictly bigger than it
  public void nextPermutation2(int[] num) {
    int len = num.length;
    int bar = num[len - 1];
    for (int i = len - 1; i > 0; i--) {
      if (num[i - 1] >= num[i]) bar = num[i - 1];
      else {
        Arrays.sort(num, i, len);
        int j = i;
        while (num[j] <= num[i - 1]) {
          j++;
        }
        int temp = num[j];
        num[j] = num[i - 1];
        num[i - 1] = temp;
        return;
      }
    }
    Arrays.sort(num);
    return;
  }
}