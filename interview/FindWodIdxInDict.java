/*
A method String dic(int i) returns the ith element in a dictionary. The words in the dictionary
are in order but the size of the dictionary is unknown. Write a method to return the index of a
word if it is in the dictionary.
 */

class solu{
  public int getIndex(String word){
// null case or empty string
    if ( word == null || word.isEmpty() )
      return -1;
    return getIndex(word,0,Integer.MAX_VALUE);
  }
  public int getIndex(String word, int start, int end){
    if (start > end)
      return -1;
    if (start == end) {
      if (dic(start).equals(word)) // found the index of word
        return start;
      else // the word is not in the dictionary
        return -1;
    }
    int mid = (start + end) / 2;
    String midStr = dic(mid);
    if ( midStr == null) // out of bound of the dictionary
      return getIndex(word, start, mid);
    else{
      int cmp = midStr.compareTo(word);
      if (cmp == 0) // found the index of word
        return mid;
      else if (cmp < 0)
// mid is smaller than word, pick the right half
        return getIndex(word, mid+1,end);
      else // mid is greater than word, pick the left half
        return getIndex(word,start,mid);
    }
  }
}