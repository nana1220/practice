package ch5bitmanipulation;

import java.lang.Integer;
import java.util.ArrayList;
/*
 * An array A contains all the integers from 0 through n, except for one number which is missing.
 * In this problem, we cannot access an entire integer in A with a single operation. The elements
 * of A are represented in binary, and the only operation we can use to access them is
 * "fetch the j-th bit of A[i]" (j-th is starting from the least significant bit), which takes
 * constant time. Write code to find the missing integer. Can you do it in O(n) time?
 */

/*
 * Solution1: sum up every bits for a number O(log(n)), because the largest number n will have
 * log(n) bits. Sum up all the number O(n), compare the total sum with the sum without any missing value
 * time: O(nlog(n))
 *
 * Solution2: if we have all numbers from 0 to n, the number of zeros in each digit is always
 * larger than or equal to the number of ones. If count(0) <= count(1) in i-th digit, the i-th digit
 * of the missing number should be 0; otherwise, it should be 1. Recursively get each digit of
 * the missing number.
 * Besides, since we know that the missing number's value in i-th digit, we can narrow down the serach
 * range to those numbers with i-th digit equal to missing number and count the number of
 * ones and zeros for the higher digit for the subset
 */
public class FindMissingValue {
  /**
   *
   * @param numbers
   * @param ithDigit initial input value is 0, search starts from 0th digit
   * @return the value represented by missing value's i-th digit
   */
  static int findMissingNumber(ArrayList<Integer> numbers, int ithDigit) {
    if (ithDigit > 31) { // base case
      return 0;
    }
    ArrayList<Integer> zeros = new ArrayList<Integer>();
    ArrayList<Integer> ones = new ArrayList<Integer>();
    for (Integer number : numbers) {
      if (((number >> ithDigit) & 1) == 1) {
        ones.add(number);
      } else {
        zeros.add(number);
      }
    }
    if (zeros.size() <= ones.size()) {
      return findMissingNumber(zeros, ithDigit + 1); // 0 * Math.pow(2, ithDigit) is ignored
      // Note: can also use following bit manipulation
      // the deeper recursion return higher digit, so shift right and set current digit
      // int res = findMissingNumber(zeros, ithDigit + 1);
      // return (res << 1) | 0;
    } else {
      // don't forget explicitly convert to int
      return 1 * (int) Math.pow(2, ithDigit) + findMissingNumber(ones, ithDigit + 1);
      // int res = findMissingNumber(ones, ithDigit + 1);
      // return (res << 1) | 1;
    }
  }

  public static void main(String[] args) {
    ArrayList<Integer> nums = new ArrayList<Integer>();
    nums.add(0);
    nums.add(1);
    nums.add(3);
    nums.add(4);
    nums.add(5);
    System.out.println(findMissingNumber(nums, 0));
  }
}