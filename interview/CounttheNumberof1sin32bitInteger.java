/*
Count the number of 1s in a 32-bit integer.
For example:
Input: 7
Output: 3
 */
class Solu {
  int count_ones(int x) {
    int count = 0;
    while (x != 0) {
      count += x & 1;
      x >>= 1;
    }
    return count;
  }

  int count(int x) {
    count = 0;
    while (x != 0) {
      count++;
      x = x & (x - 1);
    }
  }
  // G家要求你不可以用循环
  // Divide and conquer思路。先每2位为一组，组内高低位相加，每4位为一组，组内高低位相加，每8位为一组，组内高低位相加，每16位为一组，还是组内高低位相加。
  //
  int count_ones(int x) {
    x = ((x & 0b1010101010101010) >> 1) + (x & 0b0101010101010101);
    x = ((x & 0b1100110011001100) >> 2) + (x & 0b0011001100110011);
    x = ((x & 0b1111000011110000) >> 4) + (x & 0b0000111100001111);
    x = ((x & 0b1111111100000000) >> 8) + (x & 0b0000000011111111); // only for 16 bits int
    // (>> 16) + (), need one line more, higher 16 bits and lower 16 bits
    return x;
  }
  用十六进制简化一下：
  int count_ones(int x) {
    x = ((x & 0xAAAA) >> 1) + (x & 0x5555);
    x = ((x & 0xCCCC) >> 2) + (x & 0x3333);
    x = ((x & 0xF0F0) >> 4) + (x & 0x0F0F);
    x = ((x & 0xFF00) >> 8) + (x & 0x00FF);
    return x;
  }
}
