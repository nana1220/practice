/*
Given an array A[], write a function that segregates even and odd numbers. The functions should put all even numbers first, and then odd numbers.

Example
Input = {12, 34, 45, 9, 8, 90, 3}
Output = {12, 34, 8, 90, 45, 9, 3}
 */

// 就是2-way partitioning，sort colors的变种题
class Solu{

  void segregates_even_odd(vector<int>& arr) {
    int l = 0, r = (int)arr.size() - 1;
    while(l < r) {
      while(arr[l] % 2 == 0 && l < r) ++l;
      while(arr[r] % 2 != 0 && l < r) --r;
      if(l < r) swap(arr[l++], arr[r--]);
    }
  }
}