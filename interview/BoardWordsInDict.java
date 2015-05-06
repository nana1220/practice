/*
Given a 4x4 board of letters and a dictionary, find all valid words. The dictionary provides two
methods. boolean hasNext(String s) returns true when the dictionary has at least one word
prefixing with s. boolean hasWord(String w) returns true when w is in the dictionary.
 */

class solu{
  public void findWords(char[][] board){
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] visited = new boolean[rows][cols];
// for each position on the board, call the findWords method
    for (int i = 0; i < rows; i++)
      for (int j = 0; j < cols; j ++){
        StringBuilder s = new StringBuilder();
        findWords(board,i,j,visited,s);
      }
  }
  public void findWords(char[][] board, int i, int j,
                        boolean[][] visited, StringBuilder s){
// corner cases when i or j is out of bound
    if (i<0 || i >= board.length || j<0 || j>=board[0].length)
      return;
// If the letter at (i,j) is not used yet and the dictionary has
// possible words with prefix of current string appended with
// board[i][j], it is a possible path
    if (!visited[i][j] && hasNext(s.toString() + board[i][j])){
      visited[i][j] = true;
      s.append(board[i][j]);
      if (hasWord(s.toString())){ // we have a valid word
        System.out.println(s.toString());
      }
// go through each neighbor
      findWords(board,i-1,j,visited,s);
      findWords(board,i+1,j,visited,s);
      findWords(board,i,j-1,visited,s);
      findWords(board,i,j+1,visited,s);
// backtracking
      s.deleteCharAt(s.length()-1);
      visited[i][j] = false;
    }
  }
}