/*
有一个密码锁，它由6位数字组成（都是0~9的数字），我们如果将这六位数字顺时针旋转180度，它还是一个有效的6位数字的概率是多少？
 */

/*
他的数字都是表示成那种电子钟表的格式的，所以5和2倒过来还是它们自己。1倒过来还算是1，这个和面试官确认过了。这时我们可以发现还有
7个数字是有效的，那 么6位的密码锁倒过来还是有效的概率就是0.7的六次方。

第二个问题是由第一个问题的基础上提出的，在旋转之后还是一个有效数字的基础上，将这个6位的密码锁旋转之后六位数字与原来数字相同的概率是多少？
这个问题需要好好思考下，标记6位数字为 abcdef ，旋转之后就变成了T（fedcba)，其中T()操作会将每一位的数字旋转180度。
我们再来观察下，若想要af旋转180度还是af的话，有以下几种情况：
（0，0）、（1,，1）、（2，2）、（5，5）、（8，8）、（6，9）、（9，6）
也就是说我们需要将六位数字分为（1，6）、（2，5）和（3，4）这三组。这三组的组合可以有6的三次方（也就是216种组合）。那么这个概率就很好求了
，只需将216除以7的六次方就行了。最后结果是216/117649
 */



/*
A valid number may not contain 2,3,4,5,7. Flip those numbers 180 degrees and
  it's not a valid number. Single digit numbers is a special case. It
  contains 0, 1, 8. For 2N digit numbers, the first digit can be 1,6,8,9 and
  the next N-1 digits can be 0,1,6,8,9. For 2N+1 digit numbers, you can insert
  0,1,8 in the middle of any valid 2N digit numbers and it's still a valid
  number.

  1        digit: 3
      2N     digit: 4 * 5 ^ (N-1)
      2N+1 digit: 3 * 4 * 5 ^ (N-1)

  For example, there are 20 valid 4 digit numbers:

      10 => 1001, 11 => 1111, 16 => 1691, 18 => 1881, 19 => 1961
      60 => 6009, 61 => 6119, 66 => 6699, 68 => 6889, 69 => 6969
      80 => 8008, 81 => 8118, 86 => 8698, 88 => 8888, 89 => 8968
      90 => 9006, 91 => 9116, 96 => 9696, 98 => 9886, 99 => 9966

  there are 60 valid 5 digit numbers

  1001 => 10001, 10101, 10801 etc
 */

class SOlu{


  public static int getNumMirrors(String limit)
  {
    byte[] n = limit.getBytes();
    int digits = n.length;
    for (int i = 0; i < digits; i++)
    {
      n[i] -= '0';
    }
    if (digits == 1)
    {
      return getNumMirrorsSingleDigit(n[0]);
    }
    else
    {
      return getNumMirrorsLessThanKDigits(digits) +
          getNumKDigitMirrorsLessThanN(n, digits);
    }
  }

  private static int getNumMirrorsSingleDigit(int n)
  {
    if (n >=8) return 3;
    if (n >=1) return 2;
    return 1;
  }

  private static int getNumMirrorsLessThanKDigits(int digits)
  {
    int sum = 3; // 0, 1, 8
    int base = 4; // 1, 8, 6, 9
    for (int i = 1; i < digits/2; i++)
    {
      sum += 4 * base;
      base *= 5;
    }
    if (digits % 2 == 1)
    {
      sum += base;
    }

    return sum;
  }

  private static int getNumKDigitMirrorsLessThanN(byte[] n, int digits)
  {
    int sum;
    int base = 1;
    boolean isNMirror;
    for (int i = 1; i < digits/2; i++)
    {
      base *= 5;
    }
    if (digits % 2 == 1)
    {
      base *= 3;
    }
    switch (n[0]) // 1, 6, 8, 9
    {
      // n=987654321, add all mirrors between 0 and 900000000
      case 9:  sum = 3 * base; isNMirror = n[digits-1] == 6; break;
      case 8:  sum = 2 * base; isNMirror = n[digits-1] == 8;break;
      case 7:  return 2 * base;
      case 6:  sum = base; isNMirror = n[digits-1] == 9;break;
      case 1:  sum = 0; isNMirror = n[digits-1] == 1;break;
      default: return base;
    }

    for (int i = 1; i < digits/2; i++)
    {
      base /= 5;
      switch (n[i]) // 0, 1, 6, 8, 9
      {
        // n=987654321, i=1, add all mirrors between 900000000 and 980000000
        case 9:  sum += 4 * base; isNMirror &= n[digits-i-1] == 6; break;
        case 8:  sum += 3 * base; isNMirror &= n[digits-i-1] == 8; break;
        case 7:  return 3 * base;
        case 6:  sum += 2 * base; isNMirror &= n[digits-i-1] == 9; break;
        case 1:  sum += base; isNMirror &= n[digits-i-1] == 1; break;
        case 0:  isNMirror &= n[digits-i-1] == 0; break;
        default: return sum + 2 * base;
      }
    }

    if (digits % 2 == 1)
    {
      switch (n[digits/2]) // 0, 1, 8
      {
        case 9:  return sum + 3;
        case 8:  sum += 2; break;
        case 1:  sum += 1; break;
        case 0:  break;
        default: return sum + 2;
      }
    }

    return isNMirror ? sum + 1 : sum;
  }
}