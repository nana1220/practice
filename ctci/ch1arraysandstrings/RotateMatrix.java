package ch1arraysandstrings;

import java.lang.System;
import java.lang.String;

public class RotateMatrix {

  public static void main(String[] args) {
    int[][] image3 = new int[][] {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    rotateMatrix(image3, 3);
    printMatrix(image3);

    int[][] image4 = new int[][] {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}
    };
    rotateMatrix(image4, 4);
    printMatrix(image4);
  }

  static void rotateMatrix(int[][] image, int N) { // 4 bytes pixel is a 32 bits int
    for (int i = 0; i < N / 2; i++) { // layer
      int first = i; // left or top boarder
      int last = N - 1 - i; // right or bottom boarder
      for (int j = 0; j < last - first; j++) { // last - first  elements are copied for each board not last -first +1
        int temp = image[i][first + j]; // top: row defined by layer
        image[i][first + j] = image[last - j][i]; // left: column defined by layer
        image[last - j][i] = image[N - 1 - i][last - j]; // bottom: row defined by layer
        image[N - 1 - i][last - j] = image[first + j][N - 1 - i]; // right: column defined by layer
        image[first + j][N - 1 - i] = temp;
      }
    }
  }

  static void printMatrix(int[][] image) {
    for (int[] val : image) {
      for (int v : val) {
        System.out.print(v + " ");
      }
      System.out.print("\n");
    }
  }
}