/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there
is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function will only be called once for each test case.
 */

public class ReadNCharactersGivenRead4 {
  /* The read4 API is defined in the parent class Reader4.
        int read4(char[] buf); */

  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return The number of characters read
   */
  public int read(char[] buf, int n) {
    char[] buff = new char[4];
    int len = 0; // stop reading when len = n or end of file
    boolean eof = false;
    while (!eof && len < n) {
      int tmp = read4(buff);
      if (tmp < 4) eof = true;
      System.arraycopy(buff, 0, buf, len, Math.min(n - len, tmp));
      len += Math.min(n - len, tmp);
    }
    return len;
  }
}

