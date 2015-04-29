public class MaxProductOfTwoDifferentWords {
   /*
    * Given a dictionary of words, how can we efficiently find a pair words s.t. they don't have
    * characters in common and product of their length is maximum?
    * For example:
    * S = { "abc", "cde", "f", "efh"}.
    * Here the pair satisfying the property mentioned in problem will be ("abc", "efh").
    */

  // If we want to get the max product of two words share common characters, we can use
  // a map<Character, List<Word>>, eg, c -> ["abc", "cde"]
  // time: O(n*m*lgn)

  // Sol1
  // The naive solution is to get all pairs of words (n^2, n is the number of words),
  // and for each pair, we have to check whether they have common characters (a, a is
  // the size of alphabet)
  // time: O(a*n^2); space: O(1)


  // Sol2
  // Assume the alphabet is small enough (eg, lowercase English characters, 26)
  // One optimization is to use bitmap to represent the occurring characters in the word
  // eg, abba -> 11, a->1, ac -> 101
  // to check whether two words have common characters, we only need to AND two words' bitmap
  // time: preprocess O(n*m), query (n^2); space: O(n)
//  把这个word转换成int (比如a->bit0, b->bit1, ...), 如果超过32个字符，可以用int64. 并且对每一个int，记录它的最大长度。比如aaab和ab都是0x03，但是长度不一样。
//  可以用std::map<int, int>，前一个int代表word转换后的值，后一个int代表这些有共同字符的words的最大长度。设word总数有N，则空间需要为O(N)。
//
//  然后对每一个int，跟其他的int进行bits AND操作，如果没有重复的字符，则结果是0，同时得到它们的长度和，跟最大长度和比较。
//  这样时间复杂度是O(n^2)
//      [还可以对pair按照它们总长度进行排序，然后先处理最长的pair，如果有一pair结果为0，则不需要再处理剩下的pair]


  // Sol3
  // Further optimization:
  // The key point is that we have 2^a bitmaps, we can get the longest word represented by each bitmap
  // http://www.quora.com/Algorithms/Given-a-dictionary-of-words-how-can-we-efficiently-find-a-pair-words-s-t-they-dont-have-characters-in-common-and-sum-of-their-length-is-maximum

  // Implementation of Sol2. Assume we only have lowercase characters, that is [a-z]
  public int getMaxProduct(String[] ss) {
    int max = 0;
    for (int i = 0; i < ss.length; i++) {
      for (int j = i + 1; j < ss.length; j++) {
        if (!shareCommon(ss[i], ss[j]))
          max = Math.max(ss[i].length() * ss[j].length(), max);
      }
    }
    return max;
  }

  private int getBitmap(String s) {
    int bitmap = 0;
    for (int i = 0; i < s.length(); i++)
      bitmap |= (1 << (s.charAt(i) - 'a'));
    return bitmap;
  }

  private boolean shareCommon(String a, String b) {
    return (getBitmap(a) & getBitmap(b)) > 0;
  }
}