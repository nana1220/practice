package ch10scalabilityandmemorylimits;

/*
 * Given an input file with four billion non-negative integers, provide an algorithm
 * to generate an integer which is not contained in the file. Assume you have 1 GB of
 * memory available for this task.
 * FOLLOW UP
 * What if you have only 10 MB of memory? Assume that all the values are distinct and
 * we now have no more than one billion non-negative integers.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Exception;
import java.util.Scanner;
import java.util.BitSet;

public class GenerateInteger {

  /*
 * 4 billion = 4*2^30, 1GB = 2^30 bytes = 8*2^30 bits, so each integer can map to a bit
 * integer 2^32, 2^31 non-negative, need to use long for 4 billion non-negative integer
 */
  static int generateInteger(String filename) throws Exception {
    long numInteger = (long) Integer.MAX_VALUE + 1; // 2^32
    // Can also use BitSet: BitSet bitset = new BitSet(Integer.MAX_VALUE);
    byte[] mapping = new byte[(int) numInteger / 8]; // don't forget cast to int
    Scanner cin = new Scanner(new FileReader(filename));
    while (cin.hasNextInt()) {
      int number = cin.nextInt(); // bitset.set(number); set bit at specified index to be true
      // dont' forget type conversion when assign to byte
      mapping[number / 8] = (byte) (mapping[number / 8] | (1 << (number % 8)));
    }
    for (int i = 0; i < mapping.length; i++) {
      for (int j = 0; j < 8; j++) {
        if (((mapping[i] >> j) & 1) == 0) { // if (bitset.get(i)) return i;
          return i * 8 + j;
        }
      }
    }
    return -1; // return i * 8 + j: under if condition, so need another return
  }

  /*
   * follow up: two pass scan.
   *
   * For block searching
   * Partition integers into blocks, use an array of int to count number of integers for each block,
   * so array size = block number. e.g. if there are only 999 numbers in the range of 1000 ~ 1999
   * we know that there is one number missing in this range
   * 10MB ~= 8*2^20 bytes, 1 int = 4 bytes, so the size of array is at most 2*2^20, so the block
   * number <= 2*2^20, there are 2^31 non-negative integer, so range size for each block is at least
   * 2^31 / (2*2^20) ~= 2^10
   *
   * For search integer in the range, we need to fit range in the memory so range size <= 10MB (8*2^20 bytes)
   * so final range size between (2^10 bits, 2^26 bits)
   */
  static int generateInteger2(String filename) throws FileNotFoundException {
    int blockSize = 1 << 12; // 2^12
    int blockNum = 1 << 20; // 2^20
    int[] blockCounts = new int[blockNum];
    Scanner cin = new Scanner(new FileReader(filename));
    while (cin.hasNextInt()){
      ++blockCounts[cin.nextInt() / blockSize];
    }
    int blockIndex = -1;
    for (int i = 0; i < blockNum; i++) {
      if (blockCounts[i] < blockSize) {
        blockIndex = i;
        break;
      }
    }
    if (blockIndex == -1)
      return -1; // every block contains enough integer, no missing integer
    int start = blockIndex * blockSize;
    int end = (blockIndex + 1) * blockSize;
    cin = new Scanner(new FileReader(filename));
    BitSet mapping = new BitSet(blockSize); // use (0,blockSize) instead of (0,end) to save space
    while (cin.hasNextInt()) {
      int num = cin.nextInt();
      if (num > start && num <= end) {
        mapping.set(num - start);
      }
    }
    for (int i = 1; i < blockSize; i++) {
      if (!mapping.get(i))
        return i + start;
    }
    return -1;
  }
}