/*
Please design and implement a class:
This class will be given a list of words (such as might be tokenized from a paragraph of text), and will provide
 a method that takes two words and returns the shortest distance (in words) between those two words in the provided text.
Example:

WordDistanceFinder finder({"the", "quick", "brown", "fox", "quick"});
assert(finder.distance("fox", "the") == 3);
assert(finder.distance("quick", "fox") == 1);
 */

class Solu{
  class WordDistanceFinder {
    public:
    WordDistanceFinder(vector<string>&& words) : _words(move(words)){}

    int distance(const string& a, const string& b) {
      int ret = INT_MAX, pos_a = -1, pos_b = -1;
      for(int i = 0; i < _words.size(); ++i) {
        if(a == _words[i]) {
          pos_a = i;
          if(pos_b >= 0) ret = min(ret, abs(pos_a - pos_b));
        } else if(b == _words[i]) {
          pos_b = i;
          if(pos_a >= 0) ret = min(ret, abs(pos_a - pos_b));
        }
      }
      return (pos_a < 0 || pos_b < 0) ? -1 : ret;
    }
    private:
    vector<string> _words;
  };
}

/*
Follow up:
如果这个distance函数会被频繁调用，如何通过预处理来优化。


可以用hash table把每个单词在vector中的所有索引存起来。这样调用distance的时候问题就转化成：

从两个已排序的数组中分别选一个数字，求绝对值最小值。
这个问题也有可以讨论的地方，假设两个数组的大小分别为M和N，如果M和N大致相同，可以直接用类似merge sorted array的方法从头开始一个一个比下去。最坏的时间复杂度为O(M+N)。
如果两个数组大小相差非常大，假设M>>N，那么可以遍历N，然后用binary search找出最接近N[i]的值，复杂度为O(NlogM)。
 */