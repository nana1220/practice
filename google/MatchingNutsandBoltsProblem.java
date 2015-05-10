/*
Matching nuts and bolts problem can be stated as follows: “Given a collection of n nuts of distinct sizes and n bolts
such that there is a one-to-one correspondence between the nuts and the bolts, find for each nut its corresponding bolt.
We can only compare nuts to bolts i.e., we can neither compare nuts to nuts nor bolts to bolts.
 */



/*
Solution to this problem is with help of quicksort.
1. pick any object A randomly, then compare it all B to find the exact match for it. This pivot in quicksort.
2. While searching for match divide B into two part with respect to pivot so this will make
all small object (of type B) than pivot on left side and all large object (of type B ) on right.. same as quicksort.
3. So at this point we find exact match for B as well as we aligend B. Now we can do the same thing on array of object A .
Then repeat the process for on both halves.

Complexity will be (n lg n)
 */

class SOlu{
  void sort_two_groups(vector<int>& a, vector<int>& b) {
    auto partition = [&](vector<int>& arr, int l, int r, int pivot) {
      int e = l;
      while(e <= r) { // same as sort three colors, pivot is also treated as a color
        if(arr[e] < pivot) swap(arr[l++], arr[e++]);
        else if(arr[e] > pivot) swap(arr[e], arr[r--]); // don't increase e
        else e++; // do nothing keep going
      }
      return l;
    };
    function<void(int,int)> sort = [&](int l, int r) {
      if(l >= r) return;
      int val_a = a[l + (rand() % (r - l + 1))];

      int pivot = partition(b, l, r, val_a); // sort nuts
      partition(a, l, r, b[pivot]); // sort bolts

      sort(l, pivot - 1); // quick sort
      sort(pivot + 1, r); // quick sort
    };
    sort(0, (int)a.size() - 1);
  }
}