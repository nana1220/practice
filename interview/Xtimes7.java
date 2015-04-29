/*
Write a function to calculate x * 7 without using + - / * %.

 */

/*
x * 7可以做如下分解：
x * 7 = x * 4 + x * 2 + x
= (x << 2) + (x << 1) + x
= add(x << 2, add(x << 1, x))
所以本质上这题就是考用二进制实现add函数。
 */

class Solu{
  int add(int a, int b) {
    int sum, carry;
    do {
      sum = a ^ b;
      carry = (a & b) << 1;
      a = sum;
      b = carry;
    } while (b);
    return a;
  }
  int times_7(int val) {
    return add(val << 2, add(val << 1, val));
  }
}