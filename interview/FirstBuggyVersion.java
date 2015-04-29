/*
某版本库从某一个版本开始引入了一个bug, 请找出最早引入该bug的版本。
现提供has_bug(int version)函数来判断版本号n是否引入了这个bug，要求尽可能少调用此函数。
版本号范围为[1, n]
 */

// binary search
class Solu {
  int first_buggy_version(int n) {
    int l = 1, r = n;
    while (l <= r) {
      int m = l + (r - l) / 2;
      if (has_bug(m)) r = m - 1;
      else l = m + 1;
    }
    return l;
  }
}