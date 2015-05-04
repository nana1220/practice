/*
给一个dictionary, 一个string,找出dict 里能全部用string里的letter 表示的所有最长的词。
For example:
字典包含如下单词：
abcde, abc, abbbc, abbbccca, abbbcccabbcx
给string = "abc"，最长单词应为"abbbccca"


也是给一个字典，所以建trie比较合适。搜索函数如下（trie代码请参考Get Similar Words中的实现）
本题还有一种变体，基本上是差不多的：
given Set set, List chars, return Set which has longest be covered by the List
e.g. dgg cat naioe lot.
1st case: dcnlggatio -> return [dgg,cat,lot]
2st case: dcnlggatioe -> return [naioe]
 */

class Solu{


  string search_longest(const string& str) {
    string ret;
    bool count[256] = {false};
    for(auto c : str) count[c] = true;

    function<void(string,Node*)> search = [&](string cur, Node* node){
      if (!node) return;
      if (node->end()) {
        if(cur.length() > ret.size())
          ret = cur;
      }
      for(int i = 0; i < 26; ++i) {
        if(!node->children[i] || !count[node->children[i]->value]) continue;
        search(cur + node->children[i]->value, node->children[i]);
      }
    };
    search("", _root);
    return ret;
  }

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

}