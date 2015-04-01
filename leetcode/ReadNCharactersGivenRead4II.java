/*
Read N Characters Given Read4 II - Call multiple times

The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is
only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
 */

public class ReadNCharactersGivenRead4II {
  /* The read4 API is defined in the parent class Reader4.
        int read4(char[] buf); */
  private char[] buffer = new char[4];
  private int bufsize = 0;
  private int offset = 0;

  public int read(char[] buf, int n) {
    int len = 0;
    boolean eof = false;
    while (!eof && len < n) {
      if (bufsize == 0) {
        bufsize = read4(buffer);
        if (bufsize < 4) {
          eof = true;
        }
      }
      int num = Math.min(n - len, bufsize);
      System.arraycopy(buffer, offset, buf, len, num);
      /*
       * if read from old buffer, offset = offset + num,
       * if the read make old buffer empty, offset == 4
       * so it's in fact (offset + num) % 4,
       * bufsize -= num
       *
       * if read from new buffer, offset = 0, after read offset += num, bufsize -= num
       */
      offset = (offset + num) % 4;
      bufsize -= num;
      len += num;
    }
    return len;
  }
}