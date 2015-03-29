/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all
unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
 */

public class ThreeSum {
  // sort first, then run a outer loop for first element
  // and then run a inner loop computing two sum
  public List<List<Integer>> threeSum(int[] num) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if (num.length < 3) // check error
      return res;
    Arrays.sort(num); // to make result non-descending order
    for (int i = 0; i < num.length; i++) {
      if (i > 0 && num[i - 1] == num[i]) continue; // avoid duplicates
      int sum = -1 * num[i];
      // since numbers are sorted, maintain left and right end, shrink to middle, instead of use map.contains.(sum - elem)
      // thus duplicate two sum can be avoided
      int l = i + 1;
      int r = num.length - 1;
      while (l < r) {
        if (num[l] + num[r] == sum) {
          ArrayList<Integer> triple = new ArrayList<Integer>();
          triple.add(-1 * sum);
          triple.add(num[l]);
          triple.add(num[r]);
          res.add(triple);
          // NOTE!!!: dont forget check bound for l and r
//          while (l < num.length - 1 && num[++l] == num[l]) { // avoid duplicates
//            continue;
//          }
//          while (r > i + 1 && num[--r] == num[r]) {
//            continue;
//          }
          do {
            l++;
          } while(l < num.length - 1 && num[l] == num[l-1]); // NOTE!!!: dont forget check bound for l and r
          do{
            r--;
          } while(r > i + 1 && num[r] == num[ r + 1]);
        } else if (num[l] + num[r] > sum) r--;
        else l++;
      }
    }
    return res;
  }
}