/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
 */

// BST first col to determine which row, after BST finish the upper bound index points to the row
public class Search2DMatrix {
  static public boolean searchMatrix(int[][] matrix, int target) {
    int i=0;
    int j=matrix.length-1;
    while(i<=j){
      int mid = (i+j)/2;
      if(matrix[mid][0]==target){
        return true;
      }
      if(matrix[mid][0] > target) j = mid-1;
      else i=mid+1;
    }
    // target should be in the j-th row
    int row = j;
    System.out.println(j);
    if(row<0 || row>=matrix.length) return false;
    i=0;
    j=matrix[0].length -1; // don't forget -1
    while(i<=j){
      int mid =(i+j)/2;
      if(matrix[row][mid] == target) return true;
      if(matrix[row][mid] > target) j = mid-1;
      else i=mid+1;
    }
    return false;
  }

  // the matrix can be considered as a sorted array. To find one element in this sorted array by using binary search,
  // so only one BST is needed
  public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
      if(matrix==null || matrix.length==0 || matrix[0].length==0)
        return false;

      int m = matrix.length;
      int n = matrix[0].length;

      int start = 0;
      int end = m*n-1;

      while(start<=end){
        int mid=(start+end)/2;
        int midX=mid/n; // Note: divide col number to get row
        int midY=mid%n; // Note: mod col number to get col

        if(matrix[midX][midY]==target)
          return true;

        if(matrix[midX][midY]<target){
          start=mid+1;
        }else{
          end=mid-1;
        }
      }

      return false;
    }
  }
}