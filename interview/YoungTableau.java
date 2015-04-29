public class YoungTableau {

  /**
   * Given a N*N Matrix.
   * All rows are sorted, and all columns are sorted.
   * Find the Kth Largest element of the matrix.
   */
  // http://www.careercup.com/question?id=6335704

  // This is actually a variant of merge sort.
  // Each row in the matrix can be regarded as a array of size N
  // Then the question becomes to merge sort M arrays of size N
  // time: O(klgk); space: O(k)
//  1> The smallest number is the one which doesn't have any number on the left or above.
//  2> (0,0) is the only one number which satisfies the rule no 1, so lets remove it and keep it as 1st smallest element.
//  3> Step 2, makes 2 element (0,1) & (1,0) to satisfy the rule no 1, find the smallest among them and remove it.
//     It will release another one element to satisfy the rule no 1.
//  4> Repeat step 3 till you remove k element.
  public ArrayList<Integer> getKthLargestInYoungTableau(int[][] matrix, int k){
    int M = matrix.length, N = matrix[0].length;
    ArrayList<Integer> res = new ArrayList<Integer>();
    PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
      public int compare(Cell c1, Cell c2){
        return c1.val - c2.val;
      }
    });
    for (int i=0; i<M; i++)
      minHeap.add(new Cell(i, 0, matrix[i][0]));// this is k-th smallest, for k-largest we should add last col to max-heap, each time pop the largest one for k times
    while (--k>=0 && !minHeap.isEmpty()){
      Cell curr = minHeap.poll();
      if (curr.y+1<N)
        minHeap.add(new Cell(curr.x, curr.y+1, matrix[curr.x][curr.y+1]));
      res.add(curr.val);
    }
    return res;
  }

  class Cell{
    int x;
    int y;
    int val;
    public Cell(int x, int y, int val){
      this.x = x;
      this.y = y;
      this.val = val;
    }
  }

  @Test
  public void test(){
    int[][] matrix = new int[][]{
        {1,3,5,7},
        {2,4,6,8},
        {3,5,7,9},
        {4,6,8,10}
    };
    System.out.println(getKthLargestInYoungTableau(matrix, 3));
    System.out.println(getKthLargestInYoungTableau(matrix, 16));
  }
}