/*
Determine whether an integer is a palindrome. Do this without extra space.

Some hints:
Could negative integers be palindromes? (ie, -1)
If you are thinking of converting the integer to string, note the restriction of using extra space.
You could also try reversing an integer. However, if you have solved the problem "Reverse Integer",
you know that the reversed integer might overflow. How would you handle such case?
There is a more generic way of solving this problem.
 */

public class PalindromeNumber {

  /*
   * reverse number check if reversed is equal to original
   */
  public boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    // use long in case reverse number is overflow
    long reverse = 0;
    int original = x; // NOTE: don't forget to keep a copy of x, x will be changed
    while (x > 0) {
      reverse = reverse * 10 + x % 10;
      x /= 10;
    }
    return (reverse == original);
  }
}
