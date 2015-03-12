import java.lang.Integer;
import java.lang.String;
import java.lang.System;

public class GreyCode {

  public static void main(String[] args) {
    Integer term1 = Integer.parseInt(args[0]);
    Integer term2 = Integer.parseInt(args[1]);
    System.out.println(greyCode((byte)term1.intValue(), (byte)term2.intValue()));
  }

  public static int greyCode(byte term1, byte term2) {
    byte res = (byte)(term1 ^ term2);
    for (int i = 0; i < 8; i++) {
      byte temp = (byte) (1 << i);
      if (temp == res) {
        return 1;
      }
    }
    return 0;
  }
}