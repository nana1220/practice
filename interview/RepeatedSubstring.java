/*
Amazon
有一种String,是把一个更短的String重复n次而构成的，那个更短的String长度至少为2，输入一个String写代码返回T或者F
例子：
"abcabcabc" true 因为它把abc重复3次构成
"bcdbcdbcde" false 最后一个是bcde
"abcdabcd" true 因为它是abcd重复2次构成
"xyz" false 因为它不是某一个String重复
"aaaaaaaaaa" false 重复的短String长度应至少为2（这里不能看做aa重复5次)

要求算法复杂度为O(n)
 */

// kmp
class Solu{
  bool is_repeat(const string& s) {
    int len = (int)s.length();
    vector<int> prefix(len, 0);

    for (int i = 1, k = 0; i < len; ++i) {
      while (k != 0 && s[i] != s[k]) k = prefix[k - 1];
      if (s[i] == s[k]) ++k;
      prefix[i] = k;
    }

    int d = len - prefix[len - 1], id = d - 1, cur_ret = 0;
    if (d < 2) return false;
    bool ret = false;
    while (true) {
      if (id == len - 1) return ret;
      else if (id >= len) return false;

      if (prefix[id] != cur_ret) return false;
      else {
        id += d;
        cur_ret += d;
      }
      ret = true;
    }
    return ret;
  }
}