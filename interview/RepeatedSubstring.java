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


// https://hellosmallworld123.wordpress.com/2014/06/10/kmp-related/
class solu{
// S = “ABABAB” would be F = {0001234}
  public int [] failure(String s) {
    int [] f = new int[s.length() + 1];
    f[0] = f[1] = 0;
    int j = 0; // record  the last possible position to extend
    for(int i = 1; i < f.length - 1; i++) {
      if (s.charAt(i-1) == s.charAt(k)) {
        f[i+1] = k+1;
        k++;
      }
      else if (k != 0) {
        k = f[k];
        i--; // try next k with same i
      }
      else {
        f[i+1] = 0;
      }
    }
    return f;
  }

  public static String minConcat(String s) {
    int [] f = failure(s);
    int n = f[s.length()];
    String concast = s;
    while(n != 0) {
      if (n%(s.length() - n) == 0) { //n should be greater thatn s.length() - n if not, the statement will not be 0.
        concast = s.substring(0, s.length() - n);
      }
      n = f[n];
    }
    return concast;
  }
}