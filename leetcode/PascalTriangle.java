/*
    Given numRows, generate the first numRows of Pascal's triangle.
    For example, given numRows = 5,
    Return
    [
         [1],
        [1,1],
       [1,2,1],
      [1,3,3,1],
     [1,4,6,4,1]
    ]
*/

// Get each level in top-down order
// Each level is based on the previous level
// time: O(n^2); sapce: O(n^2)
public class Solution {
  public ArrayList<ArrayList<Integer>> generate(int numRows) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(numRows==0) return res;
    ArrayList<Integer> one = new ArrayList<Integer>();
    one.add(1);
    res.add(one);
    int i=1;
    while(i < numRows){
      ArrayList<Integer> curr = new ArrayList<Integer>();
      ArrayList<Integer> prev = res.get(i-1); // put declaration of prev out of loop would be cleaner
      int len = prev.size();
      curr.add(1);
      for(int j=1; j<len;j++){
        curr.add(prev.get(j-1)+prev.get(j));
      }
      curr.add(1);
      res.add(curr);
      i++;
    }
    return res;
  }
}