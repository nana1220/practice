package ch9recursionanddynamicprogramming;

import java.lang.System;
import java.util.ArrayList;

/*
 * Write an algorithm to print all ways of arranging eight queens on an 8x8 chess
 * board so that none of them share the same row, column or diagonal.
 */
public class EightQueues {
  // only need a single array where column [row] = c indicates that
  // row r has a queen at column c
  // So not necessary to define Point class
  static class Point {
    int x;
    int y;
    Point (int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  // only previous rows have been put queens, so iterate 0 to row - 1 in way
  static boolean checkValid(int row, int col, Point[] way) {
    for (int i = 0; i < row; i++) {
      Point p = way[i];
      if (p.y == col) { // a queen of previous row is in the same col
        return false;
      }
      // if row distance == col distance then in the same diagonal
      if (Math.abs(p.x - row) == Math.abs(p.y - col)) {
        return false;
      }
    }
    return true;
  }

/*
 * Note!!
 * Each valid path must be cloned then add to ways, otherwise next path will overwrite it
 */
  static void storeWays(ArrayList<Point[]> ways, Point[] way, int row) {
    if (row == 8) {
      ways.add(way.clone()); // must clone before add to result
      return;
    }
    for (int col = 0; col < 8; col++) {
      if (checkValid(row, col, way)) {
        way[row] = new Point(row, col);
        storeWays(ways, way, row + 1);
      }
    }
  }

  public static void main(String[] args) {
    ArrayList<Point[]> ways = new ArrayList<Point[]>();
    Point[] way = new Point[8];
    storeWays(ways, way, 0);
    for (Point[] w : ways) {
      for (Point p : w) {
        System.out.print("(" + p.x + ", " + p.y + ")");
      }
      System.out.println();
    }
    System.out.println(ways.size());
  }
}