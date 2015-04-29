/*
要求实现对于一个window_size， 不停插入值，返回当前的平均数，
如果输入没有达到window_size则输出所有数的平均值，达到后踢掉最早的输入输出最新的平均值。
例子：
For Window size: 2
MovingAverage m(2)
m.get_next(1) -> 1
m.get_next(2) -> 1.5
m.get_next(3) -> 2.5
m.get_next(4) -> 3.5
 */

class SOlu{
  class MovingAverage {
    public:
    MovingAverage(int size) : _size(size) {}

    double get_next(double number) {
      _nums.push_back(number);
      _sum += number;
      while(_nums.size() > _size) {
        _sum -= _nums.front();
        _nums.pop_front();
      }
      return _sum / _nums.size();
    }
    private:
    list<double> _nums;
    double _sum{ 0 };
    int _size{ 0 };
  };
}