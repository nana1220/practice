/*
AlgorithmGuru has decided to work on UTF8 encoding schemes and to check if a string is valid UTF8 or not.
 Just read the below given description.

UTF-8 is a variable-length encoding of letters or runes. If the most significant bit of the first
 byte is 0, the letter is 1 byte long. Otherwise, its length is the number of leading 1’s in the first byte.
  If a letter is more than one byte long, all subsequent runes start with 10. Here’s a chart:

UTF-8 encoding scheme is described below:

0XXXXXXX = this is the entire rune
10XXXXXX = this is a continuation of the rune from the previous byte
110XXXXX = this is the start of a 2-byte rune.
1110XXXX = this is the start of a 3-byte rune.
11110XXX = this is the start of a 4-byte rune.
111110XX = this is the start of a 5-byte rune.
1111110X = this is the start of a 6-byte rune.
11111110 = this is the start of a 7-byte rune.
11111111 = this is the start of a 8-byte rune.

For example, a 3-byte rune would be 1110XXXX 10XXXXXX 10XXXXXX.

Write a function that decides whether a given byte array (or string) is valid UTF-8 encoded text.
 */



class UTF8 {
  bool valid_utf8(const vector<unsigned char>& data) {
    int size = 0;
    for(auto c : data) {
      if(size == 0) {
        if((c >> 5) == 0b110) size = 1;
        else if((c >> 4) == 0b1110) size = 2;
        else if((c >> 3) == 0b11110) size = 3;
        else if(c >> 7) return false;
      } else {
        if((c >> 6) != 0b10) return false;
        --size;
      }
    }
    return size == 0;
  }
}