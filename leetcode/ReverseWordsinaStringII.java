/*
Question:
Similar to Question [6. Reverse Words in a String], but with the following constraints:
“The input string does not contain leading or trailing spaces and the words are always
separated by a single space.”
Could you do it in-place without allocating extra space?
 */

/*
思路就是两步走，第一步就是将整个字符串翻转。然后从头逐步扫描，将每个遇到单词再翻转过来。

1）如果是Java，应该跟面试官指出String是immutable，所以需要用char array来做。
2）follow-up问题：k-step reverse。也就是在第二部翻转的时候，把k个单词看作一个长单词，进行翻转。
 */
public class Solution {
  public void reverseWords(char[] s) {
    reverse(s, 0, s.length-1);
    for (int i=0, j=0; j<=s.length; j++) {
      if (j==s.length || s[j]==' ') { // note!!!: range checking condition goes first
        reverse(s, i, j-1);
        i =  j + 1;
      }
    }
  }

  private void reverse(char [] s, int begin, int end) {
    while(begin < end){
      char temp = s[begin];
      s[begin] = s[end];
      s[end] = temp;
      begin++;
      end--;
    }
  }
}