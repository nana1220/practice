/*
{ “face”, “ball”, “apple”, “art”, “ah” }
“htarfbp…”
根据下面的string去给上面list words排序。就是平常我们按abcd…排，这次按string里的letter顺序排
 */

class Solu{
  void str_sort(vector<string>& strs, const string& dict) {
    unordered_map<char, int> ranks;
    for(int i = 0; i < dict.size(); ++i) {
      ranks[dict[i]] = i;
    }
    sort(strs.begin(), strs.end(), [&ranks](const string& a, const string& b) {
      size_t len = std::min(a.length(), b.length());
      for(size_t i = 0; i < len; ++i) {
        auto r = ranks[a[i]] - ranks[b[i]];
        if(r < 0) return true;
        else if(r > 0) return false;
      }
      return a.length() < b.length();
    });
  }
}