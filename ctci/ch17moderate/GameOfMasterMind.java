/*
When you guess the correct color for the correct slot, you get a "hit." If you guess
a color that exists but is in the wrong slot, you get a "pseudo-hit." Note that a slot
that is a hit can never count as a pseudo-hit.
For example, if the actual solution is RGBY and you guess GGRR, you have one
hit and one pseudo-hit.
Write a method that, given a guess and a solution, returns the number of hits
and pseudo-hits.
*/

package ch17moderate;

public class GameOfMasterMind {
  /*
   * Or first pass compute hits and put wrong color into a frequency array, each position
   * in the array represents a color defined though a function that maps color to number,
   * and the value represents frequency
   * Then in second pass, if colors of guess and answer are not equal check if the color in the
   * frequency array.
   * For a easy problem like this one, improve by writing clean code,
   * write a Result class hold the result and a code fuction encode color to number
   */
  static int[] getHits(int[] answer, int[] guess) {
    int hit = 0;
    int pHit = 0;
    for (int i = 0; i < 4; i++) {
      if (guess[i] == answer[i]) {
        hit++;
        guess[i] = -1;
        answer[i] = -1;
      }
    }
    for (int i = 0; i < 4; i++) {
      if (guess[i] != -1) {
        int color = guess[i];
        for (int j = 0; j < 4; j++) {
          if (color == answer[j]) pHit++;
        }
      }
    }
    return new int[]{hit, pHit};
  }

  public int code(char c) {
    switch (c) {
      case 'B':
        return 0;
      case 'G':
        return 1;
      case 'R':
        return 2;
      case 'Y':
        return 3;
      default:
        return -1;
    }
  }
}
