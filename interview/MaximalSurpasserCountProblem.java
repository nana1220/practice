/*
输入一个数组，返回数组元素的surpasser个数的最大值。
数组元素a[i] 的surpasser是指元素a[j], j > i， a[j] > a[i]。
比如[10, 3, 7, 1, 23, 14, 6, 9] 这个数组中10的surpasser是23,14，个数是2。而3的surpasser是7,23,14,6,9，个数为5，并且是最多的一个。所以返回5。
 */

/*
利用特殊数据结构的话，本题有很多种做法，比如BST，线段树，点树，树状数组。下面给出一种归并排序思路的解法。实际上跟Inverse Pairs基本是完全一样的。
这里关心的是顺序而不是逆序。
大体思路就是在降序归并排序的过程中每次遇到顺序对就记录下来，比如merge的过程中两个元素分别是3和7，3 < 7，所以3的顺序数+1。最后merge sort完毕后，
每个顺序数的个数我们都知道，返回最大值即可。
思路很简单，唯一tricky的地方就是每次遇到顺序的时候，这个顺序数的个数到底加多少。这个需要注意一下。
 */

class Solu{
  int max_surpasser(vector<int> nums) {
    vector<int> temp(nums.size());
    int ret = 0;
    unordered_map<int, int> counts;

    auto merge = [&](int left, int mid, int right) {
      if(left >= right) return;
      int l = mid, r = right, cur = right;
      while (l >= left && r > mid) {
        if(nums[l] < nums[r]) {
          counts[nums[l]] += r - mid; // all the elements from mid to r, are surpassers of nums[l], so count += r-mid
          ret = max(ret, counts[nums[l]]);
          temp[cur--] = nums[l--]; // put the smallest value on the right of the array, that is , decreasing order not natrual ordering
        } else {
          temp[cur--] = nums[r--];
        }
      }
      while(l >= left) temp[cur--] = nums[l--];
      while(r > mid) temp[cur--] = nums[r--];
      copy(temp.begin()+left, temp.begin()+right+1, nums.begin()+left);
    };
    function<void(int,int)> sort = [&](int left, int right) {
      if(left >= right) return;
      int mid = left + (right - left) / 2;
      sort(left, mid);
      sort(mid + 1, right);
      merge(left, mid, right);
    };
    sort(0, (int)nums.size() - 1);
    return ret;
  }
}

/*
用BST的话也很简单，注意每个节点要存surpasser的数量，O(NlogN)建树，然后O(N)遍历一遍找到最大值即可。注意tricky的地方是BST的定
义是不允许有重复的，而这题的输入数组是有重复的，要处理一下这个情况。
Initialize an empty binary search tree and then iterate in reverse order through the array.
 */