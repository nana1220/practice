package ch9recursionanddynamicprogramming;

/*
 * Implement the "paint fill" function that one might see on many image editing
 * programs. That is, given a screen (represented by a two-dimensional array of colors),
 * a point, and a new color, fill in the surrounding area until the color changes from the
 * original color
 */

import java.util.ArrayList;


public class PaintFill {

  static enum Color {
    Red, Green, Blue, Yellow;
  }

  /*
   * Note the ordering of the x and y in screen[y] [x], and remember this when you hit
   * graphical problem. Because x represents the horizontal axis {that is, it's left to right), it
   * actually corresponds to the number of a column, not the number of rows. The value of
   * y equals the number of rows
   */
  static void painFill(int x, int y, Color[][] matrix, Color newColor, Color oldColor) {
    // base case
    if (x < 0 || y < 0 || x >= matrix[0].length || y >= matrix.length) {
      return;
    }
    // pruning case, if not satisfied prune this subtree
    if (matrix[y][x] == oldColor) {
      matrix[y][x] = newColor; // don't forget
      painFill(x + 1, y, matrix, newColor, oldColor);
      painFill(x - 1, y, matrix, newColor, oldColor);
      painFill(x, y + 1, matrix, newColor, oldColor);
      painFill(x, y - 1, matrix, newColor, oldColor);
    }
  }
}