/*
写一个程序，找出 5^1234566789893943的从底位开始的1000位数字。
 */

class solu{
  string get_pow_digits(int m, int n, int k) {
    if(k == 0 || m > 10  || n < 0) return "";
    string ret(k,'0');
    ret.back() = '1';

    auto multiply = [](string& num1, int num2) {
      if(num1.empty()) num1 = to_string(num2);
      int carry = 0;
      for(int i = (int)num1.size()-1; i >= 0; --i) {
        int val = (num1[i] - '0') * num2 + carry;
        num1[i] = (val % 10) + '0';
        carry = val / 10;
      }
    };
    for(int i = 1; i <= n; ++i) multiply(ret, m);
    return ret;
  }
}