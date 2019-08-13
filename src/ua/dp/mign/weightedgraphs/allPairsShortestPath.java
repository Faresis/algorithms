////////////////////////////////////////////////////////////////
class Vertex {
  public char label;        // label (e.g. 'A')
  public boolean isInTree;
// -------------------------------------------------------------
  public Vertex(char lab) {
    label = lab;
    isInTree = false;
  }
// -------------------------------------------------------------
}  // end class Vertex
////////////////////////////////////////////////////////////////
class Graph {
  private final int MAX_VERTS = 20;
  private final int INFINITY = 1000000;
  private Vertex vertexList[]; // list of vertices
  private int adjMat[][];      // adjacency matrix
  private int nVerts;          // current number of vertices
// -------------------------------------------------------------
  public Graph() {
    vertexList = new Vertex[MAX_VERTS];
                                         // adjacency matrix
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    nVerts = 0;
    for(int j=0; j<MAX_VERTS; j++)     // set adjacency
      for(int k=0; k<MAX_VERTS; k++)  //     matrix
        adjMat[j][k] = INFINITY;     //     to infinity
  }  // end constructor
// -------------------------------------------------------------
  public void addVertex(char lab) {
    vertexList[nVerts++] = new Vertex(lab);
  }
// -------------------------------------------------------------
  public void addEdge(int start, int end, int weight) {
    adjMat[start][end] = weight;  // (directed)
  }
// -------------------------------------------------------------
  public void displayMatrix() {
    for (int i=0; i<nVerts; i++) {
      for (int j=0; j<nVerts; j++) {
        int val = adjMat[i][j];
        if (val == INFINITY) {
          System.out.print("inf ");
        } else {
          System.out.print(adjMat[i][j] + " ");
        }
      }
      System.out.println();
    }
  }
// -------------------------------------------------------------
  public void floyds() {
    for (int mid=0; mid<nVerts; mid++) {
      for (int end=0; end<nVerts; end++) {
        int lastHalf = adjMat[mid][end];
        if (lastHalf != INFINITY) {
          for (int start=0; start<nVerts; start++) {
            if (start == end) continue;
            
            int firstHalf = adjMat[start][mid];
            if (firstHalf != INFINITY) {
              int oldPath = adjMat[start][end];
              int newPath = firstHalf+lastHalf;
              if (oldPath > newPath) {
                adjMat[start][end] = newPath;
              }
            }
          }
        }
      }
    }
  }
// -------------------------------------------------------------
}  // end class Graph
////////////////////////////////////////////////////////////////
class PathApp {
  public static void main(String[] args) {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');     // 0  (start)
    theGraph.addVertex('B');     // 1
    theGraph.addVertex('C');     // 2
    theGraph.addVertex('D');     // 3
    theGraph.addVertex('E');     // 4

    theGraph.addEdge(0, 1, 50);  // AB 50
    theGraph.addEdge(0, 3, 80);  // AD 80
    theGraph.addEdge(1, 2, 60);  // BC 60
    theGraph.addEdge(1, 3, 90);  // BD 90
    theGraph.addEdge(2, 4, 40);  // CE 40
    theGraph.addEdge(3, 2, 20);  // DC 20
    theGraph.addEdge(3, 4, 70);  // DE 70
    theGraph.addEdge(4, 1, 50);  // EB 50

    System.out.println("Initial matrix: ");
    theGraph.displayMatrix();
    System.out.println();
    
    System.out.println("All pairs shortest path matrix: ");
    theGraph.floyds();
    theGraph.displayMatrix();
  }  // end main()
}  // end class PathApp
////////////////////////////////////////////////////////////////

