/*
    Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/*
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

// Use the property that two cross lines with same slope are actually the same line
// [1] Choose an anchor point, create a line by choosing another point
// [2] count the number of lines of different slopes that pass through the anchor point, we
// use a map here to store the count result
// [3] Note there may be same points in the given set, which should be counted into all slopes
// [4] Note the two points with same x value, they have infinite slope
// time: O(n^2); space: O(n)

// NOTE::: use hashmap to associate slope with counts
public class Solution {
  public int maxPoints(Point[] points) {
    if (points==null)   return 0;
    if (points.length<=2)   return points.length;// note the case of only one point
    int N = points.length, max=2;
    for (int i=0; i<N; i++){
      Map<Double, Integer> map = new HashMap<Double, Integer>();
      int duplicates =1, infinite=0;  // duplicate counts the number of points that have the same coordinates as p1, 1 denotes p1 itself
      // NOTE!!: j starts from i+1, there are C^2_n = n(n-1)/2 lines for n points, not n^2
      for (int j=i+1; j<N; j++){ // infinite counts the number of lines with infinite slope
        Point p1 = points[i], p2 = points[j];
        if (p1.x==p2.x){
          if (p1.y==p2.y)     duplicates++;
          else    infinite++;
        }
        else{
          double slope = p1.y==p2.y ? 0.0 : (double) (p1.y-p2.y)/(p1.x-p2.x); // because slopes with 0.0 and -0.0 should be treated as same slope
          if (!map.containsKey(slope))    map.put(slope, 0);
          map.put(slope, map.get(slope)+1);
        }
      }
      for (int num : map.values())    max = Math.max(duplicates+num, max);
      max = Math.max(max, duplicates+infinite);
    }
    return max;
  }
}