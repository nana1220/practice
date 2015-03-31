/*
How to serialize strings and pass it over the network and de-serialize the string?
The string may contain any possible character out of 256 valid characters.
How do you escape characters in a string?
 */

import java.lang.StringBuilder;
import java.util.*;

public class SerializeStringList {
  static String serialize(List<String> strs) {
    StringBuilder sb = new StringBuilder();
    for (String s : strs) {
      int len = s.length();
      sb.append(len);
      sb.append(":");
      sb.append(s);
    }
    return sb.toString();
  }

  static List<String> deserialize(String strs) {
    if (strs.length() == 0) return null;
    List<String> res = new ArrayList<String>();
    int index = 0;
    while (index < strs.length()) {
      int markerIdx = strs.indexOf(":", index);
      int num = Integer.valueOf(strs.substring(index, markerIdx));
      res.add(strs.substring(markerIdx + 1, markerIdx + num + 1));
      index = markerIdx + num + 1;
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(deserialize("3:del4:ding4:$%!@2::2").toString());
    System.out.println(serialize(deserialize("3:del4:ding4:$%!@2::2")));
  }
}
