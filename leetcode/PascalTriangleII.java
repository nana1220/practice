/*
    /*Given an index k, return the kth row of the Pascal's triangle.
    For example, given k = 3,
    Return [1,3,3,1].
    Note:
    Could you optimize your algorithm to use only O(k) extra space?
*/

// Initialzie a array of size n at first
// In each level, we update the values in range of [1...level]
// NOTE:: in other word, add 0 after the last position of previous level, compute current level from end of previous level
// Note we have to update from the end of the array.
// time: O(n^2); space: O(n)
public class Solution {
  public List<Integer> getRow(int rowIndex) {
    List<Integer> rst = new ArrayList<Integer>();
    int[] res = new int[rowIndex+1];
    res[0]=1;
    int i=1;
    while(i<=rowIndex){
      for(int j=i; j>=1; j--){
        res[j]=res[j-1]+res[j];
      }
      i++;
    }

    for(int num : res) rst.add(num);
    return rst;
  }
}