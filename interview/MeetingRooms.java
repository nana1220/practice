/*
Given a array of pairs where each pair contains the start and end time of a meeting (as in int),
Determine if a single person can attend all the meetings
For example:
Input array { pair(1,4), pair(4, 5), pair(3,4), pair(2,3) }
Output: false
 */
public class Solu {
  bool attend_all(vector<pair<int, int>> meetings) {
    // sort by left end
    sort(meetings.begin(), meetings.end(),[ &](pair <int,int>p0, pair <int,int>p1){
      return p0.first < p1.first;
    });
    for (size_t i = 1; i < meetings.size(); ++i) {
      // every consecutive intervals must not overlap
      if (meetings[i].first < meetings[i - 1].second) return false;
    }
    return true;
  }
}

/*
Follow up:
determine the minimum number of meeting rooms needed to hold all the meetings.
Input array { pair(1, 4), pair(2,3), pair(3,4), pair(4,5) }
Output: 2
 */
// 把起点和终点放在一个数组里，然后排序。然后扫描这个排序好的时间点数组，是起点则当前房间+1，是重点则-1，其中扫描过程中的最大值就是最小房间数。
// 我们需要用一种方式来区分起点和终点。下面给的方法是把终点都用负数存，排序的比较函数直接比较他们的绝对值，然后通过正负号来判断这个时间点是起点还是终点。
public class Solu{
  int min_rooms(vector<Interval>& meetings) {
    vector<int> times;
    for(auto m : meetings) {
      times.push_back(m.begin);
      times.push_back(-m.end);
    }
    sort(times.begin(), times.end(), [](int a, int b){
      return abs(a) == abs(b) ? a < b : abs(a) < abs(b); // if begin == end, put end ahead of begin, because no need for an extra room
    });
    int ret = 0, cur = 0;
    for(auto t : times) {
      if(t >= 0) ret = max(ret, ++cur);
      else --cur;
    }
    return ret;
  }
}