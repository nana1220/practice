/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.

 */

// dp
/*
dp[i][j]表示进入这个格子后保证knight不会死所需要的最小HP。如果一个格子的值为负，那么进入这个格子之前knight需要有的最小HP是-dungeon[i][j] + 1.如果格子的值非负，那么最小HP需求就是1.
xDP的方向是从最右下角开始一直到左上角。
 */
public class Solution {
  public int calculateMinimumHP(int[][] dungeon) {
    int m = dungeon.length;
    int n = dungeon[0].length;
    int[][] dp = new int[m][n];
    dp[m-1][n-1] = Math.max(1, -1*dungeon[m-1][n-1] +1); // currentblood + dungeon >=1 and currentblood>=1, so currentblood >= max(-1*dungeon+1, 1)
    for(int i=m-1; i>0;i--){
      dp[i-1][n-1] = Math.max(1, dp[i][n-1]-dungeon[i-1][n-1]); // currentblood + dungeon =nextblood and currentblood>=1
    }
    for(int j=n-1; j>0;j--){
      dp[m-1][j-1] = Math.max(1, dp[m-1][j]-dungeon[m-1][j-1]); // currentblood + dungeon =nextblood and currentblood>=1
    }
    for(int i=m-1;i>0;i--){
      for(int j=n-1; j>0; j--){
        // currentblood + dungeon = min(nextblooddown, nextbloodright) and current>=1
        dp[i-1][j-1] = Math.max(1, Math.min(dp[i][j-1], dp[i-1][j]) -dungeon[i-1][j-1]);
      }
    }
    return dp[0][0];
  }
}

// brute force: TLE
public class Solution {
  public int calculateMinimumHP(int[][] dungeon) {
    int m = dungeon.length;
    int n = dungeon[0].length;
    int[] minBlood = new int[]{Integer.MAX_VALUE};
    ArrayList<Integer> minBloods = new ArrayList<Integer>();
    dfs(dungeon,0,0,0,minBlood,minBloods);
    int max=minBloods.get(0);
    for(int b : minBloods){
      if(b>max) max=b;
    }
    if(max>0) return 1;
    else return -1*max +1;
  }
  void dfs(int[][] dun, int x, int y, int blood, int[] minBlood, ArrayList<Integer> minBloods){
    int m=dun.length;
    int n=dun[0].length;
    blood += dun[x][y];
    int tmp = minBlood[0];
    minBlood[0]=Math.min(blood, minBlood[0]);
    if(x==m-1 && y==n-1){
      minBloods.add(minBlood[0]);
      minBlood[0]=tmp;
      return;
    }
    if(x+1<m) dfs(dun, x+1, y, blood, minBlood, minBloods);
    if(y+1<n) dfs(dun, x, y+1, blood, minBlood, minBloods);
    minBlood[0] = tmp; // NOTE: restore blood value before return
  }
}