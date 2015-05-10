/*

 */

public class UrlShorteningService {
  /*
   * routing prefix to indicate a short URL avoids URL encoding issues
   * Identify and route short URL requests
   * ID encoding
   * The simplest way reduce the length of a resource ID is to convert it to a higher base, with a larger
   * set of characters to represent them. e.g. base 26 (a-z)
   * http://last.fm/    +        t          d4Fa
   * (Root Domain)   (route)   (type)   (encoded id)
   *
   *
How to convert the ID to a shortened URL
Think of an alphabet we want to use. In your case that's [a-zA-Z0-9]. It contains 62 letters.
Take an auto-generated, unique numerical key (the auto-incremented id of a MySQL table for example).
For this example I will use 12510 (125 with a base of 10).
Now you have to convert 12510 to X62 (base 62).
12510 = 2×621 + 1×620 = [2,1]
This requires use of integer division and modulo

How to resolve a shortened URL to the initial ID
The reverse is even easier. You just do a reverse lookup in your alphabet.
e9a62 will be resolved to "4th, 61st, and 0th letter in alphabet".
e9a62 = [4,61,0] = 4×622 + 61×621 + 0×620 = 1915810
Now find your database-record with WHERE id = 19158 and do the redirect.
   */
}


public class UrlShortener {
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int    BASE     = ALPHABET.length();

  public static String encode(int num) {
    StringBuilder sb = new StringBuilder();

    while ( num > 0 ) {
      sb.append( ALPHABET.charAt( num % BASE ) );
      num /= BASE;
    }

    return sb.reverse().toString();
  }

  public static int decode(String str) {
    int num = 0;

    for ( int i = 0, len = str.length(); i < len; i++ ) {
      num = num * BASE + ALPHABET.indexOf( str.charAt(i) );
    }

    return num;
  }
}