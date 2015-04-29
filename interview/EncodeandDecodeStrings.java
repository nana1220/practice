/*
请设计一个encode和decode字符串的算法，并实现以下两个function：


string encode_strings(const vector<string>& strs);
vector<string> decode_strings(const string& str);

 */

class Solu{
  string encode_strings(const vector<string>& strs) {
    string ret;
    for(auto& s : strs)
    ret += to_string(s.size()) + "#" + s;
    return ret;
  }
  vector<string> decode_strings(const string& str) {
    vector<string> ret;
    size_t pos = 0;
    while(pos < str.length()) {
      auto p = str.find("#", pos);
      if(p == string::npos) return ret;
      int size = atoi(str.substr(pos, p - pos).c_str());
      ret.push_back(str.substr(p + 1, size));
      pos = p + 1 + size;
    }
    return ret;
  }

}