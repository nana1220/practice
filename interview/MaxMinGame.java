/*
一串数，例如 10， 20，30，40，20，50，25. 两个玩家，只能选最左或最右，轮流选，怎样max你的和。举出贪心反例。用max-min。
 */

include <iostream>
include <assert.h>
#include <algorithm>
    using namespace std;

    int coinGame(int array[], int n) {
    assert(n > 0);
    if (n == 1). more info on 1point3acres.com
    return array[0];
    int start = 0;
    int end = n - 1;
    int sum = 0;
    for (; start < end;) {
    if (end - start == 1) {
    sum += max(array[start], array[end]);
    break;
    }
    else if (end - start == 2) {
    sum += max(array[start], array[end]) + array[start+1];
    break;
    }
    else {
    //cout << array[end] - array[end-1] << endl;
    sum += (min(array[start] - array[start+1], array[start]-array[end]) >
    min(array[end] - array[end-1], array[end]-array[start]))
    ? array[start++] : array[end--];
    //cout << end << endl;
    min(array[start] - array[start+1], array[start]-array[end]) >
    min(array[end] - array[end-1], array[end]-array[start]) ? start++ : end--;
    //cout << end << endl;
    }
    }
    return sum;
    }

    int main() {
    int test1[] = {50, 100, 1000, 49};
    int test2[] = {50, 100, 1, 49};
    cout << coinGame(test1, 4) << endl;
    cout << coinGame(test2, 4) << endl;

    }