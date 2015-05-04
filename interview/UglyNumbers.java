/*
Ugly Numbers are numbers whose only prime factors are 2, 3 or 5. The sequence
1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
shows the first 11 ugly numbers. By convention, 1 is included.
Write a program to find and print the 150’th ugly number.
 */

class Solu{
  unsigned get_ugly_num(unsigned n) {
    // instead of use three indexes
    // can maintian three queue, each time get a ugly number from the minimum of head of three queues,
    // and put 2*number, 3*number, 5*number to three queues

    // can use one minheap to hold 2*number, 3*number, 5*number
    unsigned i2 = 0, i3 = 0, i5 = 0;
    unsigned next_mul2 = 2, next_mul3 = 3, next_mul5 = 5;

    unsigned ret = 1;
    vector<unsigned> ugly(n);
    ugly[0] = 1;

    for(unsigned i = 1; i < n; i++) {
      ret = min(min(next_mul2, next_mul3), next_mul5);
      ugly[i] = ret;

      if(ret == next_mul2) next_mul2 = ugly[++i2]*2;
      if(ret == next_mul3) next_mul3 = ugly[++i3]*3;
      if(ret == next_mul5) next_mul5 = ugly[++i5]*5;
    }
    return ret;
  }
}