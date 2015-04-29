/*
Given a string S, find the longest palindromic substring in S.
 */

// dp 空间O(n^2)，时间也是O(n^2)
class Solu{
  string longest_palin(string s) {
    int n = (int)s.length(), begin = 0;
    int max_len = 1;
    vector<vector<bool>> table(n, vector<bool>(n, false));
    for (int i = 0; i < n; i++)
      table[i][i] = true;
    for (int i = 0; i < n-1; i++) {
      if (s[i] == s[i+1]) {
        table[i][i+1] = true;
        begin = i;
        max_len = 2;
      }
    }
    for (int len = 3; len <= n; len++) {
      for (int i = 0; i < n-len+1; i++) {
        int j = i+len-1;
        if (s[i] == s[j] && table[i+1][j-1]) {
          table[i][j] = true;
          begin = i;
          max_len = len;
        }
      }
    }
    return s.substr(begin, max_len);
  }
}

// 可以直接用两重循环做。因为回文字符串是从中间对称的，所以我们可以从中间展开判断。一个长度为n的string只需要展开判断2n-1次就好了。
class SOlu{
  string longest_palin(string s) {
    int n = (int)s.length();
    if (n == 0) return "";
    pair<int,int> longest = {0,1}; // means start from 0, length = 1

    auto expand = [&](int c1, int c2) -> pair<int,int>{
      int l = c1, r = c2;
      while (l >= 0 && r < s.length() && s[l] == s[r]) {
        l--;
        r++;
      }
      return {l+1, r-l-1};
    };
    for (int i = 0; i < n-1; i++) {
      auto p1 = expand(i, i);
      if (p1.second > longest.second)
        longest = p1;

      auto p2 = expand(i, i+1);
      if (p2.second > longest.second)
        longest = p2;
    }
    return s.substr(longest.first, longest.second);
  }
}