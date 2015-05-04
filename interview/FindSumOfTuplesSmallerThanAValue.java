/*
 * sorted array, find number of tuples a + b <= X
 * follow up, find number of three tuples a+ b + c <= X
 */
class solution {
  public static int numberOfPairs(int[] A, int x) {
    int left = 0, right = A.length - 1;
    int count = 0;
    while (left < right) {
      //valid element that can pair with A[left]
      //should be on the left of A[right]
      if (A[left] + A[right] > x)
        right--;
        //A[left] + A[right] <= x, we know this is a valid pair
        //also we know every elements from A[left+1] to A[right] can
        //be paired with A[left], since A is a sorted array, then we
        //should increment count by (right - left).
      else {
        count += (right - left);
        left++;
      }
    }
    return count;
  }
}

/*
 * three sum
 */
/*
1:    vector<vector<int> > threeSum(vector<int> &num) {
2:      // Start typing your C/C++ solution below
3:      // DO NOT write int main() function
4:      std::sort(num.begin(), num.end());
5:      vector<vector<int> > result;
6:      int len = num.size();
7:      for(int i =0; i< len; i++)
8:      {
9:        int target = 0-num[i];
10:        int start = i+1, end =len-1;
11:        while(start<end)
12:        {
13:          if(num[start] + num[end] == target)
14:          {
15:            vector<int> solution;
16:            solution.push_back(num[i]);
17:            solution.push_back(num[start]);
18:            solution.push_back(num[end]);
19:            result.push_back(solution);
20:            start++; end--;
21:            while(start<end && num[start] == num[start-1]) start++;
22:            while(start<end && num[end] == num[end+1]) end--;
23:          }
24:          else if(num[start] + num[end] < target)
25:          {
26:            start++;
27:          }
28:          else
29:          {
30:            end--;
31:          }
32:        }
33:        if(i<len-1)
34:        {
35:          while(num[i] == num[i+1]) i++;
36:        }
37:      }
38:      return result;
39:    }
（1）先排序
（2）扫一遍数组，用hash来记录当前的数是第几个数。, can build a table dp[i][j] denote number of distinct values in  [i,j]
比如 [1,2,3,3,5,5,5,10] => H[1] = 1,H[2] = 2,H[3] = 3,H[5] = 4,H[10] = 5
int prev = arr[0]; map.put(prev, 1); int pos = 2;
for( int i = 1; i < arr.length; i++) {
 if (arr[i] != prev) { map.put(arr[i], pos++); prev = arr[i];} }
（3）类似3SUM的方法，写二重循环，用two pointer来求解。时间复杂度O（N^2)
  for (i = 0;i < N;i++) {
     start = i + 1;
     end = N - 1;
     while (start < end) {
        while (A + A[start] + A[end] > T) end--;
        count += Q[A[end]] - Q[A[start]];   // A, A[start] + ? 的个数-google 1point3acres
        start++;
}




[Some tricks]
1. Line 21 and Line 22.
    filter the duplicate during two-pointer scan. For example [-2, 0, 0, 2,2], the expected output should be [-2,0,2]. If no filter here, the output will be duplicate as [-2,0,2] and [-2,0,2]
2. Line 35
   filter the duplicate for outside iteration. For example [-2, -2, -2, 0,2].
 */