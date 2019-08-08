////////////////////////////////////////////////////////////////
class StackX {
  private final int SIZE = 80;
  private Vertex[] st;
  private int top;
// ------------------------------------------------------------
  public StackX() {
    st = new Vertex[SIZE];    // make array
    top = -1;
  }
// ------------------------------------------------------------
  public void push(Vertex j)   // put item on stack
  { st[++top] = j; }
// ------------------------------------------------------------
  public Vertex pop()          // take item off stack
  { return st[top--]; }
// ------------------------------------------------------------
  public Vertex peek()         // peek at top of stack
  { return st[top]; }
// ------------------------------------------------------------
  public boolean isEmpty()  // true if nothing on stack
  { return (top == -1); }
// ------------------------------------------------------------
  public int size() {
    return top+1;
  }
// ------------------------------------------------------------
}  // end class StackX
////////////////////////////////////////////////////////////////
class Vertex {
  int[][] moves = {{-2,-1}, {-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}};
  int currentMove = 0;
  boolean wasVisited;
  int row;
  int col;

  Vertex(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public String toString() {
    return "Row: " + row + " Col: " + col;
  }

  public int[] getNextMove() {
    return currentMove < moves.length ? moves[currentMove++] : null;
  }

  public void resetMoves() {
    currentMove = 0;
  }
}
////////////////////////////////////////////////////////////////
class Graph {
  private final int size;
  private Vertex[][] board;    
// ------------------------------------------------------------
  public Graph(int size) {
    this.size = size;
    board = new Vertex[size][size];
    
    for (int row=0; row<size; row++) {
      for (int col=0; col<size; col++) {
        board[row][col] = new Vertex(row, col);
      }
    }
  }  // end constructor
// ------------------------------------------------------------
  public void dfs() {                                 // begin at vertex 0
    StackX theStack = new StackX();
    Vertex first = board[0][0];
    first.wasVisited = true;
    theStack.push(first); 

    while( theStack.size() < this.size*this.size ) {
      // get an unvisited vertex adjacent to stack top
      Vertex v = getAdjUnvisitedVertex( theStack.peek() );
      if(v == null) {                   // if no such vertex,
        v = theStack.pop();
        v.resetMoves();
        v.wasVisited = false;
      }
      else                           // if it exists,
      {
        v.wasVisited = true;
        theStack.push(v);                 // push it
      }
    }  // end while

    // display route
    System.out.println("Display route: ");
    while (!theStack.isEmpty()) {
      System.out.println(theStack.pop());
    }

    // stack is empty, so we're done
    for(int row=0; row<this.size; row++) {         // reset flags
      for(int col=0; col<this.size; col++) {
        board[row][col].wasVisited = false;
        board[row][col].resetMoves();
      }
    }
  }  // end dfs
// ------------------------------------------------------------
  // returns an unvisited vertex adj to v
  public Vertex getAdjUnvisitedVertex(Vertex v) {
    for (int[] move = v.getNextMove(); move != null; move = v.getNextMove()) {
      int newRow = v.row + move[0];
      int newCol = v.col + move[1];
      if (isInBoard(newRow, newCol) && board[newRow][newCol].wasVisited == false) {
        return board[newRow][newCol];
      }
    }
    return null;
  }  // end getAdjUnvisitedVertex()
// ------------------------------------------------------------
  private boolean isInBoard(int row, int col) {
    return row >= 0 && row < this.size && col >= 0 && col < this.size;
  }
// ------------------------------------------------------------
}  // end class Graph
////////////////////////////////////////////////////////////////
class KnightsTourDfsApp {
  public static void main(String[] args) {
    Graph theGraph = new Graph(7);
    theGraph.dfs();
  }  // end main()
}  // end class KnightsTourDfsApp
////////////////////////////////////////////////////////////////

