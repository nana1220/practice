/*
Given two lines on a Cartesian plane, determine whether the two lines would inter-
sect.
 */
package ch7mathematicsandprobability;

public class Line {
  /*
  Understand limitations of floating point representations. Never check for equality
  with ==. Instead, check if the difference is less than an epsilon value.
   */
  static double epsilon = 0.000001;
  public double slope;
  public double yintercept;
  public Line(double s, double y) {
    slope = s;
    yintercept = y;
  }
  public boolean intersect(Line Iine2) {
      return Math.abs(slope - Iine2.slope) > epsilon
          || Math.abs(yintercept - Iine2.yintercept) < epsilon;
  }
}
