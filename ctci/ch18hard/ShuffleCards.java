/*
Write a method to shuffle a deck of cards. It must be a perfect shuffle—in other
words, each of the 52! permutations of the deck has to be equally likely. Assume that
you are given a random number generator which is perfect.
 */

package ch18hard;

/*
 * suppose we had a method shuffle() that worked on n - 1 elements. Could we use this to shuffle
 * n elements? Yes, so this is a recursive problem. We would first shuffle the first n - 1 elements. Then, we
 * would take the nth element and randomly swap it with an element in the array
 */

import java.util.Random;

public class ShuffleCards {

  /*
   * recursive
   */
  static void shuffle(int[] cards, int index) {
    if (index == 0) return;
    shuffle(cards, index - 1);
    int pos = new Random().nextInt(index + 1); // [0, index], if pos = index then no swap
    int temp = cards[pos];
    cards[pos] = cards[index];
    cards[index] = temp;
  }

  static void shuffle(int[] cards) {
    shuffle(cards, cards.length - 1);
  }

  /*
   * iterative
   */
  static void shuffleIter(int[] cards) {
    for (int i = 1; i < cards.length; i++) {
      int pos = new Random().nextInt(i + 1);
      int temp = cards[pos];
      cards[pos] = cards[i];
      cards[i] = temp;
    }
  }
}