class Solu{
  int rand10() {
    int x = 0;
    do {
      x = (rand7() - 1) * 7 + rand7();
    } while(x > 40);
    return x % 10 + 1; // mod 10 is [0,9], plus one to get [1,10]
  }

  int rand7(){
    int x = ~(1<<31); // max int
    while(x > 21)
      x = 5 * (rand5() - 1) + rand5(); // rand25
    return x % 7 + 1;
  }
}