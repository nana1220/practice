public class Solution {
  // DFS, TLE
  public int minimumTotal(List<List<Integer>> triangle) {
    if(triangle.size() ==1) return triangle.get(0).get(0);
    List<Integer> path=new ArrayList<Integer>();
    path.add(0); // index of level 0
    total(triangle, path, 1);
    return minSum;
  }
  int minSum = Integer.MAX_VALUE;
  // List<Integer> res=null;
  void total(List<List<Integer>> triangle, List<Integer> path, int level){
    if(level==triangle.size()){
      int i=0;
      //       List<Integer> res = new ArrayList<Integer>();
      int sum=0;
      while(i < level){
        int idx = path.get(i);
        int val = triangle.get(i).get(idx);
        //           res.add(val);
        sum += val;
        i++;
      }
      if(sum< minSum)
        minSum = sum;
      return;
    }
    int prevIdx = path.get(level-1); // path stores item's index from each level
    path.add(prevIdx);
    total(triangle, path, level+1);
    path.remove(path.size()-1);
    path.add(prevIdx+1);
    total(triangle, path, level+1);
    path.remove(path.size()-1);
  }

  // dp: bottom up,  use additional array to store the sum of paths.
  public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
      int level = triangle.size();
      int[] minSum = new int[level+1]; // level+1 all zeros
      for(int i = level-1; i>=0; i--){ // bottom up
        List<Integer> currLevel = triangle.get(i);
        for(int j =0; j<currLevel.size(); j++){
          minSum[j] = currLevel.get(j) + Math.min(minSum[j], minSum[j+1]);
        }
      }
      return minSum[0];
    }
  }
}