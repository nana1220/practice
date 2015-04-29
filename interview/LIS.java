/*
The longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence
such that all elements of the subsequence are sorted in increasing order.
For example, length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is {10, 22, 33, 50, 60, 80}.
一维DP经典题。
 */

class Solu{
  // can store the prev index with max length in dp[], s.t to trace back the LIS
  int lis( int arr[], int n ) {
    vector<int> dp(n,1);
    for ( int i = 1; i < n; i++ )
      for ( int j = 0; j < i; j++ )
        if ( arr[i] > arr[j] && dp[i] < dp[j] + 1)
          dp[i] = dp[j] + 1;

    max = *max_element(dp.begin(), dp.end());
    return max;
  }
}
