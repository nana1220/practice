/*
给定一个数组A[1..n]，求数组B[1..n]，使得B[i] = A[1] * A[2] .. * A[i-1] * A[i+1] .. * A[n]。要求不要使用除法，O(n)时间且不用额外空间。

Examples:
Input: {2, 3, 4, 5}
Output: {60, 40, 30, 24}
 */

// 分左右两次扫一下即可。
class Solu{
  vector<int> get_product_array(const vector<int>& arr) {
    vector<int> ret(arr.size());
    for(int prod = 1, i = 0; i < arr.size(); ++i) {
      prod *= arr[i];
      ret[i] = prod;
    }
    for(int prod = 1, i = (int)arr.size() - 1; i >= 0; --i) {
      ret[i] = (i > 0 ? ret[i-1] : 1) * prod;
      prod *= arr[i];
    }
    return ret;
  }
}