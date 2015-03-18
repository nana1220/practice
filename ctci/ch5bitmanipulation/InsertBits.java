package ch5bitmanipulation;

public class InsertBits {

  static int insertBits1(int N, int M, int i, int j) {
    int mask = ~(1 << j - 1) | (1 << i - 1); // set i to j all 0s, other all 1s
    N &= mask; // set i to j all 0s, other bits unchanged
    M = M << i; // set M to i to j, other bits all 0s
    return M | N;
  }

  static int insertBits2(int n, int m, int i, int j) {
    // Validation
    if (i >= 32 || j < i) {
      return 0;
    }

    int allOnes = ~0; // allOnes = 11111111

    int left = allOnes << (j + 1); // 1s through position j, then 0s. left = 11100000
    int right = ((1 << i) - 1); // 1’s after position i.  right = 00000011
    int mask = left | right; // All 1s, except for 0s between i and j. mask = 11100011

		/* Clear i through j, then put m in there */
    int n_cleared = n & mask; // Clear bits j through i.
    int m_shifted = m << i; // Move m into correct position.

		/* OR them, and we're done! */
    return n_cleared | m_shifted;
  }

  public static void main(String[] args) {
    System.out.println("N: " + Integer.toBinaryString(100));
    System.out.println("M: " + Integer.toBinaryString(3));
    System.out.println(Integer.toBinaryString(insertBits1(100, 3, 3, 4)));
    System.out.println(Integer.toBinaryString(insertBits2(100, 3, 3, 4)));
  }
}