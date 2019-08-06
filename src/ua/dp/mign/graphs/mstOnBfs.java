////////////////////////////////////////////////////////////////
class Queue {
  private final int SIZE = 20;
  private int[] queArray;
  private int front;
  private int rear;
// -------------------------------------------------------------
  public Queue() {
    queArray = new int[SIZE];
    front = 0;
    rear = -1;
  }
// -------------------------------------------------------------
  public void insert(int j) {
    if(rear == SIZE-1)
      rear = -1;
    queArray[++rear] = j;
  }
// -------------------------------------------------------------
  public int remove() {
    int temp = queArray[front++];
    if(front == SIZE)
      front = 0;
    return temp;
  }
// -------------------------------------------------------------
  public boolean isEmpty() {
    return ( rear+1==front || (front+SIZE-1==rear) );
  }
// -------------------------------------------------------------
}  // end class Queue
////////////////////////////////////////////////////////////////
class Vertex {
  public char label;        // label (e.g. 'A')
  public boolean wasVisited;
// -------------------------------------------------------------
  public Vertex(char lab) {
    label = lab;
    wasVisited = false;
  }
// -------------------------------------------------------------
}  // end class Vertex
////////////////////////////////////////////////////////////////
class Graph {
  private final int MAX_VERTS = 20;
  private Vertex vertexList[]; // list of vertices
  private int adjMat[][];      // adjacency matrix
  private int nVerts;          // current number of vertices
  private Queue theQueue;
// ------------------------------------------------------------
  public Graph() {
    vertexList = new Vertex[MAX_VERTS];
                                          // adjacency matrix
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    nVerts = 0;
    for(int j=0; j<MAX_VERTS; j++)      // set adjacency
      for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
        adjMat[j][k] = 0;
    theQueue = new Queue();
  }  // end constructor
// -------------------------------------------------------------
  public void addVertex(char lab) {
    vertexList[nVerts++] = new Vertex(lab);
  }
// -------------------------------------------------------------
  public void addEdge(int start, int end) {
    adjMat[start][end] = 1;
    adjMat[end][start] = 1;
  }
// -------------------------------------------------------------
  public void displayVertex(int v) {
    System.out.print(vertexList[v].label);
  }
// -------------------------------------------------------------
  public void mst() {
    vertexList[0].wasVisited = true; // mark it
    theQueue.insert(0);              // insert at tail
    int v2;
    int previousUnvisited = 0;

    while( !theQueue.isEmpty() ) {
      int v1 = theQueue.remove();   // remove vertex at head
      while( (v2=getAdjUnvisitedVertex(v1)) != -1 ) {
        vertexList[v2].wasVisited = true;  // mark it
        displayVertex(previousUnvisited);  // display it
        displayVertex(v2);                 // display it
        System.out.println();
        previousUnvisited = v2;
        theQueue.insert(v2);               // insert it
      }   // end while
    }  // end while(queue not empty)

      // queue is empty, so we're done
    for(int j=0; j<nVerts; j++)             // reset flags
      vertexList[j].wasVisited = false;
  }  // end mst()
// -------------------------------------------------------------
  // returns an unvisited vertex adj to v
  public int getAdjUnvisitedVertex(int v) {
    for(int j=0; j<nVerts; j++)
      if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
        return j;
    return -1;
  }  // end getAdjUnvisitedVertex()
// -------------------------------------------------------------
}  // end class Graph
////////////////////////////////////////////////////////////////
class MstOnBfsApp {
  public static void main(String[] args) {
    System.out.println("ABCDE graph. All vertices connected.");
    Graph theGraph = new Graph();
    theGraph.addVertex('A');    // 0  (start for bfs)
    theGraph.addVertex('B');    // 1
    theGraph.addVertex('C');    // 2
    theGraph.addVertex('D');    // 3
    theGraph.addVertex('E');    // 4

    theGraph.addEdge(0, 1);     // AB
    theGraph.addEdge(0, 2);     // AC
    theGraph.addEdge(0, 3);     // AD
    theGraph.addEdge(0, 4);     // AE
    theGraph.addEdge(1, 2);     // BC
    theGraph.addEdge(1, 3);     // BD
    theGraph.addEdge(1, 4);     // BE
    theGraph.addEdge(2, 3);     // CD
    theGraph.addEdge(2, 4);     // CE
    theGraph.addEdge(3, 4);     // DE

    System.out.println("Edges: ");
    theGraph.mst();
    System.out.println();

    //         A
    //       /   \
    //      B  -  C
    //      |     |
    //      D  -  E
    //      |     |
    //      F  -  G
    //      |     |
    //      H  -  I
    Graph nineG = new Graph();
    nineG.addVertex('A');    // 0
    nineG.addVertex('B');    // 1
    nineG.addVertex('C');    // 2
    nineG.addVertex('D');    // 3
    nineG.addVertex('E');    // 4
    nineG.addVertex('F');    // 5
    nineG.addVertex('G');    // 6
    nineG.addVertex('H');    // 7
    nineG.addVertex('I');    // 8

    nineG.addEdge(0, 1);     // AB
    nineG.addEdge(0, 2);     // AC
    nineG.addEdge(1, 2);     // BC
    nineG.addEdge(1, 3);     // BD
    nineG.addEdge(2, 4);     // CE
    nineG.addEdge(3, 4);     // DE
    nineG.addEdge(3, 5);     // DF
    nineG.addEdge(4, 6);     // EG
    nineG.addEdge(5, 7);     // FH
    nineG.addEdge(5, 6);     // FG
    nineG.addEdge(6, 8);     // GI
    nineG.addEdge(7, 8);     // HI

    System.out.println("Edges: ");
    nineG.mst();
    System.out.println();
      
  }  // end main()
}  // end class MstOnBfsApp
////////////////////////////////////////////////////////////////

