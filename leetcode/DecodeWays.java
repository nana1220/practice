/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 */

// dfs: top-down, f(n) = f(n-1) + f(n-2)
// TLE, need to add HashMap<String, Integer> to store count value for substring
public class Solution {
  public int numDecodings(String s) {
    if(s.length()==0) return 0;
    return dfs(s,0);
  }
  int dfs(String s, int idx){
    if(idx== s.length()-1  || idx == s.length()) return 1; // NOTE!!: last skip can be 1 char or 2 char
    int count=0;
    int val =  Integer.parseInt(s.substring(idx,idx+1));
    if(val!=0) { //NOTE:  first char of substring could be zero, Or if(val==0) return 0; since 0* can not pass [10,26] either
      count += dfs(s,idx+1);
    }
    val = Integer.parseInt(s.substring(idx,idx+2));
    if (val>=10 && val <=26){
      count += dfs(s,idx+2);
    }
    return count;
  }
}

// dp: bottom-up, f(1) + f(2) = f(3)
public class Solution {
  public int numDecodings(String s) {
    if(s.length() ==0) return 0;
    int[] dp = new int[s.length()];
    int  val = Integer.parseInt(s.substring(0,1));
    dp[0]= val!=0 ? 1:0;
    if(s.length() <2) return dp[0]; // check
    val = Integer.parseInt(s.substring(0,2));
    int val2 =  Integer.parseInt(s.substring(1,2));
    if (val>=10 && val <=26) dp[1]=1;
    if (val2!=0) dp[1] += dp[0];// Note!!: if current value is 0, it cannot be skiped from 1 step
    for(int i=2; i<s.length();i++){
      val = Integer.parseInt(s.substring(i,i+1));
      if (val!=0) dp[i] += dp[i-1];
      val = Integer.parseInt(s.substring(i-1,i+1));
      if (val>=10 && val <=26) dp[i]+=dp[i-2];
    }
    return dp[s.length()-1];
  }
}