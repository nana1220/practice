/*
一个array里面找最大的这样的h:有h个数大于等于h
比如{2,3,5}答案是2，{5,6,7,8}答案是4
 */

/*
返回的h最多就是A.length, 所以直接开一个A.length的数组counting值分别为1 to A.length的elem数，
trick在于所有value大于A.length的elem也count到A.length那一类。因为这题不是在求H index的那个scenario，
可以稍微优化下空间，count array的size取min(A.length, max elem in A)
 */

class Solution {
  public static int findH(int[] a){
//    int max = 0;
//    for(int i = 0; i < a.length; i++){
//      if(a[i] > max){
//          max = a[i];
//      }
//    }
//    int size = Math.min(max + 1, a.length + 1);
    int size = a.length + 1;
    int[] c = new int[size];
    for(int i = 0; i < a.length; i++){
      int cur = a[i];
      if(a[i] >= 0 && cur < size){
        c[cur] ++;
      }
      else if(a[i] >= 0 && cur >= size){
        c[a.length]++;
      }
    }
    for(int i = a.length; i > 0; i--){
      c[i - 1] += c[i];
      if(i <= c[i]){
        return i;
      }
    }
    return -1;
  }
}