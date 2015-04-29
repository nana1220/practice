// Quick Select
// Instead of recursing into both sides, as in quicksort, quickselect only recurses into one side
// This reduces the average complexity from O(n log n) (in quicksort) to O(n) (in quickselect).

class SOlu{
  int kth_smallest(vector<int>& input, int k) {
    auto partition = [&](int l, int r) {
      int e = l;
      int pivot = input[r];
      while(e <= r) {
        if (input[e] > pivot){
          swap(input[e], input[r--]);
        } else if (input[e] == pivot) {
          e++;
        } else {
          swap(input[l++], input[e++]);
        }
      }
      return l;
    };

    function<int(int, int, int)> selector =
    [&](int l, int r, int k){
      if (l == r) return input[l];

      int new_pivot = partition(l, r);
      int length = new_pivot - l + 1;

      if (k == length) return input[new_pivot];
      else if (k < length) return selector(l, new_pivot - 1, k);
      else return selector(new_pivot + 1, r, k - length);
    };

    return selector(0, (int)input.size()-1, k);
  }
}