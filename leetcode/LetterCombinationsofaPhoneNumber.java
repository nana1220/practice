/*
Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.


Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsofaPhoneNumber {
  public List<String> letterCombinations(String digits) {
    List<String> list = new ArrayList<String>();
    if (digits.length() == 0) return list;
    HashMap<Character, String> map = new HashMap<Character, String>();
    map.put('1', "");
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    map.put('6', "mno");
    map.put('7', "pqrs");
    map.put('8', "tuv");
    map.put('9', "wxyz");
    map.put('0', "");
    combine(digits, map, list, "");
    return list;
  }

  /*
   * DFS, length of input string is the depth of the tree
   * time O(n)
   */
  void combine(String str, HashMap<Character, String> map, List<String> list, String res) {
    if (str.length() == 0) {
      list.add(res);
      return; // Dont forget, void method recursive base case, add return statement
    }
    char digit = str.charAt(0);
    for (char ch : map.get(digit).toCharArray()) {
      // can use StringBuilder, and make it like a stack, push: sb.append(ch);
      combine(str.substring(1), map, list, res + ch); // here res + ch is string copy,
      // when done backtracking, pop: sb.deleteCharAt(sb.length() - 1);
    }
  }
}