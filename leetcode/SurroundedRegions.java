/*
    Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
    A region is captured by flipping all 'O's into 'X's in that surrounded region .
    For example,
    X X X X
    X O O X
    X X O X
    X O X X
    After running your function, the board should be:
    X X X X
    X X X X
    X X X X
    X O X X
*/

// DFS stackover flow for online judge
public class Solution {
  // flip the areas of 'O's connecting to edge to '1'
  // then flip remaining areas of 'O' to 'X'
  // set '1' back to 'O'
  public void solve(char[][] board) {
    if(board.length ==0 || board[0].length==0) return;
    int m =board.length;
    int n = board[0].length;
    for(int x=0; x<m; x++){
      flip(board, x,0, 'O', '1');
      flip(board, x,n-1, 'O', '1');
    }
    for(int y=0; y<n; y++){
      flip(board,0,y,'O', '1');
      flip(board,m-1,y,'O','1');
    }
    for(int x=0; x<m;x++){
      for(int y=0; y<n; y++){
        if(board[x][y] == 'O') // NOTE: can simply set 'O' to 'X' and set '1' to 'O' in this loop
          flip(board, x, y, 'O','X');
      }
    }
    for(int x=0; x<m;x++){
      for(int y=0; y<n; y++){
        if(board[x][y]=='1') board[x][y]='O';
      }
    }
  }
  // DFS: flip c1 to c2
  void flip(char[][] board, int x, int y, char c1, char c2){
    if(board[x][y] != c1) return;
    board[x][y] = c2;
    if(x-1 >=0) flip(board, x-1, y, c1, c2);
    if(x+1<board.length) flip(board, x+1, y, c1, c2);
    if(y-1>=0) flip(board, x, y-1, c1, c2);
    if(y+1<board[0].length) flip(board,x,y+1,c1,c2);
  }

}

public class Solution {
  // flip the areas of 'O's connecting to edge to '1'
  // then flip remaining areas of 'O' to 'X'
  // set '1' back to 'O'
  public void solve(char[][] board) {
    if(board.length ==0 || board[0].length==0) return;
    int m =board.length;
    int n = board[0].length;
    for(int x=0; x<m; x++){
      bfs(board, x,0, 'O', '1');
      bfs(board, x,n-1, 'O', '1');
    }
    for(int y=0; y<n; y++){
      bfs(board,0,y,'O', '1');
      bfs(board,m-1,y,'O','1');
    }
    // set 'O' to 'X' and set '1' to 'O' in this loop
    for(int x=0; x<m;x++){
      for(int y=0; y<n; y++){
        if(board[x][y] == 'O')
          board[x][y]='X';
        if(board[x][y]=='1') board[x][y]='O';
      }
    }
  }
  // flip c1 to c2
  void bfs(char[][] board, int x, int y, char c1, char c2){
    int m = board.length;
    int n = board[0].length;
    if(board[x][y] != c1) return;
    board[x][y] = c2;
    LinkedList<Integer> queue = new LinkedList<Integer>();
    queue.add(x*n+y); // encode tow dim (x, y) to one dim (x*n+y)
    while(!queue.isEmpty()){
      int idx = queue.poll();
      int row = idx / n; // decode
      int col = idx % n; // decode
      if(row-1 >=0 && board[row-1][col] == c1){
        board[row-1][col] = c2;
        queue.add((row-1)*n+col);
      }
      if(row+1 <m && board[row+1][col] == c1){
        board[row+1][col] = c2;
        queue.add((row+1)*n+col);
      }
      if(col-1 >=0 && board[row][col-1] == c1){
        board[row][col-1] = c2;
        queue.add(row*n+col-1);
      }
      if(col+1 <n && board[row][col+1] == c1){
        board[row][col+1] = c2;
        queue.add(row*n+col+1);
      }
    }
  }
}