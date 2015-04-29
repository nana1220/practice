/*
Write a function that takes the following inputs and gives the following outputs.

Input: A list of points in 2-dimensional space, and an integer k
Output: The k input points closest to (5, 5), using Euclidean distance

Example:
Input: {(-2, -4), (0, 0), (10, 15), (5, 6), (7, 8), (-10, -30)}, k = 2
Output: {(5, 6), (7, 8)}
 */

// min heap
class Solu{
  struct Point {
    int x, y;
  int distance_square(const Point& p) {
    return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
  }
  Point(int x, int y) :x(x), y(y){}
};
vector<Point> get_k_closest_pts(const vector<Point>& pts, int k) {
    if(pts.size() < k) return pts;

    Point origin(5,5);
    vector<Point> heap(pts.begin(), pts.begin() + k);

    auto comp = [&](const Point& p0, const Point& p1) -> bool {
    return origin.distance_square(p0) < origin.distance_square(p1);
    };

    make_heap(heap.begin(), heap.end(), comp);

    for(int i = k; i < (int)pts.size(); ++i) {
    heap.push_back(pts[i]);
    push_heap(heap.begin(), heap.end(), comp);

    pop_heap(heap.begin(), heap.end(), comp);
    heap.pop_back();
    }

    return heap;
    }
}