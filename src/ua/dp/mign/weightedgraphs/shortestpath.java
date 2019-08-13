////////////////////////////////////////////////////////////////
class DistPar {                      // items stored in sPath array
  public int distance;   // distance from start to this vertex
  public int parentVert; // current parent of this vertex
// -------------------------------------------------------------
  public DistPar(int pv, int d) {
    distance = d;
    parentVert = pv;
  }
// -------------------------------------------------------------
}  // end class DistPar
///////////////////////////////////////////////////////////////
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
  private int nTree;           // number of verts in tree
  private DistPar sPath[];     // array for shortest-path data
  private int currentVert;     // current vertex
  private int startToCurrent;  // distance to currentVert
// -------------------------------------------------------------
  public Graph() {
    vertexList = new Vertex[MAX_VERTS];
                                         // adjacency matrix
    adjMat = new int[MAX_VERTS][MAX_VERTS];
    nVerts = 0;
    nTree = 0;
    for(int j=0; j<MAX_VERTS; j++)     // set adjacency
      for(int k=0; k<MAX_VERTS; k++)  //     matrix
        adjMat[j][k] = INFINITY;     //     to infinity
    sPath = new DistPar[MAX_VERTS];    // shortest paths
  }  // end constructor
// -------------------------------------------------------------
  public char get(int idx) {
    return vertexList[idx].label;
  }
// -------------------------------------------------------------
  public void addVertex(char lab) {
    vertexList[nVerts++] = new Vertex(lab);
  }
// -------------------------------------------------------------
  public void addEdge(int start, int end, int weight) {
    adjMat[start][end] = weight;  // (directed)
  }
// -------------------------------------------------------------
  public void path(int start) {
    int startTree = start;             // start at vertex 0
    vertexList[startTree].isInTree = true;
    nTree = 1;                     // put it in tree

    // transfer row of distances from adjMat to sPath
    for(int j=0; j<nVerts; j++) {
      int tempDist = adjMat[startTree][j];
      sPath[j] = new DistPar(startTree, tempDist);
    }

    // until all vertices are in the tree
    while(nTree < nVerts) {
      int indexMin = getMin(start);    // get minimum from sPath
      int minDist = sPath[indexMin].distance;

      if (minDist == INFINITY) {
        System.out.println("There are unreachable vertices");
        break;                   // sPath is complete
      }
      else
      {                        // reset currentVert
        currentVert = indexMin;  // to closest vert
        startToCurrent = minDist;
        // minimum distance from startTree is
        // to currentVert, and is startToCurrent
      }
      // put current vertex in tree
      vertexList[currentVert].isInTree = true;
      nTree++;
      adjust_sPath();             // update sPath[] array
    }  // end while(nTree<nVerts)

    displayPaths();                // display sPath[] contents

    nTree = 0;                     // clear tree
    for(int j=0; j<nVerts; j++)
      vertexList[j].isInTree = false;
  }  // end path()
// -------------------------------------------------------------
  public int getMin(int start) {
    int minDist = INFINITY;        // assume minimum
    int indexMin = start;
    for(int j=0; j<nVerts; j++) {
      if( !vertexList[j].isInTree &&  // smaller than old one
          sPath[j].distance < minDist ) {
        minDist = sPath[j].distance;
        indexMin = j;            // update minimum
      }
    }  // end for
    return indexMin;               // return index of minimum
  }  // end getMin()
// -------------------------------------------------------------
  public void adjust_sPath() {
    // adjust values in shortest-path array sPath
    int column = 0;                // skip starting vertex
    while(column < nVerts) {
      // if this column's vertex already in tree, skip it
      if( vertexList[column].isInTree ) {
        column++;
        continue;
      }
      // calculate distance for one sPath entry
      // get edge from currentVert to column
      int currentToFringe = adjMat[currentVert][column];
      // add distance from start
      int startToFringe = startToCurrent + currentToFringe;
      // get distance of current sPath entry
      int sPathDist = sPath[column].distance;

      // compare distance from start with sPath entry
      if(startToFringe < sPathDist) {
        sPath[column].parentVert = currentVert;
        sPath[column].distance = startToFringe;
      }
      column++;
    }  // end while(column < nVerts)
  }  // end adjust_sPath()
// -------------------------------------------------------------
  public void displayPaths() {
    for(int j=0; j<nVerts; j++) {
      System.out.print(vertexList[j].label + "="); // B=
      if(sPath[j].distance == INFINITY)
        System.out.print("inf");                  // inf
      else
        System.out.print(sPath[j].distance);      // 50
      char parent = vertexList[ sPath[j].parentVert ].label;
      System.out.print("(" + parent + ") ");       // (A)
    }
    System.out.println("");
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

    System.out.println("Shortest paths");
    for (int start = 0; start < 5; start++) {
      System.out.println(theGraph.get(start));
      theGraph.path(start);             // shortest paths
      System.out.println();
    }
  }  // end main()
}  // end class PathApp
////////////////////////////////////////////////////////////////

