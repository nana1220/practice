/*
 * *
 * least cost to cut a batten
 * the cost of cutting each segment is the cost of the segment length,
 * an array is storing each end point,
 * eg:[0, 3, 7, 8, 11, 12], the batten length is 12, there are 4 cuting point
 *
 * solution:
 * dp[i][j] means the min cost of cutting batten start at i, end at j.
 * dp[i][j] = dp[i][i + k] + dp[i + k] [j]  enumerates.
 */


/*
Use dynamic programming.
1) Add two auxilary marks on each end of the log (totally n+2 marks)
2) min_cost[i][j]=minimum{min_cost[i][k] + min_cost[k][j] + length[i][j]}, where k=i+1 to j-1
3) min_cost[i][i+1]=0
4) min_cost[i][i+2]=length[i][i+2]
Find min_cost[0][n+1].
 */
public class Solu {

  public int getMinCost(int[] a){
    if(a == null || a.length == 0) return -1;
    int n = a.length;
    int[][] dp = new int[n][n];

    Arrays.fill(dp, 0);

    for(int i = 2; i < n; i++){
      for(int j = 0; j + i < n; j++){
        int min = Integer.MAX_VALUE;
        for(int k = 1; k < i; k++)
          min = Math.min(min, dp[j][j + k] + dp[j + k][j + i] + a[j + i] - a[j]);
        dp[j][j+i] = min;
      }
    }

    return dp[0][n - 1];
  }

  public static void main(String[] args) {

  }
}