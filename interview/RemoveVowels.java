import java.lang.String;
import java.lang.StringBuilder;
import java.lang.System;

public class RemoveVowels {

  public static void main(String[] args) {
    for (String arg : args) {
      System.out.println(removeVowels(arg));
    }
  }

  /*
   * use indexOf instead of contains, because char can not be passed to contains
   */
  public static String removeVowels(String str) {
    if (str == null) {
      return "";
    }
    String vowels = "aeiouAEIOU";
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      if (vowels.indexOf(str.charAt(i)) > -1) {
        continue;
      }
      res.append(str.charAt(i));
    }
    return res.toString();
  }
}