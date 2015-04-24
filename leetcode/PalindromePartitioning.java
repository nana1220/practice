/*
 Given a string s, partition s such that every substring of the partition is a palindrome.
 Return all possible palindrome partitioning of s.
 For example, given s = "aab",
 Return
   [
     ["aa","b"],
     ["a","a","b"]
   ]
*/

// DFS
// We can cut or not cut at each character, thus there're 2^n combinations.
// Each combination calls check, which takes O(n)
// time: O(n*2^n); space: recursive stack
public class Solution {
  public ArrayList<ArrayList<String>> partition(String s) {
    ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    dfs(s, 0, new ArrayList<String>(), res);
    return res;
  }

  void dfs(String s, int idx, ArrayList<String> p, ArrayList<ArrayList<String>> res){
    if(idx==s.length()){
      res.add(new ArrayList<String>(p));
      return;
    }
    for(int i=idx+1; i<=s.length(); i++){
      String word = s.substring(idx, i);
      if(check(word)) {
        p.add(word);
        dfs(s, i, p, res);
        p.remove(p.size()-1);
      }
    }
  }
  // can make a lookup table for checking of palindrome of all strings, to reduce check time from O(n) to O(1)
  boolean check(String s){
    char[] c = s.toCharArray();
    int l=0;
    int r=c.length-1;
    while(l<r){
      if(c[l]!=c[r]) return false;
      l++;
      r--;
    }
    return true;
  }
}

// DP: bottom-up
// dp[i] -- all the valid partions in s.substring(0, i)
// We can also use a boolean matrix here to reduce palindrome checking time from O(n) to O(1)
// time: O(n*2^n); space: O(2^n)
public class Solution {
  public ArrayList<ArrayList<String>> partition(String s) {
    if (s==null || s.length()==0)
      return new ArrayList<ArrayList<String>>();
    // use map store dp, key is index like array int[] dp
    Map<Integer, ArrayList<ArrayList<String>>> dp = new HashMap<Integer, ArrayList<ArrayList<String>>>();
    int N = s.length();
    for (int i=0; i<=N; i++)
      dp.put(i, new ArrayList<ArrayList<String>>());
    dp.get(0).add(new ArrayList<String>());
    for (int i=1; i<=N; i++){
      for (int j=1; j<=i; j++){
        String sub = s.substring(j-1, i); // split string[0,i] to string[0,j] + string[j,i]
        if (isPalindrome(sub)){ // check string[j,i]
          for (ArrayList<String> list:dp.get(j-1)){ // get dp of string[0,j]
            ArrayList<String> r = new ArrayList<String>(list); // make a copy!!!
            r.add(sub); // add string[j,i]
            dp.get(i).add(r);
          }
        }
      }
    }
    return dp.get(N);
  }
  private boolean isPalindrome(String s){
    if (s==null || s.length()==0)
      return false;
    for (int i=0, j=s.length()-1; i<j; i++, j--){
      if (s.charAt(i)!=s.charAt(j))
        return false;
    }
    return true;
  }
}

// DP: top-down
// TODO: this code has bug, check CTCI
public class Solution {
  public ArrayList<ArrayList<String>> partition(String s) {
    HashMap<String, ArrayList<ArrayList<String>>> cache = new HashMap<String, ArrayList<ArrayList<String>>>();
    return dfs(s, cache);
  }
  ArrayList<ArrayList<String>> dfs(String s, HashMap<String, ArrayList<ArrayList<String>>> cache){
    if(cache.containsKey(s)) return new ArrayList<ArrayList<String>>(cache.get(s));
    ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    for(int i=1; i<=s.length(); i++){
      if(check(s.substring(0,i))){
        ArrayList<ArrayList<String>> partitions = dfs(s.substring(i), cache);
        if(!partitions.isEmpty()){
          for(ArrayList<String> p : partitions) p.add(0, s.substring(0,i));
        } else if (i ==s.length()){
          ArrayList<String> tmp = new ArrayList<String>();
          tmp.add(s.substring(0,i));
          partitions.add(tmp);
        }
        res.addAll(partitions);
      }
    }
    if(!res.isEmpty()) cache.put(s, new ArrayList<ArrayList<String>>(res));
    return res;
  }

  boolean check(String s){
    char[] c = s.toCharArray();
    int l=0;
    int r=c.length-1;
    while(l<r){
      if(c[l]!=c[r]) return false;
      l++;
      r--;
    }
    return true;
  }
}