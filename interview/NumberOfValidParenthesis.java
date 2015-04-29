/*
Cn is the nth Catalan number, C(2n,n)/(n+1), and gives the number of valid strings of length 2n that use only (). So
if we change all [] and {} into (), there would be Cn1+n2+n3 ways. Then there are C(n1+n2+n3,n1) ways to change n1 ()
 back to {}, and C(n2+n3,n3) ways to change the remaining () into []. Putting that all together, there are
  C(2n1+2n2+2n3,n1+n2+n3)C(n1+n2+n3,n1)C(n2+n3,n3)/(n1+n2+n3+1) ways.
 */

public class NumberOfValidParenthesis {

  public int getNumberOfValidParenthesis(int N){
    if (N==0)
      return 0;
    int[][] dp = new int[2*N+1][2*N+1];
    for (int i=1; i<=N; i++){
      for (int j=1; j<=N; j++){
        if (i-1>=j)
          dp[i][j] += dp[i-1][j];
        if (i>=j-1)
          dp[i][j] += dp[i][j-1];
      }
    }
  }
}

class slu{
  static private void Brackets(String output, int open, int close, int pairs) {
    if ((open == pairs) && (close == pairs) && output.length() == total * 2) {
      System.out.println(output);
    } else {
      if (open < pairs)
        Brackets(output + "(", open + 1, close, pairs);
      if (close < open)
        Brackets(output + ")", open, close + 1, pairs);
    }

  }
}