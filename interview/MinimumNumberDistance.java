/*
Given an unsorted array arr[] and two numbers x and y, find the minimum distance between x and y in arr[]. The array might also contain duplicates. You may assume that both x and y are different and present in arr[].

Examples:
Input: arr[] = {1, 2}, x = 1, y = 2
Output: 1.

Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2
Output: 1.
 */

class Solu{
  int min_num_distance(const vector<int>& arr, int x, int y) {
    int ret = INT_MAX;

    int last_x = -1, last_y = -1;
    for(int i = 0; i < arr.size(); ++i) {
      if(arr[i] == x) {
        if(last_y >= 0) ret = min(ret, i - last_y);
        last_x = i;
      }
      if(arr[i] == y) {
        if(last_x >= 0) ret = min(ret, i - last_x);
        last_y = i;
      }
    }
    return ret;
  }
}