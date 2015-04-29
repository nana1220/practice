/*
Abbreviation: apple can be abbreviated to 5, a4, 4e, a3e, …
If given a string and an abbreviation, return if the string matches abbr.
Assume the original string only contains alphabetic characters.
For example:
“internationalization”, “i5a11o1” -> true
 */

class solu{
  bool abbr_match(const string& str, const string& abbr) {
    int strid = 0, abbrid = 0;
    int number = 0;
    for(;; ++abbrid) {
      if(strid >= str.length()) break;

      char c = abbr[abbrid];
      if (c >= '0' && c <= '9') {
        number = number * 10 + (c - '0');
      } else {
        if(number != 0) {
          strid += number;
          number = 0;
        }
        if(c == '\0' || str[strid] != c) break;
        strid++;
      }
    }
    return strid == (int)str.length() && abbrid == (int)abbr.length();
  }
}