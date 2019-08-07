import java.util.*;

////////////////////////////////////////////////////////////////
class Link {
  public Vertex vertex;
  public Link next;              // next link in list
// -------------------------------------------------------------
  public Link(Vertex vertex) {
    this.vertex = vertex;
  }
// -------------------------------------------------------------
  public void displayLink() {
    System.out.print("{" + vertex.label + "} ");
  }
}  // end class Link
////////////////////////////////////////////////////////////////
class LinkList {
  private Link first;            // ref to first link on list
// -------------------------------------------------------------
  public LinkList() {
    first = null;               // no links on list yet
  }
// -------------------------------------------------------------
  public void insertFirst(Vertex vertex) {
    Link newLink = new Link(vertex);
    newLink.next = first;       // it points to old first link
    first = newLink;            // now first points to this
  }
// -------------------------------------------------------------
  public Vertex getAdjUnvisitedVertex() {
    Link current = first;
    while (current != null) {
      if (current.vertex.wasVisited == false)
        return current.vertex;
      current = current.next;
    }
    return null;
  }
// -------------------------------------------------------------
   public void displayList() {
     System.out.print("List (first-->last): ");
     Link current = first;       // start at beginning of list
     while(current != null) {
       current.displayLink();   // print data
       current = current.next;  // move to next link
     }
     System.out.println("");
   }
// -------------------------------------------------------------
}  // end class LinkList
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
class StackX {
  private final int SIZE = 20;
  private Vertex[] st;
  private int top;
// ------------------------------------------------------------
  public StackX() {
    st = new Vertex[SIZE];    // make array
    top = -1;
  }
// ------------------------------------------------------------
  public void push(Vertex j) {
    st[++top] = j;
  }
// ------------------------------------------------------------
  public Vertex pop() {
    return st[top--];
  }
// ------------------------------------------------------------
  public Vertex peek() {
    return st[top];
  }
// ------------------------------------------------------------
  public boolean isEmpty() {
    return (top == -1);
  }
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
  private Map<Character, LinkList> adjList;
  private int nVerts;          // current number of vertices
  private StackX theStack;
// ------------------------------------------------------------
  public Graph() {
    vertexList = new Vertex[MAX_VERTS];
    adjList = new HashMap<Character, LinkList>();
    nVerts = 0;
    theStack = new StackX();
  }  // end constructor
// ------------------------------------------------------------
  public void addVertex(char lab) {
    vertexList[nVerts++] = new Vertex(lab);
  }
// ------------------------------------------------------------
  public void addEdge(int start, int end) {
    Character s = Character.valueOf(vertexList[start].label);
    Character e = Character.valueOf(vertexList[end].label);
    LinkList startList = adjList.get(s);
    LinkList endList = adjList.get(e);
    if (startList == null) {
      startList = new LinkList();
      adjList.put(s, startList);
    }
    if (endList == null) {
      endList = new LinkList();
      adjList.put(e, endList);
    }

    startList.insertFirst(vertexList[end]);
    endList.insertFirst(vertexList[start]);
  }
// ------------------------------------------------------------
  public void displayVertex(Vertex v) {
    System.out.print(v.label);
  }
// ------------------------------------------------------------
  public void dfs() {
    Vertex first = vertexList[0];
    first.wasVisited = true;          // mark it
    displayVertex(first);             // display it
    theStack.push(first);                 // push it

    while( !theStack.isEmpty() ) {
      // get an unvisited vertex adjacent to stack top
      Vertex v = getAdjUnvisitedVertex( theStack.peek() );
      if(v == null)                    // if no such vertex,
        theStack.pop();
      else                           // if it exists,
      {
        v.wasVisited = true;  // mark it
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
  public Vertex getAdjUnvisitedVertex(Vertex v) {
    return adjList.get(v.label).getAdjUnvisitedVertex();
  }  // end getAdjUnvisitedVertex()
// ------------------------------------------------------------
}  // end class Graph
////////////////////////////////////////////////////////////////
class DfsAdjacencyListApp {
  public static void main(String[] args) {
    Graph theGraph = new Graph();
    theGraph.addVertex('A');    // 0  (start for dfs)
    theGraph.addVertex('B');    // 1
    theGraph.addVertex('C');    // 2
    theGraph.addVertex('D');    // 3
    theGraph.addVertex('E');    // 4

    theGraph.addEdge(0, 1);     // AB
    theGraph.addEdge(1, 2);     // BC
    theGraph.addEdge(0, 3);     // AD
    theGraph.addEdge(3, 4);     // DE

    System.out.print("Visits: ");
    theGraph.dfs();             // depth-first search
    System.out.println();
  }  // end main()
}  // end class DfsAdjacencyListApp
////////////////////////////////////////////////////////////////

