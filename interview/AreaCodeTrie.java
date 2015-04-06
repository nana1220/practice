/*
+1 North America
...
+1950 Northern Cal
…
+44 UK
+4420 London
+447 UK Mobile
+44750 Vodafoned
…

and we have a phone number, for instance
+447507439854795
+44989045454

return where the number is from
 */

public class AreaCodeTrie {

  class NumberNode {
    NumberNode[] children;
    String country;

    NumberNode(String str) {
      children = new NumberNode[10]; // 0~9
      country = str;
      for (int i = 0; i < 10; i++) children[i] = null;
    }
  }

  class NumberTree {
    NumberNode root = new NumberNode("");

    void addNumber(String number, String country) {
      NumberNode curr = root;
      for (int i = 0; i < number.length(); i++) {
        int idx = number.charAt(i) - '0';
        if (curr.children[idx]==null){
          curr.children[idx]=new NumberNode("");
        }
        curr = curr.children[idx];
      }
      curr.country = country;
    }

    String getCountry(String number) {
      NumberNode curr = root;
      for (int i = 0; i < number.length(); i++) {
        int idx = number.charAt(i) - '0';
        if (curr.children[idx] == null) return null;
        curr = curr.children[idx];
      }
      return curr.country;
    }
  }
}