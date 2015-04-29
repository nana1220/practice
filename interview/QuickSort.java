class Solu{
  int partition (int arr[], int l, int h) {
    int x = arr[h];    // pivot
    int i = (l - 1);  // Index of smaller element

    for (int j = l; j <= h- 1; j++) {
      // If current element is smaller than or equal to pivot
      if (arr[j] <= x) {
        i++;    // increment index of smaller element
        swap(arr[i], arr[j]);  // Swap current element with index
      }
    }
    swap(arr[i + 1], arr[h]);
    return (i + 1);
  }
  void quickSort(int arr[], int l, int h) {
    if (l < h) {
      int p = partition(arr, l, h); /* Partitioning index */
      quickSort(arr, l, p - 1);
      quickSort(arr, p + 1, h);
    }
  }
}