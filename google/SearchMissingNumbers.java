/*
A sorted array contains integers from 1..n with m of them missing. Find all missing numbers.

Example:
n = 8 , m = 2
arr = [1,2,4,5,6,8]
Result has to be {3, 7}.
 */


// ????
class SOlu{
  vector<int> search_missing_nums(const vector<int>& nums, int misnum) {
    int len = (int)nums.size();
    int n = len + misnum;// total number
    vector<int> ret;

    function<void(int,int)> bs = [&](int l, int r) {
      if(l > r) return;
      int m = l + (r-l)/2;

      if(l == m || m-1-l == nums[m-1]-nums[l]) {// if l to m-1 no missing
        int pre = l == 0 ? 0 : nums[l-1];
        for(int i = 1; i < nums[l] - pre; ++i)// l-1 to l
          ret.push_back(pre+i);
        if(l != m) {
          for(int i = 1; i < nums[m] - nums[m-1]; ++i)// m-1 to m
            ret.push_back(nums[m]-i);
        }
      } else {
        bs(l, m-1);
      }

      if(m == r || r-m-1 == nums[r]-nums[m+1]) {// m+1 to r
        int post = r == len-1 ? n+1 : nums[r+1];
        for(int i = 1; i < nums[m+1] - nums[m]; ++i)
          ret.push_back(nums[m]+i);
        if(m != r) {
          for(int i = 1; i < post - nums[r]; ++i)
            ret.push_back(post-i);
        }
      } else {
        bs(m+1, r);
      }
    };

    bs(0, len-1);
    return ret;
  }
}