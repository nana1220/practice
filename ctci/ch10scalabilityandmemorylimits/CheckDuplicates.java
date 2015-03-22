package ch10scalabilityandmemorylimits;

/*
 * You have an array with all the numbers from 1 to N, where N is at most 32,000. The array may
 * have duplicate entries and you do not know what N is. With only 4 KB of memory
 * available, how would you print all duplicate elements in the array?
 */

/*
 * 4 KB means we can address up to 8*4*2^10 bits which is bigger than 32000.
 * So create a bit vector with 32000 bits, each bit maps to one integer.
 */
class CheckDuplicates {
  public void checkDuplicates(int[] arr) {
    BitSet mappting = new BitSet(32000);
    for (int i = 0; i < A.length; i++) {
      int num = arr[i];
      if (mappting.get(num - 1)) // BitSet index starts from 0, so number 1 maps to index 0
        System.out.println(num);
      else
        mappting.set(num - 1);
    }
  }

  // implement BitSet
  public class BitSet {
    int[] bitset; // store int[]

    public BitSet(int size) {
      bitset = new int[size >> 5]; // divide by 32
    }

    boolean get(int pos) {
      int wordNum = pos >> 5;
      int bitNum = (pos & 0x1F); // mod 32
      return (bitset[wordNum] & (1 << bitNum)) != 0;
    }

    void set(int pos) {
      int wordNum = pos >> 5;
      int bitNum = (pos & 0x1F);
      bitset[wordNum] |= 1 << bitNum;
    }
  }
}