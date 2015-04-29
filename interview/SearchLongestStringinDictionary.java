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
}