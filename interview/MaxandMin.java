/*
Find the max and min elements of an unsorted integer array using a minimal number of comparisons
 */

class Solu{
  void max_min(int a[], int n, int& max, int& min) {
    if(n <= 0) return;
    if(n == 1){
      max = min = a[0];
      return ;
    }
    for (int i = 0; i < n; i+=2)
      if(i<n && a[i] < a[i+1])
        swap(a[i], a[i+1]);

    max = a[0];
    for (int i = 2; i < n; i+=2)
      if(max < a[i]) max = a[i];

    min = a[1];
    for (int i = 1; i < n; i+=2)
      if(min > a[i]) min = a[i];
  }
}