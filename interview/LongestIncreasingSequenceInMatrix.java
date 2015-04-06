/*
find the longest increasing sequence in an integer matrix in 4 directions (up down left right),
return the sequence; for example: input:. visit 1point3acres.com for more.
Ex: input: consider 4x4 grid
2 3 4 5
4 5 10 11
20 6 9 12
6 7 8 40

output : 4 5 6 7 8 9 10 11 12
 */
class Solution1{
  /*
   find the longest length for every point, and record the result in a 2D
   array so that we do not need to calculate the longest length for some points again.
   */
  int original[m][n] = {...};
  int longest[m][n] = {0};

  int find() {
    int max = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int current = findfor(i, j);
        if (current > max) { max = current; }
      }
    }
    return max;
  }

  int findfor(int i, int j) {
    if (longest[i][j] == 0) {
      int max = 0;
      for (int k = -1; k <= 1; k++) {
        for (int l = -1; l <= 1; l++) {
          if (!(k == 0 && l == 0) &&
              i + k >= 0 && i + k < m &&
              j + l >= 0 && j + l < n &&
              original[i + k][j + l] > original[i][j]
              )
            int current = findfor(i + k, j + l); // surrending four cells
          if (current > max) { max = current; }
        }
      }
    }
    longest[i][j] = max + 1;
  }
  return longest[i][j];
}
}


// time complexity is: O(M.N), given array's dimension is MxN.
/*. create a new 2d array (temp[][]) of same dimension as original.
    the element temp[i][j] contains the length of the LIS starting at this point.
    first fill this temp array with 0's.
*/
class Solution2 {
  int arr[M][N]; // given array.
  int temp[M][N];

  for(i=0;i<M;i++){
    for (j = 0; j < N; j++) {
      if (temp[i][j] == 0) {
        fill(temp, i, j);
      }
    }
  }

  // thus it will calculate each element of temp array only once.
  int fill(int temp[][], int i, int j) {
    if (i < 0 || j < 0 || i >= M || j >= N)
      return 0;
    if (temp[i][j] != 0)
      return temp[i][j];
    int left = 0, right = 0, up = 0, down = 0; // can use only ome int max;
    if (i > 1 && arr[i - 1][j] = arr[i][j] + 1)
      up = fill(temp, i - 1, j);   // max = Math.max(max, up);
    if (i < M - 1 && arr[i + 1][j] = arr[i][j] + 1)
      down = fill(temp, i + 1, j);  // max = Math.max(max, down);
    if (j > 1 && arr[i][j - 1] = arr[i][j] + 1)
      left = fill(temp, i, j - 1);
    if (j < M - 1 && arr[i][j + 1] = arr[i][j] + 1)
      right = fill(temp, i, j + 1);
    temp[i][j] = max(up, down, left, right) + 1;
    return temp[i][j];
  }

  // find the maximum element in the array.it's value will be the length of LIS.
  void find() {
    max_x = 0, max_y = 0;
    for (i = 0; i < M; i++) {
      for (j = 0; j < N; j++) {
        if (temp[i][j] > temp[max_x][max_y]) {
          max_x = i;
          max_y = j;
        }
      }
    }
  }

  // Above will  give the length of LIS.now, to find the LIS, we will check it's neighbours whose
  // LIS value difference is 1.
  void findSequence() {
    i = max_x, j = max_y;
    printf("%d ", temp[i][j]);
    while (temp[i][j] != 1) {
      if (i > 0) {
        if (temp[i - 1][j] == temp[i][j] - 1 && arr[i - 1][j] == arr[i][j] + 1) {
          i = i - 1;
          System.out.println(arr[i][j]);
   //System.out.println(i+" "+j);
          continue;
        }
      }
      if (i < M -1) {
        if (temp[i + 1][j] == temp[i][j] - 1 && arr[i + 1][j] == arr[i][j] + 1) {
          i = i + 1;
          System.out.println(arr[i][j]);
          continue;
        }
      }
      if (j > 0) {
        if (temp[i][j - 1] == temp[i][j] - 1 && arr[i][j - 1] == arr[i][j] + 1) {
          j = j - 1;
          System.out.println(arr[i][j]);
          continue;
        }
      }
      if (j < N -1) {
        if (temp[i][j + 1] == temp[i][j] - 1 && arr[i][j + 1] == arr[i][j] + 1) {
          j = j + 1;
          System.out.println(arr[i][j]);
          continue;
        }
      }
    }
  }
}

