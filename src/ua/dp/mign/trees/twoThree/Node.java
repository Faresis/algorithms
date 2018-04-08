package ua.dp.mign.trees.twoThree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

////////////////////////////////////////////////////////////////
class Node {
    private static final int ORDER = 3;
    private int numItems;
    private Node parent;
    private Node childArray[] = new Node[ORDER];
    private DataItem itemArray[] = new DataItem[ORDER - 1];

    // -------------------------------------------------------------
    // connect child to this node
    public void connectChild(int childNum, Node child) {
        childArray[childNum] = child;
        if (child != null)
            child.parent = this;
    }

    public void connectChild(Node child) {
        int childNum = getChildNum(child.getMax());
        this.connectChild(childNum, child);
    }

    public Node getNextChild(long theValue) {
        return getChild(getChildNum(theValue));
    }

    private int getChildNum(long theValue) {
        int j;
        int numItems = this.getNumItems();
        for (j = 0; j < numItems; j++)
        {
            if (theValue < this.getItem(j).getData())
                return j;
        }
        return j;
    }

    private long getMax() {
        return getItem(numItems - 1).getData();
    }

    // -------------------------------------------------------------
    // disconnect child from this node, return it
    public Node disconnectChild(int childNum) {
        Node tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public void disconnectChildren() {
        Arrays.setAll(childArray, (i) -> null);
    }

    // -------------------------------------------------------------
    public Node getChild(int childNum) {
        return childArray[childNum];
    }

    public LinkedList<Node> getChildren() {
        return Arrays.stream(childArray).filter(Objects::nonNull).collect(Collectors.toCollection(LinkedList::new));
    }

    // -------------------------------------------------------------
    public Node getParent() {
        return parent;
    }

    // -------------------------------------------------------------
    public boolean isLeaf() {
        return (childArray[0] == null) ? true : false;
    }

    // -------------------------------------------------------------
    public int getNumItems() {
        return numItems;
    }

    // -------------------------------------------------------------
    public DataItem getItem(int index)   // get DataItem at index
    {
        return itemArray[index];
    }

    // -------------------------------------------------------------
    public boolean isFull() {
        return (numItems == ORDER - 1) ? true : false;
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    // -------------------------------------------------------------
    public int findItem(long key)       // return index of
    {                                    // item (within node)
        for (int j = 0; j < ORDER - 1; j++)         // if found,
        {                                 // otherwise,
            if (itemArray[j] == null)          // return -1
                break;
            else if (itemArray[j].dData == key)
                return j;
        }
        return -1;
    }  // end findItem

    // -------------------------------------------------------------
    public int insertItem(long data) {
        DataItem newItem = new DataItem(data);
        // assumes node is not full
        if (this.isFull()) {
            throw new IllegalStateException("Can't insert into a full node.");
        }
        if (this.isEmpty()) {
            itemArray[0] = newItem;
            numItems++;
            return 0;
        } else {
            long newKey = newItem.dData;
            long oldKey = itemArray[0].getData();
            if (newKey > oldKey) {
                itemArray[1] = newItem;
                numItems++;
                return 1;
            } else {
                itemArray[1] = itemArray[0];
                itemArray[0] = newItem;
                numItems++;
                return 0;
            }
        }
    }  // end insertItem()

    // -------------------------------------------------------------
    public DataItem removeItem()        // remove largest item
    {
        // assumes node not empty
        DataItem temp = itemArray[numItems - 1];  // save item
        itemArray[numItems - 1] = null;           // disconnect it
        numItems--;                             // one less item
        return temp;                            // return item
    }

    // -------------------------------------------------------------
    public void displayNode()           // format "/24/56/74/"
    {
        for (int j = 0; j < numItems; j++)
            itemArray[j].displayItem();   // "/56"
        System.out.println("/");         // final "/"
    }
// -------------------------------------------------------------
}  // end class Node
