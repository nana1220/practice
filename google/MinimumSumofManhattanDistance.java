/*
已知平面上有N个点，找到其中某个点P，使得P到其余所有点的Manhattan距离之和最短并求出这个最短距离。
因为是曼哈顿距离，所以可以把x轴和y轴分离开来计算。

针对x坐标对所有点排序。
求一个数组，每个元素为对应点到其他所有点的在x轴上的距离和。
这一步需要O(n)的算法，具体思路是，记录两个数组，left和right:
left[i] = x[1] + x[2] + ... + x[i-1]
right[i] = x[i+1] + x[i+2] ... + x[n]
然后对每个点
sum[i] = x[i] * i - left[i] + right[i] - (n - 1 - i) * x[i], sorted array, left<right
针对y坐标重复1和2的计算。
求得每个点在x和y上的1D曼哈顿距离和之后，可以遍历所有点，直接找出最小值。

1 sort according to x coordinates
2 scan from left to right, for each point, compute the total
  Manhattan distance on x from it to the rest of points
  (O(n) time for the left most point and O(1) for each of the rest)
3 do 1 and 2 for y coordinates
4 add up the distances on x and y for each point and locate the minimum
 */
class SOlu{
  map<pair<int,int>,int> mht_sum(const vector<Point>& pts, bool get_x) {
    int n = (int)pts.size();
    vector<int> left(n), right(n);

    int sum = 0;
    for(int i = 0; i < n; ++i) {
      left[i] = sum;
      sum += get_x ? pts[i].x : pts[i].y;
    }
    sum = 0;
    for(int i = n - 1; i >= 0; --i) {
      right[i] = sum;
      sum += get_x ? pts[i].x : pts[i].y;
    }
    map<pair<int,int>,int> ret;
    for(int i = 0; i < n; ++i) {
      int p = get_x ? pts[i].x : pts[i].y;
      ret[{pts[i].x,pts[i].y}] = p * i - left[i] + right[i] - (n-1-i) * p;
    }
    return ret;
  }
  int min_mht_dis(vector<Point> pts) {
    sort(pts.begin(), pts.end(), [&](const Point& p0, const Point& p1){
      return p0.x < p1.x;
    });
    auto x_sums = mht_sum(pts, true);

    sort(pts.begin(), pts.end(), [&](const Point& p0, const Point& p1){
      return p0.y < p1.y;
    });
    auto y_sums = mht_sum(pts, false);

    int ret = INT_MAX;
    for(int i = 0; i < pts.size(); ++i) {
      ret = min(ret, x_sums[{pts[i].x,pts[i].y}] + y_sums[{pts[i].x,pts[i].y}]);
    }
    return ret;
  }
}

/*
follow up：如果要求这个点与所给的k个点不重合，该怎么办？
如果这个位置与k个点重合，则从这个位置开始进行搜索，将这个点周围的点和对应的距离放入到一个堆里，每次从堆中取出最小距离的点，
然后将这个点周围的点放入堆中，直到取出的点不与所给k个点重合。时间复杂度klogk
因为最多从堆中取出k+1个点即可找到一个不与所给k个点重合的点。堆每次操作为logk。
 */