/*
给一个Quack的类，里面有三个方法：
pop(): 随机从头或者尾扔出一个元素；
peek(): 随机看头或者尾的一个元素，peek()之后pop()的话一定会pop()出peek()的那个元素；
push(): 向尾部插入一个元素

问题是：给一个排序好的Quack,怎么把里面的元素原封不动的放到一个Array里面。
Follow up：如果quack里面有重复的元素，怎么处理。
*/


/*
对于不重复元素的情况，用queue存小的数，stack存大的数，先pop()一个数，再peek一下，比较这两个数，如果pop的大，就代表肯定是quack
的尾巴，反之肯定是头，然后插入queue或者stack就行了。这里假设quack有empty方法。
*/

class SOlu{
  vector<int> recover_quack(quack& qk) {
    queue<int> q;
    stack<int> s;

    while(!qk.empty()) {
      int num1 = qk.pop();
      if(qk.empty()) {
        q.push(num1);
        break;
      }
      int num2 = qk.peek();
      if(num1 < num2) q.push(num1);
      else s.push(num1);
    }
    vector<int> ret;
    while(!q.empty()) {
      ret.push_back(q.front());
      q.pop();
    }
    while(!s.empty()) {
      ret.push_back(s.top());
      s.pop();
    }
    return ret;
  }

}
/*
对于有重复元素的情况，算法跟上面差不多，但是当遇到重复的时候，可以用一个hash_map记下来这个count，最后结算的时候补上去即可。
 */
class Solu{
  vector<int> recover_quack(quack& qk) {
    queue<int> q;
    stack<int> s;
    unordered_map<int,int> rep;

    while(!qk.empty()) {
      int num1 = qk.pop();
      if(qk.empty()) {
        q.push(num1);
        break;
      }
      int num2 = qk.peek();
      if(num1 < num2) q.push(num1);
      else if(num1 > num2) s.push(num1);
      else {
        ++rep[num1];
      }
    }
    vector<int> ret;
    while(!q.empty()) {
      int num = q.front();
      ret.push_back(num);
      int count = rep[num];
      while(count-- > 0) ret.push_back(num);
      rep[num] = 0;
      q.pop();
    }
    while(!s.empty()) {
      int num = s.top();
      ret.push_back(num);
      int count = rep[num];
      while(count-- > 1) ret.push_back(num);
      rep[num] = 0;
      s.pop();
    }
    return ret;
  }
}

class quack {
  public:
  quack(const vector<int>& init) {
    for(auto i : init) {
      _data.push_back(i);
    }
  }

  bool empty() {
    return _data.empty();
  }

  int pop() {
    int ret = 0;
    int ran = _last_peek == -1 ? rand() % 2 : _last_peek;
    if(ran == 0) {
      ret = _data.front();
      _data.pop_front();
    } else {
      ret = _data.back();
      _data.pop_back();
    }
    _last_peek = -1;
    return ret;
  }

  void push(int num) {
    _data.push_back(num);
  }

  int peek() {
    int ret = 0;
    if(rand() % 2 == 0) {
      ret = _data.front();
      _last_peek = 0;
    } else {
      ret = _data.back();
      _last_peek = 1;
    }
    return ret;
  }
  private:
  deque<int> _data;
  int _last_peek{ -1 }; // store 0(front) 1(end) for last peek, if -1 means no last peek
};
