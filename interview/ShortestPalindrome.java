/*
Given a string S, you are allowed to convert it to a palindrome by adding 0 or more characters in front of it.
Find the length of the shortest palindrome that you can create from S by applying the above transformation.

Examples:
cca -> 1
aba -> 0
acb -> 2
abcb -> 3
abb -> 2
abcdefg -> 6


本题有多种O(n)的做法，下面给出比较容易实现的KMP解法。
实际上本题稍微转换一下就是KMP的prefix数组的构建算法。

先快速复习下KMP算法。通俗来说，KMP的prefix数组的每一位定义为pattern的当前最长相同后缀前缀长度。
比如pattern为abab时，
Prefix数组为{0, 0, 1, 2}。
第1位固定为0，第2位时当前子串为ab，没有相同的前缀和后缀，所以设为0。
第3位时当前子串为aba，存在前缀和后缀都为a，并且长度最大，所以该位设为1。第4位时当前子串为abab，最长相同的前缀和后缀为ab，所以该位为2。

如何将这个prefix数组应用在本题中？现在拿第一个例子cca来举例，因为涉及到回文，我们需要把这个字符串扩展一个他的镜像字符串，
并且中间用一个一定不会出现在输入字符串中的字符为分隔（这里可以用#符号，实际代码里我用的'\0'。
这样我们可以得到新的字符串：cca#acc
对这个字符串求prefix数组，结果为：
{0, 1, 0, 0, 0, 1, 2}
最后一位为2，意思为镜像字符串和原字符串的最长相同后缀前缀长度为2，而原字符串的长度为3，所以我们最少需要补1个字符，就可以构造出回文字符串。所以结果返回3 - 2 = 1。
 */

class Solu{
  int shortest_palindrome(string s) {
    int n = (int)s.size();
    vector<int> p(2 * n + 1);
    s += '\0' + string(s.rbegin(), s.rend());

    p[0] = 0;
    for (int i = 1; i <= 2 * n; i++) {
      int j = p[i - 1];
      while (j > 0 && s[j] != s[i]) j = p[j - 1];
      if(s[i] == s[j]) ++j;
      p[i] = j;
    }
    return n - p[2 * n];
  }
}