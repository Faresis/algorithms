import java.util.*;

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
  static final int INFINITY = 1000000;

  char label;        // label (e.g. 'A')
  boolean isInTree;
  Map<Character, Integer> edges;
// -------------------------------------------------------------
  public Vertex(char lab) {
    label = lab;
    isInTree = false;
    this.edges = new HashMap<Character, Integer>();
  }
// -------------------------------------------------------------
  public void addEdge(char label, int dist) {
    this.edges.put(label, dist);
  }
// -------------------------------------------------------------
  public int getDist(char label) {
    return edges.containsKey(label) ? edges.get(label) : INFINITY;
  }
// -------------------------------------------------------------
}  // end class Vertex
///////////////////////////////////////////////////////////////
class Graph {
  private final int MAX_VERTS = 20;
  private Vertex vertexList[]; // list of vertices
  private int nVerts;          // current number of vertices
  private int nTree;           // number of verts in tree
  private DistPar sPath[];     // array for shortest-path data
  private int currentVert;     // current vertex
  private int startToCurrent;  // distance to currentVert
// -------------------------------------------------------------
  public Graph() {
    vertexList = new Vertex[MAX_VERTS];
    nVerts = 0;
    nTree = 0;
    sPath = new DistPar[MAX_VERTS];    // shortest paths
  }  // end constructor
// -------------------------------------------------------------
  public void addVertex(char lab) {
    vertexList[nVerts++] = new Vertex(lab);
  }
// -------------------------------------------------------------
  public void addEdge(int start, int end, int dist) {
    vertexList[start].addEdge(vertexList[end].label, dist);
  }
// -------------------------------------------------------------
  public void path() {
    int startTree = 0;             // start at vertex 0
    vertexList[startTree].isInTree = true;
    nTree = 1;                     // put it in tree

    for(int j=0; j<nVerts; j++) {
      int tempDist = vertexList[startTree].getDist(vertexList[j].label);
      sPath[j] = new DistPar(startTree, tempDist);
    }

    // until all vertices are in the tree
    while(nTree < nVerts) {
      int indexMin = getMin();    // get minimum from sPath
      int minDist = sPath[indexMin].distance;

      if(minDist == Vertex.INFINITY) {
        System.out.println("There are unreachable vertices");
        break;                   // sPath is complete
      }
      else
      {                        // reset currentVert
        currentVert = indexMin;  // to closest vert
        startToCurrent = sPath[indexMin].distance;
        // minimum distance from startTree is
        // to currentVert, and is startToCurrent
      }
      // put current vertex in tree
      vertexList[currentVert].isInTree = true;
      nTree++;
      update_sPath();             // update sPath[] array
    }  // end while(nTree<nVerts)

    displayPaths();                // display sPath[] contents

    nTree = 0;                     // clear tree
    for(int j=0; j<nVerts; j++)
      vertexList[j].isInTree = false;
}  // end path()
// -------------------------------------------------------------
  public int getMin() {
    int minDist = Vertex.INFINITY;        // assume minimum
    int indexMin = 0;
    for(int j=1; j<nVerts; j++) {
      if( !vertexList[j].isInTree &&  // smaller than old one
          sPath[j].distance < minDist ) {
        minDist = sPath[j].distance;
        indexMin = j;            // update minimum
      }
    }  // end for
    return indexMin;               // return index of minimum
  }  // end getMin()
// -------------------------------------------------------------
  public void update_sPath() {
    // adjust values in shortest-path array sPath
    int column = 1;                // skip starting vertex
    while(column < nVerts) {
    // if this column's vertex already in tree, skip it
      if( vertexList[column].isInTree ) {
        column++;
        continue;
      }
      // calculate distance for one sPath entry
      // get edge from currentVert to column
      int currentToFringe = vertexList[currentVert].getDist(vertexList[column].label);
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
  }  // end update_sPath()
// -------------------------------------------------------------
  public void displayPaths() {
    for(int j=0; j<nVerts; j++) {
      System.out.print(vertexList[j].label + "="); // B=
      if(sPath[j].distance == Vertex.INFINITY)
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
    theGraph.path();             // shortest paths
    System.out.println();
  }  // end main()
}  // end class PathApp
////////////////////////////////////////////////////////////////

