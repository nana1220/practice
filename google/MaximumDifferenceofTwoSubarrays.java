/*
Given an array of integers. Find two disjoint contiguous subarrays such that the absolute difference between the sum of two subarray is maximum.
Note: The subarrays should not overlap.

For example:
Array: { 2, -1, -2, 1, -4, 2, 8 }
Result subarrays: {-1, -2, 1, -4 }, { 2, 8 }
Maximum difference = 16

 */

/*
这题实际上就是Maximum Subarray的一个应用。
可以用四个辅助数组分别存每个index左侧的最大连续和，左侧的做小连续和，右侧的最大连续和，右侧的最小连续和，O(n)搞定。
然后对每个index计算abs(right_max[i+1] - left_min[i])和abs(left_max[i-1] - right_min[i])，找出最大值即可。
整体时间O(n)，空间O(n)
 */

class SOlu{
  int max_dif_subarr(const vector<int>& arr) {
    int len = (int)arr.size();
    if(len < 2) return 0;

    vector<int> left_max(len), right_max(len), left_min(len), right_min(len);

    int sum_max = arr.front(), sum_min = arr.front();
    left_min[0] = left_max[0] = arr[0];

    for(int i = 1; i < len; ++i) {
      if(sum_min > 0) sum_min = 0;
      sum_min += arr[i];
      sum_max += arr[i];

      left_max[i] = max(sum_max, left_max[i-1]);// leftMax[i]=Math.max(leftMax[i-1]+arr[i], arr[i])
      left_min[i] = min(sum_min, left_min[i-1]);

      if(sum_max < 0) sum_max = 0;
    }

    sum_max = arr.back(), sum_min = arr.back();
    right_min.back() = right_max.back() = arr.back();

    for(int i = len - 2; i >= 0; --i) {
      if(sum_min > 0) sum_min = 0;

      sum_min += arr[i];
      sum_max += arr[i];

      right_max[i] = max(sum_max, right_max[i+1]);
      right_min[i] = min(sum_min, right_min[i+1]);

      if(sum_max < 0) sum_max = 0;
    }

    int ret = INT_MIN;
    for(int i = 1; i < len; ++i)
      ret = max(ret, abs(left_max[i-1] - right_min[i]));
    for(int i = len - 2; i >= 0; --i)
      ret = max(ret, abs(right_max[i+1] - left_min[i]));
    return ret;
  }
}
