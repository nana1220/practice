/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.
 */

// so many edge cases..
public class Solution {
  public List<String> fullJustify(String[] words, int L) {

    List<String> res = new ArrayList<String>();
    if(words.length==1) {
      int space = L - words[0].length();
      StringBuilder sb = new StringBuilder();
      sb.append(words[0]);
      int s=0;
      while(s <space) {
        sb.append(" ");
        s++;
      }
      res.add(sb.toString());
      return res;
    }
    int len= words.length;
    int i=0;
    while (i<len){
      int l =L;
      int numSpace =0;
      List<String> str = new ArrayList<String>();
      while(i < len && words[i].length()<l) {
        str.add(words[i]);
        l -= (words[i].length() +1);
        numSpace++;
        i++;
      }
      if(i< len && words[i].length() == l){
        str.add(words[i]);
        i++;
      } else {
        numSpace += l;
      }
      int spacePerWord;
      int extraSpace;
      if(str.size() >1){
        spacePerWord = numSpace / (str.size()-1);
        extraSpace = numSpace % (str.size()-1);
      } else{ // edge case, one word line
        spacePerWord = L-str.get(0).length();
        extraSpace=0;
      }
      StringBuilder sb = new StringBuilder();
      for (int k=0; k<str.size()-1; k++){
        sb.append(str.get(k));
        int space = 0;
        while(space < spacePerWord){
          sb.append(" ");
          space++;
        }
        if (extraSpace >0){
          sb.append(" ");
          extraSpace--;
        }
      }
      sb.append(str.get(str.size()-1));
      if (str.size()==1){ // line with only one word
        int s=0;
        while(s<spacePerWord){
          sb.append(" ");
          s++;
        }
      }
      res.add(sb.toString());
    }
    // handle last line
    String last = res.get(res.size()-1);
    String[] lastArr = last.split("\\s+");
    int l = 0;
    StringBuilder lastsb = new StringBuilder();
    for(String s : lastArr){
      l += s.length();
      lastsb.append(s);
      if(l<L){ // NOTE: check
        lastsb.append(" ");
        l++;
      }
    }
    while(l<L){
      lastsb.append(" ");
      l++;
    }
    res.set(res.size()-1, lastsb.toString());
    return res;
  }
}