/*
给了a, b, c三个vector<string>，要求求出数组的Combination，每一个vector中选一个，为空则不选，不用考虑duplicate。
 */

/*
本来是极其简单的，直接三重循环搞定就好，但是如果有个数组是空的那就不行了。
用递归的方法写了下，能比iterative的清晰很多。考虑空数组的话，三重循环做也可以但是会比较难看。
而且下面的方法也容易扩展成k个数组的情况。
 */

class Solu{
  using array = vector<int>;
  vector<array> arr_comb(const array& a, const array& b, const array& c) {
    vector<array> ret;
    array sln;
    const array* arrs[] = {&a,&b,&c};

    function<void(int)> solve = [&](int arr_id) {
      if(arr_id == 3) {
        ret.push_back(sln);
        return;
      } else if(arrs[arr_id]->empty()) {
        solve(arr_id+1);
      } else {
        for(int i = 0; i < arrs[arr_id]->size(); ++i) {
          sln.push_back(arrs[arr_id]->at(i));
          solve(arr_id+1);
          sln.pop_back();
        }
      }
    };
    solve(0);
    return ret;
  }
}