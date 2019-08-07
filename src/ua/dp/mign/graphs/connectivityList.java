////////////////////////////////////////////////////////////////
class StackX {
  private final int SIZE = 20;
  private int[] st;
  private int top;
// ------------------------------------------------------------
  public StackX() {
    st = new int[SIZE];    // make array
    top = -1;
  }
// ------------------------------------------------------------
  public void push(int j)   // put item on stack
  { st[++top] = j; }
// ------------------------------------------------------------
  public int pop()          // take item off stack
  { return st[top--]; }
// ------------------------------------------------------------
  public int peek()         // peek at top of stack
  { return st[top]; }
// ------------------------------------------------------------
  public boolean isEmpty()  // true if nothing on stack
  { return (top == -1); }
// ------------------------------------------------------------
}  // end class StackX
////////////////////////////////////////////////////////////////
class Vertex {
  public char label;        // label (e.g. 'A')
  public boolean wasVisited;
// ------------------------------------------------------------
  public Vertex(char lab) {
    label = lab;
    wasVisited = false;
  }
// ------------------------------------------------------------
}  // end class Vertex
////////////////////////////////////////////////////////////////
class Graph {
  private final int MAX_VERTS = 20;
  private Vertex vertexList[]; // list of vertices
  private int adjMat[][];      // adjacency matrix
  private int nVerts;          // current number of vertices
  private StackX theStack;
// ------------------------------------------------------------
  public Graph() {
    vertexList = new Vertex[MAX_VERTS];
                                          // adjacency matrix
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    nVerts = 0;
    for(int y=0; y<MAX_VERTS; y++)      // set adjacency
      for(int x=0; x<MAX_VERTS; x++)   //    matrix to 0
        adjMat[x][y] = 0;
    theStack = new StackX();
  }  // end constructor
// ------------------------------------------------------------
  public void addVertex(char lab) {
    vertexList[nVerts++] = new Vertex(lab);
  }
// ------------------------------------------------------------
  public void addEdge(int start, int end) {
    adjMat[start][end] = 1;
  }
// ------------------------------------------------------------
  public void displayVertex(int v) {
    System.out.print(vertexList[v].label);
  }
// ------------------------------------------------------------
  public void dfs(int start) {                                 // begin at vertex 0
    vertexList[start].wasVisited = true;  // mark it
    displayVertex(start);                 // display it
    theStack.push(start);                 // push it

    while( !theStack.isEmpty() )      // until stack empty,
    {
      // get an unvisited vertex adjacent to stack top
      int v = getAdjUnvisitedVertex( theStack.peek() );
      if(v == -1)                    // if no such vertex,
        theStack.pop();
      else                           // if it exists,
      {
        vertexList[v].wasVisited = true;  // mark it
        displayVertex(v);                 // display it
        theStack.push(v);                 // push it
      }
    }  // end while

    // stack is empty, so we're done
    for(int j=0; j<nVerts; j++)          // reset flags
      vertexList[j].wasVisited = false;
  }  // end dfs
// ------------------------------------------------------------
  // returns an unvisited vertex adj to v
  public int getAdjUnvisitedVertex(int v) {
    for(int j=0; j<nVerts; j++)
      if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
        return j;
    return -1;
  }  // end getAdjUnvisitedVertex()
// ------------------------------------------------------------
  public void displayTable() {
    for (int i=0; i<5; i++) {
      for (int j=0; j<5; j++) {
        System.out.print(adjMat[i][j] + " ");
      }
      System.out.println();
    }
  }
// ------------------------------------------------------------
  public void buildConnections() {
    for (int mid=0; mid<5; mid++) {
      for (int end=0; end<5; end++) {
        if (adjMat[mid][end] == 1) {
          for (int start=0; start<5; start++) {
            if (adjMat[start][mid] == 1)
              adjMat[start][end] = 1;
          }
        }
      }
    }
  }
// ------------------------------------------------------------
  public void displayConnectivity() {
    for (int v=0; v<5; v++) {
      dfs(v);
      System.out.println();
    }
  }
// ------------------------------------------------------------
}  // end class Graph
////////////////////////////////////////////////////////////////
class ConnectivityListApp {
  public static void main(String[] args) {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');    // 0  (start for dfs)
    theGraph.addVertex('B');    // 1
    theGraph.addVertex('C');    // 2
    theGraph.addVertex('D');    // 3
    theGraph.addVertex('E');    // 4

    theGraph.addEdge(0, 2);     // AC
    theGraph.addEdge(1, 0);     // BA
    theGraph.addEdge(1, 4);     // BE
    theGraph.addEdge(3, 4);     // DE
    theGraph.addEdge(4, 2);     // EC

    theGraph.displayConnectivity();
  }  // end main()
}  // end class ConnectivityListApp
////////////////////////////////////////////////////////////////

