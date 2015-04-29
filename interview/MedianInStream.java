/* Facebook
Create the data structure for a component that will receive a series of numbers over the time and, when asked, returns the median of all received elements.
 */

class solu{
  class OnlineMedian {
    public:
    void add_number(int number) {
      if (_max_heap.empty() || _max_heap[0] > num) {
        _max_heap.push_back(num);
        push_heap(_max_heap.begin(), _max_heap.end());

        if (_max_heap.size() > _min_heap.size()) {
          pop_heap(_max_heap.begin(), _max_heap.end());
          _min_heap.push_back(_max_heap.back());
          push_heap(_min_heap.begin(), _min_heap.end(), greater<int>());
          _max_heap.pop_back();
        }
      } else if (_max_heap[0] <= num) {
        _min_heap.push_back(num);
        push_heap(_min_heap.begin(), _min_heap.end(), greater<int>());

        if (_min_heap.size() > _max_heap.size() &&
            _min_heap.size() - _max_heap.size() >1) {
          pop_heap(_min_heap.begin(), _min_heap.end(), greater<int>());
          _max_heap.push_back(_min_heap.back());
          push_heap(_max_heap.begin(), _max_heap.end());
          _min_heap.pop_back();
        }
      }
    }
    int get_median() const {
      if(_min_heap.empty()) return0;
      if(_min_heap.size() == _max_heap.size()) {
        return ((double)_min_heap[0] + (double)_max_heap[0]) *0.5;
      } else {
        return _min_heap[0];
      }
    }
    private:
    vector<int> _min_heap;
    vector<int> _max_heap;
  };

}