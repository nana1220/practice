/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 */


/*
Suppose there are N elements and they range from A to B.
Then the maximum gap will be no smaller than ceiling[(B - A) / (N - 1)]
Let the length of a bucket to be len = ceiling[(B - A) / (N - 1)], then we will have at most num = (B - A) / len + 1 of bucket
for any number K in the array, we can easily find out which bucket it belongs by calculating loc = (K - A) / len and therefore
maintain the maximum and minimum elements in each bucket.
Since the maximum difference between elements in the same buckets will be at most len - 1, so the final answer will not be
taken from two elements in the same buckets.
For each non-empty buckets p, find the next non-empty buckets q, then q.min - p.max could be the potential answer to
the question. Return the maximum of all those values.
 */
public class Solution {
  public int maximumGap(int[] nums) {
    if(nums.length<2) return 0;
    int max = nums[0];
    int min = nums[0];
    for (int n : nums){
      max = Math.max(max, n);
      min = Math.min(min, n);
    }
    // int bucketSize = 1+ (max-min) / (nums.length -1);
    // NOTE!!!!!, use ceil is critical!!!!!!!!!!!!,
    // otherwise siez of [1,100000] is 100000 not 9999, only has one bucket not two, the max gap appears inside the bucket, not allowed
    int bucketSize = (int)Math.ceil((double)(max - min)/(nums.length - 1));
    int bucketNum = (max-min) / bucketSize +1;
    int[][] buckets = new int[bucketNum][2];
    for(int[] arr : buckets) {Arrays.fill(arr,-1);}
    for(int val : nums){
      int idx =(val-min) / bucketSize;
      buckets[idx][0] = buckets[idx][0] == -1 ? val : Math.min(buckets[idx][0], val);
      buckets[idx][1] = buckets[idx][1] == -1 ? val : Math.max(buckets[idx][1], val);
    }
    int maxGap=0;
    //int maxGap= buckets[0][1]-buckets[0][0]; this is wrong, each bucket only keeps min and max value in it, max-min is not the gap since the values between min and max are skipped
    // store prev value instead of prev index
    int prev = buckets[0][1]; // the max value in first bucket must not be empty since if only one value in first bucket, max is same as min which is global min
    for(int i=1; i<bucketNum; i++){
      if(buckets[i][0]==-1) continue; // -1 means empty bucket, since min=max if there is only one value in bucket, so either min and max both -1 or both not -1
      maxGap = Math.max(maxGap, buckets[i][0]-prev);
      prev = buckets[i][1];
    }
    return maxGap;
  }
}

public class Solution {
  public int maximumGap(int[] nums) {
    if(nums.length<2) return 0;
    int max = nums[0];
    int min = nums[0];
    for (int n : nums){
      max = Math.max(max, n);
      min = Math.min(min, n);
    }
    int bucketSize = (int)Math.ceil((double)(max - min)/(nums.length - 1));
    int bucketNum = (max-min) / bucketSize +1;
    int[][] buckets = new int[bucketNum][2];
    for(int[] arr : buckets) {Arrays.fill(arr,-1);}
    for(int val : nums){
      // NOTE!!!!!!!: skip min and max values
      if(val==min || val==max) continue;
      int idx =(val-min) / bucketSize;
      buckets[idx][0] = buckets[idx][0] == -1 ? val : Math.min(buckets[idx][0], val);
      buckets[idx][1] = buckets[idx][1] == -1 ? val : Math.max(buckets[idx][1], val);
    }
    int maxGap= 0;
    int prev =min; // NOTE!!!!!!!!!!!!!: use min as first value
    for(int i=0; i<bucketNum-1; i++){
      if(buckets[i][0]==-1) continue; // -1 means empty bucket, since min=max if there is only one value in bucket, so either min and max both -1 or both not -1
      maxGap = Math.max(maxGap, buckets[i][0]-prev);
      prev = buckets[i][1];
    }
    maxGap = Math.max(maxGap, max-prev); // NOTE!!!!!!!!!!!!!!, check last against max value, which can handle the case of only one bucket
    return maxGap;
  }
}
