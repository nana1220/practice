/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
 */


// Stack, very tricky!!!!!

public class Solution {
  public int largestRectangleArea(int[] height) {
    int max=0;
    Stack<Integer> lefts = new Stack<Integer>();
    int right=0;
    while(right<height.length){
      if(lefts.isEmpty() || height[lefts.peek()] <= height[right]){
        lefts.push(right++);
      } else{
        int h= height[lefts.pop()];
        while(!lefts.isEmpty() && height[lefts.peek()] == h) lefts.pop(); // pop all equal height index
        // NOTE!!!!!!!!!!: very tricky, those higher bars on the current hightest bar's left have been popped out
        // the left index should be next index of left lower bar on the left
        // Also if stack is empty that is no left lower bar, left index should be zero
        int left=lefts.isEmpty() ? 0 : lefts.peek()+1;
        int w=  right-left;

        max=Math.max(max, w*h);
      }
    }
    while(!lefts.isEmpty()){
      int h= height[lefts.pop()];
      int left=lefts.isEmpty() ? 0 : lefts.peek()+1;
      int w=  right-left;
      max=Math.max(max,w*h);
    }
    return max;
  }

}

// brute force: TLE
// for each bar, compute the area containing this bar O(n^2)
public class Solution {
  public int largestRectangleArea(List<Integer> height) {
    int max =0;
    for(int i=0; i< height.size(); i++){
      int h=height.get(i);
      int width=1;
      int l=i;
      int r=i;
      while(l-1 >=0){
        if(height.get(l-1) >h){
          width++;
          l--;
        }
        else break;
      }
      while(r+1<height.size()){
        if(height.get(r+1) >h){
          width++;
          r++;
        } else break;
      }
      max=Math.max(max, width*h);
    }
    return max;

  }
}
