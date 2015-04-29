/*
有序数组中都是正数且为unique number，找出两个数A、B，so that A-B = 一个给定的数C。要求使用常数空间和O(N)时间
 */

class Solu{
  vector<int> two_dif(const vector<int> &A, int target) {
    if(A.size() < 2) return {};

    for(int p1 = 0, p2 = 1; p1 < A.size() && p2 < A.size(); ) {
      if(A[p2] - A[p1] < target) p2++;
      else if(A[p2] - A[p1] > target) p1++;
      else return {A[p1], A[p2]};

      if(p2 == p1) p2 = p1 + 1;
    }
    return {};
  }
}