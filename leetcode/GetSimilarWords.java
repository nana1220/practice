/*
给定一个word list 和一个target word，要求输出在word list 里跟target word的edit distance 相差不大于k的所有words。
 */

/*
这是Airbnb的电面题。直接用edit distance挨个遍历一遍也能做，但是如果word list很大，那重复计算会非常多，这时候可以用trie来优化。
下面举个例，假设字典为["cs", "ct", "cby"]，target word为"cat"，k=1。
首先建Trie:
     c
   / | \
  b  s  t
 /
y
从根节点开始搜索，遍历的单词分别为：c -> cb -> (cby) -> cs -> ct。
与普通的Edit distance动态规划算法一样，我们对每一个“路过”的单词记录一个DP table。那么所遍历的单词的DP table应该为(假设当前遍历单词，也就是下面代码中的path为”c”)：
          c a t
      [ 0 1 2 3 ] <- prev_dist
-> c  [ 1 0 1 2 ] <- cur_dist
   cb [ 2 1 1 2 ]
   cs [ 2 1 1 2 ]
   ct [ 2 1 1 1 ]
每一行的最后一位数字即为当前单词与target word的edit distance。显然，如果该值小于等于k，则加入到结果中。最终返回的结果只有一个单词，即ct。
注意，遍历到单词cb时，edit distance已经为2，且长度已经与cat相等，也就意味着该节点的子树中所包含的单词与target word的edit distance
无论如何都不可能小于等于k，因此直接从该子树返回。所以单词cby是没有被遍历到的。这也就是Trie做这题的便利之处，当字典较大时会明显提高效率。
 */

class Solu{
  class Trie {
    public:
    struct Node {
      Node(char val): value{val}{}
      ~Node() {
        for(int i = 0; i < 27; ++i) {
          if (children[i]) {
            delete children[i];
            children[i] = nullptr;
          }
        }
      }
      Node* get(char c, bool auto_create = true) {
        assert((c >= 'a' && c <= 'z') || c == '\0');
        int id = c == '\0' ? 26 : c - 'a';
        if (!children[id] && auto_create) {
          children[id] = new Node(c);
          children[id]->parent = this;
          num_children++;
        }
        return children[id];
      }
      bool end() {
        return get('\0',false);
      }
      int num_children { 0 };
      char value{ 0 };
      Node* parent{ nullptr };
      Node* children[27] { nullptr };
    };
    public:
    Trie() {
      _root = new Node(-1);
    }
    ~Trie() {
      if (_root) {
        delete _root;
        _root = nullptr;
      }
    }
    void add_string(const string& str) {
      auto node = _root;
      for (auto c : str) {
        node = node->get(c);
      }
      node->get('\0');
    }
    vector<string> get_similar(const string& s, int threshold) {
      vector<string> ret;
      function<void(Node*, string&, vector<int>&)> search = [&](Node* node,
          string& path, vector<int>& prev_dist) {
        if (node->end() && prev_dist.back() <= threshold)
          ret.push_back(path);

        for(int i = 0; i < 26; ++i) {
          if (!node->children[i]) continue;

          vector<int> cur_dist = prev_dist;
          cur_dist[0]++;

          char letter = 'a' + i;
          bool go = cur_dist[0] <= threshold;
          for (int len = 1; len <= s.size(); ++len) {

            int ins_cost = cur_dist[len - 1] + 1;
            int del_cost = prev_dist[len] + 1;
            int rep_cost = 0;

            if (s[len - 1] != letter)
              rep_cost = prev_dist[len - 1] + 1;
            else
              rep_cost = prev_dist[len - 1];

            cur_dist[len] = min(min(ins_cost, del_cost), rep_cost);

            if (cur_dist[len] <= threshold) go = true;
          }
          if (go) {
            path.push_back(letter);
            search(node->children[i], path, cur_dist);
            path.pop_back();
          }
        }
      };
      if(s.empty()) return ret;
      string p;
      vector<int> dist(s.size() + 1);
      iota(dist.begin(), dist.end(), 0);
      search(_root, p, dist);
      return ret;
    }
    private:
    Node* _root{ nullptr };
  };
}