//判断一个32位integer是否为4的幂次数。
//    查表法。因为32位int范围内的4的幂次数只有16个，完全可以实现求好然后查表。

    bool power_of_4(int number) {
    unordered_set<int> table;
    long long cur = 1;
    do {
    table.insert((int)cur);
    cur *= 4;
    } while (cur <= INT_MAX);
    return table.count(number);
    }
//    二进制操作。效率最高，时间O(1)空间O(1)。因为4次幂其实就是特殊的2次幂，即是1每次往左移两位。
//    比如前四个4次幂数字如下：
//    1 = 0b000000001
//    4 = 0b000000100
//    16 = 0b000010000
//    64 = 0b001000000
  //  以上规律可知，4次幂数字就是偶数位为0的2次幂数字，这样就很好判断了。

    bool power_of_4(int num) {
    if(num <= 0) return false;
    return (num & 0xAAAAAAAA) == 0 && (num & (num - 1)) == 0;
    }
//    二分法

    bool power_of_4(int num) {
    long long base = 4, cur = 1;
    while(cur <= num) {
    if(cur == num) return true;
    else if(cur * base > num) base = 4;
    cur *= base;
    base *= base;
    }
    return false;
    }
