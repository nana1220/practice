/*
Count the number of prime numbers less than a non-negative number, n
 */

// Sieve of Eratosthenes
public class Solution {
  public int countPrimes(int n) {
    boolean[] primes = new boolean[n+1];
    Arrays.fill(primes, true);
    for(int i=2; i<n; i++){
      int tmp = i+i;
      while(tmp<n){
        primes[tmp]=false;
        tmp += i;
      }
    }
    int count=0;
    for(int i=2; i<n; i++){
      if(primes[i]) count++;
    }
    return count;
  }
}