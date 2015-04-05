public class Prime {

  public boolean isPrime(int num){
    for(int i = 2; i < num; i++){
      if(num % i == 0){
        return false;
      }
    }
    return true;
  }


  int findnth(int nth) {
    int num, count, i;
    num=1;
    count=0;
    while (count < nth){

      num=num+1; //find the next prime number
      for (i = 2; i <= num; i++){

        if (num % i == 0) {

          break; //prime not found
        }
      }
      if ( i == num){
        count = count+1; //prime found
      }
    }
  }
}