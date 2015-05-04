
/*
Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
Determine the maximum value obtainable by cutting up the rod and selling the piecesaa
 */
public class CuttingRod {



  public int maxValue(int price[]){
    int max[] = new int[price.length+1];
    for(int i=1 ; i <= price.length; i++){ // i is length
      for(int j=0; j < i ; j++){
        max[i] = Math.max(max[i], max[i-j] + price[j]); // j=0 means no cut, j=1 means cut length1,
      }
    }
    return max[price.length];
  }

  public int maxValue1(int price[]){
    int max[] = new int[price.length+1];
    for(int i=1; i <= price.length; i++){
      for(int j=i; j <= price.length; j++){
        max[j] = Math.max(max[j], max[j-i] + price[i-1]);
      }
    }
    return max[price.length];
  }


  public int recursiveMaxValue(int price[],int len){
    if(len <= 0){
      return 0;
    }
    int maxValue = 0;
    for(int i=0; i < len;i++){
      int val = price[i] + recursiveMaxValue(price, len-i-1);
      if(maxValue < val){
        maxValue = val;
      }
    }
    return maxValue;
  }
  public static void main(String args[]){
    CuttingRod cr =new CuttingRod();
    int[] price = {3,5,8,9,10,20,22,25};
    long t1 = System.currentTimeMillis();
    int r = cr.recursiveMaxValue(price,8);
    long t2 = System.currentTimeMillis();
    System.out.println(r);
    System.out.println(t2 - t1);
  }
}