/*
Cn is the nth Catalan number, C(2n,n)/(n+1), and gives the number of valid strings of length 2n that use only (). So
if we change all [] and {} into (), there would be Cn1+n2+n3 ways. Then there are C(n1+n2+n3,n1) ways to change n1 ()
 back to {}, and C(n2+n3,n3) ways to change the remaining () into []. Putting that all together, there are
  C(2n1+2n2+2n3,n1+n2+n3)C(n1+n2+n3,n1)C(n2+n3,n3)/(n1+n2+n3+1) ways.
 */


// This is mainly an application of Catalan Numbers. Total possible valid expressions for input n is n/2’th Catalan Number if n is even and 0 if n is odd.
// O(n2)
class solu{
  // A dynamic programming based function to find nth
// Catalan number
  unsigned long int catalanDP(unsigned int n)
  {
    // Table to store results of subproblems
    unsigned long int catalan[n+1];

    // Initialize first two values in table
    catalan[0] = catalan[1] = 1;

    // Fill entries in catalan[] using recursive formula
    for (int i=2; i<=n; i++)
    {
      catalan[i] = 0;
      for (int j=0; j<i; j++)
        catalan[i] += catalan[j] * catalan[i-j-1];
    }

    // Return last entry
    return catalan[n];
  }

}

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