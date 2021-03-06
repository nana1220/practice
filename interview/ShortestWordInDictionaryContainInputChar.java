import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ShortestDict {

  private List<String> wordList;
  private List<Integer> maskList;

  public ShortestDict(String[] wordArr) {
    wordList = new ArrayList<String>();
    maskList = new ArrayList<Integer>();

    Comparator<String> cmp = new Comparator<String>() {
      public int compare(String s1, String s2) {
        return s1.length() - s2.length();
      }
    };

    Arrays.sort(wordArr, cmp); // sort dictionary by word length
    for (String word : wordArr) {
      wordList.add(word);
      maskList.add(getMask(word));
    }
  }
  public String getShortest(String inputWord) {
    int inputMask = getMask(inputWord);
    for (int i = 0; i < maskList.size(); i++) {
      if (inputMask == (inputMask & maskList.get(i))) {
        return wordList.get(i);
      }
    }
    return "";
  }

  public static int getMask(String word) {
    int mask = 0;
    word = word.toLowerCase(); // remember
    for (char c : word.toCharArray()) {
      if ('a' <= c && c <= 'z') {
        mask |= (1 << (c - 'a')); // Note: convert string to int marker, this method cannot handle dup
      }
    }
    return mask;
  }

  public static void main(String[] args) {
    String[] words = new String[]{"so99ort", "so1rt", "skype", "so11rt", "apppples"};
    ShortestDict d = new ShortestDict(words);

    System.out.println(d.getShortest("SR 456 T"));

  }

}