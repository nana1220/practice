/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
 */

// 从前往后扫描，如果第i个小孩等级比第i-1个高，那么i的糖数目等于i-1的糖数目+1；从后往前扫描，如果第i个的小孩的等级比i+1个小孩高,但是糖
// 的数目却小或者相等，那么i的糖数目等于i+1的糖数目+1
public class Solution{
  public int candy(int[] ratings) {
    int[] candies = new int[ratings.length];
    candies[0]=1;
    for (int i=1; i< ratings.length;i++){
      if(ratings[i] > ratings[i-1]) candies[i] = candies[i-1]+1;
      else candies[i]=1;
    }
    for(int i=ratings.length-1; i>0; i--){
      if(ratings[i-1] > ratings[i] && candies[i-1]<= candies[i]){
        candies[i-1] = candies[i]+1;
      }
    }
    int sum=0;
    for(int n : candies) sum+=n;
    return sum;
  }
}
// brute force: TLE
/*
d[i] 是给第i个小孩最少几块糖
rank[i] > rank[i – 1]，必须比前一个多给一块，d[i] = d[i – 1] + 1
rank[i] == rank[i – 1]，两个排名一样，第二个就给一块就行了, d[i] = 1
rank[i] < rank[i – 1]，比上一个排名低，应该少给一块，但是若上一个已经只给一块了，就得往前推一个一个多给。
推到什么时候为止呢？若排名比下一个高，糖还一样多，就得再给；直到这个关系打破（排名一样或比下一个还低，或是糖已经满足关系）就不用再往前推了。
 */
public class Solution{
  public int candy(int[] ratings) {
    if (ratings.length == 0)
      return 0;
    int[] d = new int[ratings.length];
    d[0] = 1;
    for (int i = 1; i < ratings.length; i++) {
      if (ratings[i] == ratings[i - 1])
        d[i] = 1;
      else if (ratings[i] > ratings[i - 1])
        d[i] = d[i - 1] + 1;
      else {// should give less candy than prev child
        d[i] = 1;
        if (d[i - 1] == 1) {
          int j = i;
          while (j > 0 && ratings[j - 1] > ratings[j] && d[j - 1] == d[j]) {
            //only push backwards when ratings diff but candy are same
            d[j - 1]++;
            j--;
          }
        }
      }
    }
    int sum = 0;
    for (int i = 0; i < ratings.length; i++) {
      sum += d[i];
    }
    return sum;
  }
}