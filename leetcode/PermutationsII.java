/*
  Given a collection of numbers that might contain duplicates, return all possible unique permutations.
  For example,
  [1,1,2] have the following unique permutations:
  [1,1,2], [1,2,1], and [2,1,1].
*/

// use a set store index instead of value
public class Solution {
  public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> list = new ArrayList<Integer>();
    HashSet<Integer> idxSet = new HashSet<Integer>();
    permute(num, list, res, idxSet);
    return res;
  }

  void permute(int[] num, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> res, HashSet<Integer> idxSet){
    if (list.size() == num.length){
      res.add(new ArrayList<Integer>(list));
      return;
    }
    HashSet<Integer> set = new HashSet<Integer>(); // to avoid using duplicate values in one loop
    for(int i=0; i< num.length; i++){
      if(!idxSet.contains(i) && !set.contains(num[i])){
        idxSet.add(i);
        list.add(num[i]);
        set.add(num[i]);
        permute(num, list, res, idxSet);
        idxSet.remove(i); // remember to remove both value from list and index from set
        list.remove(list.size()-1);
      }
    }
  }
}


// In place swap, swap different values to current position
// if same value is encountered, skip it
public class Solution {
  public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    Arrays.sort(num);
    perm(num, 0, res);
    return res;
  }

  private void perm(int[] num, int index, ArrayList<ArrayList<Integer>> res){
    if (index==num.length){
      ArrayList<Integer> r = new ArrayList<Integer>();
      for (int val:num)   r.add(val);
      res.add(r);
      return;
    }
    Set<Integer> set = new HashSet<Integer>();
    for (int i=index; i<num.length; i++){
      if (set.contains(num[i])) // avoid same value
        continue;
      swap(num, index, i);
      perm(num, index+1, res);  // Note: index+1, not i+1
      swap(num, index, i);
      set.add(num[i]);
    }
  }

  private void swap(int[] A, int i, int j){
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
  }
}