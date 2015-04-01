/*
 find the number of islands
 */

public class NumberOfIslands {
  int getNumber(int[][] matrix) {
    int res = 0;
    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < matrix[0].length; y++) {
        if (isIsland(matrix, x, y)) res++;
      }
    }
    return res;
  }

  boolean isIsland(int[][] matrix, int x, int y) {
    if (x < 0 || x >= matrix.length || y < 0 || y > matrix[0].length) {
      return false;
    }
    if (matrix[x][y] == 1) return false;
    matrix[x][y] = 1; // 0 means belong to island, set 0 to 1
    isIsland(matrix, x + 1, y);
    isIsland(matrix, x, y + 1);
    isIsland(matrix, x - 1, y);
    isIsland(matrix, x, y - 1);
    return true;
  }
}