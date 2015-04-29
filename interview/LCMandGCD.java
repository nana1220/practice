class solu{
  int gcd( int a, int b ) {
    if (a < b )
      swap(a,b);
    while (b) {
      int t = b;
      b = a%b;
      a = t;
    }
    return a;
  }
  int lcm(int n , int m) {
    return n / gcd(n,m) * m;
  }
}