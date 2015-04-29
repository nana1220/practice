/* AMAZON
Given an array find 3 elements such that a[i] < a[j] < a[k] and i < j < k in 0(n) time.
可以用买卖股票的第三题的思路做。

用两个数组，分别存最大值和最小值的index。
min数组从前往后扫，如果遇到最小值，则把这个index存下来。
max数组从后往前扫，原理同上。
假设数组为：{4,7,5,1,3,8,9,6,2}
扫完后的min和max应该分别为：
{0,0,0,3,3,3,3,3,3}
{6,6,6,6,6,6,6,7,8}
下面就简单了，从前往后扫描原数组，如果符合nums[mins[i]] < nums[i] && nums[i] < nums[maxs[i]]就直接返回即可。
 */
class Solu{
  vector<int> three_nums(const vector<int>& nums) {
    vector<int> ret;
    if(nums.empty()) return ret;

    vector<int> mins(nums.size()), maxs(nums.size());
    mins.front() = 0;
    maxs.back() = (int)nums.size() - 1;

    for(int i = 1; i < nums.size(); ++i) {
      if(nums[i] < nums[mins[i-1]]) mins[i] = i;
      else mins[i] = mins[i-1];
    }
    for(int i = (int)nums.size()-2; i >= 0; --i) {
      if(nums[i] > nums[maxs[i+1]]) maxs[i] = i;
      else maxs[i] = maxs[i+1];
    }
    for(auto i = 0; i < nums.size(); ++i) {
      if(nums[mins[i]] < nums[i] && nums[i] < nums[maxs[i]]) {
        ret = {nums[mins[i]], nums[i], nums[maxs[i]]};
      }
    }
    return ret;
  }

}