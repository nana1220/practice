/*
    Given a collection of intervals, merge all overlapping intervals.
    For example,
    Given [1,3],[2,6],[8,10],[15,18],
    return [1,6],[8,10],[15,18].
*/

/*
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

// not inplace
// time: O(nlgn); space: O(n)
public class Solution {
  public List<Interval> merge(List<Interval> intervals) {
    if (intervals.isEmpty()) return  new ArrayList<Interval>();
    Comparator<Interval> comp = new Comparator<Interval>(){
      public int compare(Interval i1, Interval i2){
        return i1.start - i2.start;
      }
    };
    Collections.sort(intervals, comp);
    List<Interval> res = new ArrayList<Interval>();
    int start = intervals.get(0).start; // need to check if intervals is empty
    int end =intervals.get(0).end;
    for(Interval in : intervals){
      if(in.start > end){
        res.add(new Interval(start, end));
        start = in.start;
        end = in.end;
      } else{
        end = Math.max(end, in.end); // NOTE: previous interval's end might be larger than current interval's end
      }
    }
    res.add(new Interval(stafrt, end)); // NOTE: need to add last interval!!!!!!!!!!!
    return res;
  }
}
// Sort all the intervals according start value, and then merge and delete
// time: O(nlgn); space: O(1) -- in place
public class Solution {
  public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    if (intervals == null || intervals.size() <= 1)
      return intervals;
    // no need to compare end here
    Comparator<Interval> com = new Comparator<Interval>(){
      public int compare(Interval in1, Interval in2) {
        return in1.start - in2.start;
      }
    };
    Collections.sort(intervals, com);
    int i = 0;
    while (i < intervals.size()-1){
      Interval curr = intervals.get(i)
      Interval next = intervals.get(i+1);
      if (curr.end < next.start)
        i++;
      else { // merge interval don't update i++
        curr.start = Math.min(curr.start, next.start);
        curr.end = Math.max(curr.end, next.end);
        intervals.remove(i+1);
      }
    }
    return intervals;
  }
}