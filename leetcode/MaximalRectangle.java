/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */

// this is a generalization of Largest Rectangle in Histogram
// each row i maintian a histogram from row 0 to row i
// compute Largest Rectangle for each row, keep the largest area for all rows
public class Solution {
  public int maximalRectangle(char[][] matrix) {
    int m = matrix.length;
    if (m==0) return 0;
    int n = matrix[0].length;
    if(n==0) return 0;
    int[][] heights = new int[m][n]; // heights of histogram on each row
    for (int j=0; j<n;j++){
      if(matrix[0][j] == '1'){
        heights[0][j] =1;
      } else heights[0][j]=0;
    }
    for(int i=1; i<m; i++){
      for(int j=0;j<n;j++){
        if(matrix[i][j] == '0') heights[i][j] =0;
        else heights[i][j]=1+heights[i-1][j];
      }
    }
    int max=0;
    for(int i=0; i<m; i++){
      Stack<Integer> lefts = new Stack<Integer>();
      int right =0;
      while(right<n){
        if(lefts.isEmpty() || heights[i][lefts.peek()] <= heights[i][right]){
          lefts.push(right++); // remember to update right!!
        } else{
          int h = heights[i][lefts.pop()];
          int l = lefts.isEmpty() ? 0 : lefts.peek()+1;
          int width =  right-l;
          max= Math.max(h*width, max);
        }
      }
      while(!lefts.isEmpty()){
        int h = heights[i][lefts.pop()];
        int l = lefts.isEmpty() ? 0 : lefts.peek()+1;
        int width =  right-l;
        max= Math.max(h*width, max);
      }
    }
    return max;
  }
}