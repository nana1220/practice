/*
Given an integer array, adjust each integers so that the difference of every adjcent integers are not greater than a given number target.
If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|
Note:
You can assume each number in the array is a positive integer and not greater than 100
Example:
Given [1,4,2,3] and target=1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it’s minimal. Return 2.
 */

class Solu{
  //dp[i][j]: the minimal adjust cost on changing A[i] to j,  dp[i][j] = min{dp[i-1][k] + |j-A[i]|} s.t. |k-j| <= target
  #define MAXTARGET 100
  int MinAdjustmentCost(vector<int> & A, int target){
    if(A.empty()) return 0;
    int dp[A.size()][MAXTARGET+1];
    for (int i = 0; i <= MAXTARGET; ++i)
      dp[0][i] = abs(i-A[0]);

    for(int i=1; i<A.size();i++){
      for(int j=0;j<=MAXTARGET;j++){
        dp[i][j] = -1;
        if (dp[i-1][j] < 0) continue;
        for (int k = max(j-target, 0); k<=min(MAXTARGET, j+target); ++k) { // note max(,0), and min(MAXTARGET)
          dp[i][j] = min(dp[i][j], dp[i-1][k] + abs(A[i]- j));
        }
      }
    }
    int ret = -1;
    for (int i = 1; i <= MAXTARGET; i++) {
      ret = min(ret, dp[A.size()-1][i]);
    }
    return ret;
  }
}

/*
此题可以用记忆化搜索也可以用DP。
记忆化搜索用的Memory矩阵含义为
M[i][j]：从index = i处开始往后所有的differ，并且A[i]的取值取为j + 1;

 */
class SOlu{
  int dfs(vector<vector<int>>& M, const vector<int>& A, vector<int>& B, int target, int index) {
    int len = (int)A.size();
    if (index >= len) return 0;
    int dif = 0, min = INT_MAX;

    for (int i = 0; i <= 100; i++) {
      if (index != 0 && abs(i - B[index - 1]) > target) {
        continue;
      }
      if (M[index][i - 1] != INT_MAX) {
        dif = M[index][i - 1];
        min = std::min(min, dif);
        continue;
      }

      B[index] = i;
      dif = abs(i - A[index]) + dfs(M, A, B, target, index + 1);
      min = std::min(min, dif);

      M[index][i - 1] = dif;
      B[index] = A[index];
    }

    return min;
  }
  int MinAdjustmentCost(vector<int> A, int target) {
    vector<vector<int>> M(100,vector<int>(100,INT_MAX));
    vector<int> B = A;
    return dfs(M, A, B, target, 0);
  }
}