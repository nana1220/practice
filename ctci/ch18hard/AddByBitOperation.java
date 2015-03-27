/*
Write a function that adds two numbers. You should not use + or any arithmetic
operators.
*/

package ch18hard;

/*
 * a ^ b add each bit of a to b without considering carry terms
 * a & b compute the carry term for each bit
 * (a & b) << 1 move carry terms to next bit
 * (a ^ b) ^ ((a & b) << 1) compute the sum with carry terms but not considering new carries
 * so recursively add result and carry until there are no carries
 */
public class AddByBitOperation {

  static int add(int a, int b) {
    // treat b as carry term
    // base case: b = 0 no carry
    if (b == 0) return a;
    int sum = (a ^ b); // add without carrying
    int carry = (a & b) << 1; // carry without adding
    return add(sum, carry);
  }
}