package ua.dp.mign.trees.twoThree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.LongConsumer;

////////////////////////////////////////////////////////////////
class Tree23 {
    private Node root = new Node();            // make root node

    // -------------------------------------------------------------
    public int find(long key) {
        Node curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1)
                return childNumber;               // found it
            else if (curNode.isLeaf())
                return -1;                        // can't find it
            else                                 // search deeper
                curNode = curNode.getNextChild(key);
        }  // end while
    }

    // -------------------------------------------------------------
    // insert a DataItem
    public void insert(long dValue) {
        Node curNode = root;
        while(!curNode.isLeaf()) {
            curNode = curNode.getNextChild(dValue);
        }

        if (curNode.isFull()) {
            split(curNode, dValue);
            while (root.getParent() != null) {
                root = root.getParent();
            }
        } else {
            curNode.insertItem(dValue);
        }
    }  // end insert()

    // -------------------------------------------------------------
    private static Node split(Node thisNode, long newValue)     // split the node
    {
        long[] values = {thisNode.getItem(0).getData(), thisNode.getItem(1).getData(), newValue};
        Arrays.sort(values);

        long left = values[0];
        long middle = values[1];
        long right = values[2];

        Node leftNode = thisNode;
        leftNode.removeItem();
        leftNode.removeItem();
        leftNode.insertItem(left);

        Node rightNode = new Node();
        rightNode.insertItem(right);

        Node originalParent = thisNode.getParent();
        if (originalParent == null) {
            originalParent = new Node();
            originalParent.insertItem(middle);
            originalParent.connectChild(0, leftNode);
            originalParent.connectChild(1, rightNode);
            return rightNode;
        } else if (!originalParent.isFull()) {
            originalParent.insertItem(middle);
            reLinkChildren(originalParent, leftNode, rightNode);
            return rightNode;
        } else {
            Node rightParent = split(originalParent, middle);
            if (leftNode.equals(originalParent.getChild(0))) {
                Node childOne = originalParent.disconnectChild(1);
                Node childTwo = originalParent.disconnectChild(2);
                originalParent.connectChild(1, rightNode);
                rightParent.connectChild(0, childOne);
                rightParent.connectChild(1, childTwo);
            } else if (leftNode.equals(originalParent.getChild(1))) {
                Node childTwo = originalParent.disconnectChild(2);
                rightParent.connectChild(0, rightNode);
                rightParent.connectChild(1, childTwo);
            } else if (leftNode.equals(originalParent.getChild(2))) {
                Node childTwo = originalParent.disconnectChild(2);
                rightParent.connectChild(0, childTwo);
                rightParent.connectChild(1, rightNode);
            } else {
                throw new IllegalStateException("Unknown case.");
            }
        }
        return rightNode;
    }  // end split()

    private static void reLinkChildren(Node parent, Node leftChild, Node rightChild) {
        LinkedList<Node> children = parent.getChildren();
        children.add(leftChild);
        children.add(rightChild);
        parent.disconnectChildren();

        while (!children.isEmpty()) {
            parent.connectChild(children.removeFirst());
        }
    }

    // -------------------------------------------------------------
    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    // -------------------------------------------------------------
    private void recDisplayTree(Node thisNode, int level,
                                int childNumber) {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode();               // display this node

        // call ourselves for each child of this node
        int numItems = thisNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Node nextNode = thisNode.getChild(j);
            if (nextNode != null)
                recDisplayTree(nextNode, level + 1, j);
            else
                return;
        }
    }  // end recDisplayTree()

    public long min() {
        Node current = root;
        while (current.getChild(0) != null) {
            current = current.getChild(0);
        }
        return current.getItem(0).getData();
    }

    public void inOrder(LongConsumer consumer) {
        inOrder(root, consumer);
    }

    private static void inOrder(Node node, LongConsumer consumer) {
        if (node == null) {
            return;
        }

        inOrder(node.getChild(0), consumer);
        for(int i = 0; i < node.getNumItems(); i++) {
            consumer.accept(node.getItem(i).getData());
            inOrder(node.getChild(i+1), consumer);
        }
    }

    public void addAll(long[] array) {
        for (long data : array) {
            this.insert(data);
        }
    }
// -------------------------------------------------------------\
}  // end class Tree23
