/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
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

// inplace
public class Solution {
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    if (intervals.isEmpty()) {
      intervals.add(newInterval);
      return intervals;
    }
    int i = 0;
    while (i < intervals.size() && intervals.get(i).end <= newInterval.start) i++; // find prev.end <= newInterval.start < currentInterval.end
    if (i - 1 >= 0 && intervals.get(i - 1).end == newInterval.start) { // if prev.end = newInter.start, set prev.start as start
      i--;
    } else if (i == intervals.size()) { // if newInterval.start > last.end, add newInterval to the end
      intervals.add(newInterval);
    } else if (newInterval.end < intervals.get(i).start) { // if newInterval.end < current.start, insert newInterval
      intervals.add(i, newInterval);
    } else { // set start
      intervals.get(i).start = Math.min(intervals.get(i).start, newInterval.start);
    }
    while (i < intervals.size() - 1) { // handle merge, cannot merge last interval
      if (intervals.get(i).end >= newInterval.end) break;
      if (newInterval.end < intervals.get(i + 1).start) {
        intervals.get(i).end = newInterval.end;
        break;
      }
      if (newInterval.end >= intervals.get(i + 1).start) {
        intervals.get(i + 1).start = intervals.get(i).start;
        intervals.remove(i);
      }
    }
    if (newInterval.end > intervals.get(intervals.size() - 1).end) // handle last interval
      intervals.get(intervals.size() - 1).end = newInterval.end;
    return intervals;
  }
}

// Insert the interval first, then MergeInterval
// Note the case that newInterval is added to the end of the list
// time: O(n); space: O(1)
public class Solution {
  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
    if (intervals==null || intervals.size()==0){
      intervals = new ArrayList<Interval>(); intervals.add(newInterval);
      return intervals;
    }
    int i = 0;
    while (i<intervals.size() && intervals.get(i).start < newInterval.start)
      i++;
    if (i==intervals.size())    intervals.add(newInterval); // Note here
    else    intervals.add(i, newInterval);
    i = 0;
    while (i<intervals.size()-1){
      Interval curr = intervals.get(i), next = intervals.get(i+1);
      if (curr.end<next.start)
        i++;
      else{
        curr.start = Math.min(curr.start, next.start);
        curr.end = Math.max(curr.end, next.end);
        intervals.remove(i+1);
      }
    }
    return intervals;
  }
}

// a very neat solu
public class Solution {
  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

    ArrayList<Interval> result = new ArrayList<Interval>();

    for(Interval interval: intervals){
      if(interval.end < newInterval.start){
        result.add(interval);
      }else if(interval.start > newInterval.end){
        result.add(newInterval); // insert newInterval no merge
        newInterval = interval; // then repeatedly set newInterval and add
      }else if(interval.end >= newInterval.start || interval.start <= newInterval.end){
        newInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(newInterval.end, interval.end));
      }
    }

    result.add(newInterval);

    return result;
  }
}