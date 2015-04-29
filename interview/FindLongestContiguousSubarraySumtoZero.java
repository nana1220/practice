/*
Find longest continous subarray that sum up to zero.
For example:
{1, 2 -3, 1, 5, -5, 6} should return {2, -3, 1, 5, -5}


利用sum up to zero的特性，从头开始加，如果发现cursum出现过，代表有连续subarray加和等于零。
 */

class Solu{
  vector<int> longest_subarray(const vector<int>& arr) {
    int sum = 0, length = 0, begin = -1;
    unordered_map<int, int> record = {{0,-1}};
    for(int i = 0; i < arr.size(); ++i) {
      sum += arr[i];
      if(record.count(sum)) {
        auto first = record[sum];
        if(i - first > length) {
          length = i - first;
          begin = first + 1; // the begining index of subarray is the next index of previous sum
        }
      } else {
        record[sum] = i;
      }
    }
    if(begin < 0) return {};
    else return vector<int>(arr.begin()+begin, arr.begin()+begin+length);
  }
}