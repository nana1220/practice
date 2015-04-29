/*
String只有可能有A、B、C三个字母组成，如果任何紧邻的三个字母各不相同，那就不是“valid string”。求长度为n的valid string有多少个。
比如： ABBBCA不是valid，ACCBCCA是valid
 */

/*
跟Fence Painter那题是一个做法，把串尾分为相同和不同两种情况。
假设dp_same[i]为长度=i，最后两位相同的valid string个数，dp_dif[i]为长度=i，最后两位不同的valid string个数。转移方程如下：
dp_same[i] = dp_same[i-1] + dp_dif[i-1]
dp_dif[i] = dp_same[i-1] * 2 + dp_dif[i-1]
初始状态：
dp_same[0] = 3
dp_dif[0] = 0
因为当前状态只跟上一个状态有关，所以不需要额外内存
 */
// dpSame[2]=9, dpDiff[2]=12
class Solu{
  int num_valid_strings_dp(int n) {
    int last_same = 3, last_dif = 0;
    for(int i = 1; i < n; ++i) {
      auto new_same = last_same + last_dif;
      last_dif = last_same * 2 + last_dif;
      last_same = new_same;
    }
    return last_same + last_dif;
  }
}