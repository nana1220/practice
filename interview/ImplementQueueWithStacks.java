class Solu{
  template <typename T>
  class Queue {
    public:
    void push(const T& val) {
      _in.push(val);
    }
    T pop() {
      if(_out.empty()) {
        while(!_in.empty()) {
          _out.push(_in.top());
          _in.pop();
        }
      }
      auto val = _out.top();
      _out.pop();
      return val;
    }
    private:
    stack<T> _in;
    stack<T> _out;
  };
}