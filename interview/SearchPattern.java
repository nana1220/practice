/*
给一个dictionary, 再给一个set of coding string （g5, goo3, goog2, go2le………). return all string from dictionary that
can be matched with the coding string. 要求尽量减少dictionary look up 次数。
 */

// 因为给了一个字典，可能包含单词非常多，可以用trie来预处理。trie的代码请见Get Similar Words中的实现。下面只给出搜索函数。

class SOlu{
  vector<string> search_num_pattern(const string& str) {
    vector<string> ret;

    function<void(int,int,string,Node*)> search = [&](int id, int num, string cur, Node* node){
      if(!node) return;
      if (id > str.length()) {
        if (node->value == '\0') {
          cur.resize(cur.length() - 1);
          ret.push_back(cur);
        }
        return;
      }
      if (num > 0) {
        for(int i = 0; i < 26; ++i) {
          if (node->children[i])
            search(id, num-1, cur + node->children[i]->value, node->children[i]);
        }
      } else {
        char c = id < str.length() ? str[id] : '\0';
        if (c >= '0' && c <= '9') {
          int over_id = id + 1;
          for(; over_id < str.length() && str[over_id] >= '0' && str[over_id] <= '9'; ++over_id);
          int cur_num = atoi(str.substr(id, over_id - id).c_str()) - 1;

          for(int i = 0; i < 26; ++i) {
            if (node->children[i])
              search(over_id, cur_num, cur + node->children[i]->value, node->children[i]);
          }
        } else {
          search(id+1, 0, cur + c, node->get(str[id], false));
        }
      }
    };
    search(0, 0, "", _root);
    return ret;
  }
}