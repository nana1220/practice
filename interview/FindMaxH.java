/*
一个array里面找最大的这样的h: 至少有h个数大于等于h。
比如{2,3,5}答案是2，{5,6,7,8}答案是4。

 * input: [2,3,5]
    * output: 2
    * input: [5,6,7,8]
    * output: 4
    * input: [4,6,7,8]
    * output: -1
 */


// 平均时间复杂度O(n),空间O(1)
class Solu {
  public static int findH(int[] a) {
    int start = 0;
    int end = a.length - 1;
    int pivot;
    int result = -1;

    while (start <= end) {
      swap(a, end, start + (end - start) / 2);
      pivot = a[end];
      int p = start - 1;

      //partitioning
      for (int i = start; i < end; i++)
        if (a[i] > pivot) swap(a, i, ++p);
      swap(a, end, ++p);

      if (p == a[p]) {
        result = a[p];
        end = p - 1;
      } else if (p < a[p]) start = p + 1;
      else end = p - 1;
    }

    return result;
  }
  private static void swap(int[] a, int i, int j) {
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
  }


  // counting
  int find_h_c(vector<int> a) {
    int n = (int)a.size();
    vector<int> m(n+1, 0);
    for(auto num : a) {
      if(num >= n) ++m[n];
      else ++m[num];
    }
    for(int i = n, sum = 0; i > 0; --i) {
      sum += m[i];
      if(sum >= i) return i;
    }
    return 0;
  }


  // sort
  int find_h_sort(vector<int> a) {
    sort(a.begin(), a.end(), greater<int>()); // decreasing order

    for(int i = (int)a.size(); i > 0; --i) { // from last(smallest) to first, if smallest value is larger than n, which means n values larger than n
      if(a[i-1] >= i) return i;
    }
    return 0;
  }



  // Modify countingSort, count the occurrence of each element, and get the suffix sum
  // time: O(K+N), K = Max-Min+1
  public int findH(int[] A) {
    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    for (int num : A) {
      max = Math.max(max, num);
      min = Math.min(min, num);
    }
    int K = max - min + 1;
    int[] C = new int[K];
    for (int num : A)
      C[num - min]++;
    for (int i = K - 2; i >= 0; i--) {
      int h = i + min;
      if (C[i + 1] > h)
        return -1;
      if (C[i + 1] == h)
        return h;
      C[i] += C[i + 1];
    }
    return C[0] < min ? C[0] : -1;
  }

  // Modify quick select, each time, we compare the pivot value and index
  // time: O(n)
  public int findH2(int[] A) {
    int N = A.length;
    int start = 0, end = N - 1;
    while (start <= end) {
      int pivot = partition(A, start, end);
      if (N-pivot-1 == A[pivot])
        return A[pivot];
      else if (N-pivot-1 > A[pivot])
        start = pivot+1;
      else
        end = pivot-1;
    }
    if (end==-1 && A[start]>N)
      return N;
    return -1;
  }

  private int partition(int[] A, int start, int end) {
    int i = start, j = start;
    int pivotValue = A[end];
    for (; j < end; j++) {
      if (A[j] <= pivotValue)
        swap(A, i++, j);
    }
    swap(A, i, end);
    return i;
  }

  private void swap(int[] A, int i, int j) {
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
  }
}

