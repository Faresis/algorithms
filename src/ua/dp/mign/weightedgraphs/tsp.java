import java.util.*;
import java.util.function.*;

////////////////////////////////////////////////////////////////
class Vertex {
  static final int INFINITY = 1000000;
  public char label;        // label (e.g. 'A')
  public boolean isInTree;
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
  public String toString() {
    return String.valueOf(label);
  }
// -------------------------------------------------------------
}  // end class Vertex
////////////////////////////////////////////////////////////////
class Graph {
  private final int MAX_VERTS = 20;
  private Vertex vertexList[]; // list of vertices
  private int nVerts;          // current number of vertices
// -------------------------------------------------------------
  public Graph() {
    vertexList = new Vertex[MAX_VERTS];
    nVerts = 0;
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
  public void addEdge(int start, int end, int dist) {
    vertexList[start].addEdge(vertexList[end].label, dist);
    vertexList[end].addEdge(vertexList[start].label, dist);
  }
// -------------------------------------------------------------
  public void displayMatrix() {
    for (int i=0; i<nVerts; i++) {
      System.out.println(vertexList[i].label + " : ");
      System.out.println(vertexList[i].edges);
    }
  }
// -------------------------------------------------------------
  public void anagram(int size, Consumer<Graph> c) {
    if (size == 1) {
      return;
    }
    for (int i=0; i<size; i++) {
      anagram(size-1, c);
      if (size==2) {
        c.accept(this);
      }
      rotate(size);
    }
  }
// -------------------------------------------------------------
  public String getRoute() {
    String result = "";
    for (int i=0; i<nVerts; i++)
      result += vertexList[i].label;
    return result + vertexList[0].label;
  }
// -------------------------------------------------------------
  public int getDistance() {
    int result = 0;
    List<Vertex> route = new ArrayList<>();
    for(int j=0; j<nVerts; j++) {
      route.add(vertexList[j]);
    }
    route.add(vertexList[0]);
    for (int i=1; i<route.size(); i++) {
      Vertex src = route.get(i-1);
      Vertex dst = route.get(i);
      int part = src.getDist(dst.label);
      if (part == Vertex.INFINITY) {
        return -1;
      } else {
        result += part;
      }
    }
    return result;
  }
// -------------------------------------------------------------
  private void rotate(int size) {
    int j;
    int position = nVerts - size;
    Vertex temp = vertexList[position];
    for (j=position+1; j<nVerts; j++)
      vertexList[j-1]=vertexList[j];
    vertexList[j-1] = temp;
  }
// -------------------------------------------------------------
}  // end class Graph
////////////////////////////////////////////////////////////////
class TspApp {
  public static void main(String[] args) {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');     // 0  (start)
    theGraph.addVertex('B');     // 1
    theGraph.addVertex('C');     // 2
    theGraph.addVertex('D');     // 3
    theGraph.addVertex('E');     // 4

    theGraph.addEdge(0, 1, 91);
    theGraph.addEdge(0, 2, 62);
    theGraph.addEdge(0, 3, 55);
    theGraph.addEdge(1, 2, 44);
    theGraph.addEdge(1, 4, 31);
    theGraph.addEdge(2, 3, 52);
    theGraph.addEdge(2, 4, 45);
    theGraph.addEdge(3, 4, 83);

    theGraph.displayMatrix();
    System.out.println();
    Map<String, Integer> routes = new HashMap<>();
    theGraph.anagram(4, g -> routes.put(g.getRoute(), g.getDistance()));
    System.out.println(routes);

    Map.Entry<String, Integer> min = null;
    for (Map.Entry<String, Integer> current : routes.entrySet()) {
      if (current.getValue() < 0) continue;
      if (min == null || current.getValue() < min.getValue()) {
        min = current;
      }
    }
    System.out.println("Minimal distance route: " + min.getKey() + " " + min.getValue());
  }  // end main()
}  // end class PathApp
////////////////////////////////////////////////////////////////

