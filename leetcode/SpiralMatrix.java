/*
    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
    For example,
    Given the following matrix:
    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
    You should return [1,2,3,6,9,8,7,4,5].
*/

// Iterative version, use 4 variables to mark the 4 borders of the matrix.
// time: O(n); space:(1)
public class Solution {
  public ArrayList<Integer> spiralOrder(int[][] matrix) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    if (matrix == null || matrix.length==0 || matrix[0].length==0)  return res;
    int left=0, right=matrix[0].length-1, top=0, bottom= matrix.length-1;
    while (true){
      for (int i=left; i<=right; i++)
        res.add(matrix[top][i]);
      if (++top > bottom) break;
      for (int i=top; i<=bottom; i++)
        res.add(matrix[i][right]);
      if (--right < left) break;
      for (int i=right; i>=left; i--)
        res.add(matrix[bottom][i]);
      if (--bottom < top) break;
      for (int i=bottom; i>=top; i--)
        res.add(matrix[i][left]);
      if (++left > right) break;
    }
    return res;
  }

  //If more than one row and column left, it can form a circle and we process the circle. Otherwise,
  // if only one row or column left, we process that column or row ONLY.
  public ArrayList<Integer> spiralOrder(int[][] matrix) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    if(matrix == null || matrix.length == 0) return result;
    int m = matrix.length;
    int n = matrix[0].length;
    int x=0;
    int y=0;
    while(m>0 && n>0){
      //if one row/column left, no circle can be formed
      if(m==1){
        for(int i=0; i<n; i++){
          result.add(matrix[x][y++]);
        }
        break;
      }else if(n==1){
        for(int i=0; i<m; i++){
          result.add(matrix[x++][y]);
        }
        break;
      }
      //below, process a circle
      //top - move right
      for(int i=0;i<n-1;i++){
        result.add(matrix[x][y++]);
      }
      //right - move down
      for(int i=0;i<m-1;i++){
        result.add(matrix[x++][y]);
      }
      //bottom - move left
      for(int i=0;i<n-1;i++){
        result.add(matrix[x][y--]);
      }
      //left - move up
      for(int i=0;i<m-1;i++){
        result.add(matrix[x--][y]);
      }
      x++;
      y++;
      m=m-2;
      n=n-2;
    }

    return result;
  }
}