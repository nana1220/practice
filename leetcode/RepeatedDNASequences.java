/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
 */

// base converstion, 10 to 16: 除16取余数得最低1位，然后把商继续除得第2位，直到商等于0

// encode DNA sequence to base 4 integer
public class Solution {
  public List<String> findRepeatedDnaSequences(String s) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    map.put('A', 0);
    map.put('C',1);
    map.put('G',2);
    map.put('T',3);
    char[] c = s.toCharArray();
    HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
    List<String> res = new ArrayList<String>();
    int sum=0;
    for(int i=0; i<c.length; i++){
      // base 4 to base 10, tricky part is to keep least 20 digits
      // NOTE!!!!!!!!!!!!!: & 0xfffff means only keep least 20 digits, then sum always equal to string(i-9, i+1)
      sum = (4*sum + map.get(s.charAt(i))) & 0xfffff; // 10 digits base 4, equals to 20 digits base 2, that is, 5 digit base 16
      if(i<9) continue; // initialze sum to the value of fisrt 10 DNA
      Integer count = counts.get(sum);
      if(count==null) counts.put(sum,1);
      else if (count==1){ // if count ==2, ignore, since alread put in the result
        counts.put(sum,1+count);
        res.add(s.substring(i-9, i+1));
      }
    }
    return res;
  }
}