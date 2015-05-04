/*
Find the n-th digit of a number which is constructed by concatenation of all natural numbers.
Concatenation of all natural numbers looks like:
1234567891011121314...
 */

/*
The number of numbers in the concatenated string that have k digits is

9 * 10 ^ (k - 1)

For example, there are 9 * 10 ^ (2 - 1) = 90 two digit numbers.

So the problem is to find which number of digits region the given n falls.
To find that we subtract the length of each region from digit 1 to k from the given number
Once we determine which region n falls in, we can determine which exact number it falls on.
 */

class Solu{
  int nthDigit(int n) {
    int k = 0;
    while( n > 0) {
      k++;
      n -= 9 * pow(10, k - 1) * k;
    }
    n += 9 * pow(10, k - 1) * k;
    if( n % k == 0) {
      int finalNumber = pow(10, k - 1) + (n -1)/k;
      return finalNumber % 10;
    } else {
      int finalNumber = pow(10, k - 1) + (n / k);
      return static_cast<int>((finalNumber / pow(10, k - (n % k)))) % 10;
    }
  }
}

class SOlu{
  int get_digit_num(int num, int dig) { // get dig-th digit in number num
    while(--dig > 0) num /= 10;
    return num % 10;
  }
  int get_digit(int n) {
    int numDigs = 1;
    int totalDigits = 9;

    while(n > totalDigits) {
      n -= totalDigits;
      numDigs++;
      totalDigits = numDigs * (pow(10,numDigs)-pow(10,numDigs-1));
    }

    int num = (n-1) / numDigs;
    int dig = numDigs - ((n-1) % numDigs);
    return get_digit_num(pow(10,numDigs-1) + num, dig);
  }
}