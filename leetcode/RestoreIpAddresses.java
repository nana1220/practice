/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

// DFS
public class Solution {
  public List<String> restoreIpAddresses(String s) {
    List<String> ip = new ArrayList<String>();
    List<String> res = new ArrayList<String>();
    restore(s,ip,0,res);
    return res;
  }
  void restore(String s, List<String> ip, int num, List<String> res){
    if(num==3){
      if(!isValid(s)) return;
      else{
        StringBuilder sb=new StringBuilder();
        for(String str : ip) sb.append(str);
        sb.append(s);
        res.add(sb.toString());
        return;
      }
    }
    int len = s.length();
    int i=1;
    while(i<=len && i<=3){ // double conditions
      String head = s.substring(0,i);
      if(isValid(head)) {
        ip.add(head+".");
        restore(s.substring(i), ip, num+1, res);
        ip.remove(ip.size()-1);
      }
      i++;
    }
  }
  boolean isValid(String s){
    if(s.length()==0 || s.length() >3) return false; // must guard for lenght >3, otherwise could overflow when parsed to Integer
    if(s.length()>1 && s.charAt(0) =='0') return false;
    int val =Integer.parseInt(s);
    if(0<=val&&val<=255) return true;
    return false;
  }
}