/*
Give an unsorted array find count of pairs of numbers[a,b] where a > b and b comes after a in the array.
Given array A[1... n], for every i < j, find all inversion pairs such that A[i] > A[j]
 */

/*
While merging algorithm counts number of removed inversions (sorted out one might say).

The only moment when inversions are removed is when algorithm takes element from the right side of an array and merge
it to the main array. The number of inversions removed by this operation is the number of elements left from the the left array to be merged.
 */

/*
Suppose we know the number of inversions in the left half and right half of the array (let be inv1 and inv2),
what kinds of inversions are not accounted for in Inv1 + Inv2? The answer is – the inversions we have to count during
the merge step. Therefore, to get number of inversions, we need to add number of inversions in left subarray, right subarray and merge().
 */
class SOlu{
  long merge(int[] arr, int[] left, int[] right) {
    int i = 0, j = 0, count = 0;
    while (i < left.length || j < right.length) {
      if (i == left.length) {
        arr[i+j] = right[j];
        j++;
      } else if (j == right.length) {
        arr[i+j] = left[i];
        i++;
      } else if (left[i] <= right[j]) {
        arr[i+j] = left[i];
        i++;
      } else {
        arr[i+j] = right[j];
        count += left.length-i; // left[i] > right[j], means [i, left.length-1] > j, a total of left.length -i
        j++;
      }
    }
    return count;
  }

  long invCount(int[] arr) {
    if (arr.length < 2)
      return 0;

    int m = (arr.length - 1) / 2;
    int left[] = Arrays.copyOfRange(arr, 0, m+1);
    int right[] = Arrays.copyOfRange(arr, m+1, arr.length);

    return invCount(left) + invCount(right) + merge(arr, left, right);
  }

}

class Solu{
  int inverse_pairs(vector<int> arr) {
    function<int(vector<int>&,vector<int>&,int,int)> merge =
    [&](vector<int>& dat,vector<int>& dup,int l, int r) {
      if(l >= r) {
        if(l > 0) dup[l] = dat[l];
        return 0;
      }

      int len = (r - l) / 2;
      int left = merge(dup,dat,l,l+len);
      int right = merge(dup,dat,l+len+1,r);

      int count = 0;
      int i = l + len, j = r, id_dup = r;

      while (i >= l && j >= l + len + 1) {
        if(dat[i] > dat[j]) {
          dup[id_dup--] = dat[i--];
          count += j - l - len;
        } else {
          dup[id_dup--] = dat[j--];
        }
      }

      for(; i >= l; --i) dup[id_dup--] = dat[i];
      for(; j >= l + len + 1; --j) dup[id_dup--] = dat[j];

      return left + right + count;
    };

    auto dup_arr = arr;
    return merge(arr, dup_arr, 0, (int)arr.size() - 1);
  }
}