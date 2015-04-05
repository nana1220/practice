public class AnagramSubstring {
   /*
    * Given String a and b, check if there's a substring in b that is an anagram of a
    * if there exist, return the substring, otherwise, return ""
    * eg,
    * input: a= "abc", b = "abbca"
    * output: bca
    */

  // Sliding Window
  // Maintain two tables, count occurrences of need characters and find characters
  // Maintain a variable to hold the total number of characters we've found so far
  // when number of find characters exceeds, adjust left border.
  public String getAnagramSubstring(String a, String b) {
    if (a == null || b == null)
      return "";
    int M = a.length(), N = b.length();
    if (M > N)
      return "";
    int[] need = new int[256], find = new int[256];
    int i = 0, j = 0;
    for (; i < M; i++)
      need[(int) a.charAt(i)]++;
    i = 0;
    for (; i < N; i++) {
      int val = (int) b.charAt(i);
      if (find[val] < need[val])
        M--;
      find[val]++;
      if (find[val] > need[val]) {
        while (j <= i && b.charAt(j) != b.charAt(i)) {
          find[(int) b.charAt(j++)]--;
          M++;
        }
        find[val]--;
        j++;
      }
      if (M == 0)
        return b.substring(j, i + 1);
    }
    return "";
  }
}