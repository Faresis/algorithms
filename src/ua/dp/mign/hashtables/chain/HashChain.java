package ua.dp.mign.hashtables.chain;

// hashChain.java
// demonstrates hash table with separate chaining
// to run this program: C:>java HashChainApp

import java.io.*;

////////////////////////////////////////////////////////////////
class HashTable {
    private Tree[] hashArray;   // array of trees
    private int arraySize;

    // -------------------------------------------------------------
    public HashTable(int size)        // constructor
    {
        arraySize = size;
        hashArray = new Tree[arraySize];  // create array
        for (int j = 0; j < arraySize; j++)          // fill array
            hashArray[j] = new Tree();     // with lists
    }

    // -------------------------------------------------------------
    public void displayTable() {
        for (int j = 0; j < arraySize; j++) // for each cell,
        {
            System.out.println(j + ". "); // display cell number
            hashArray[j].displayTree(); // display tree
        }
    }

    // -------------------------------------------------------------
    public int hashFunc(int key)      // hash function
    {
        return key % arraySize;
    }

    // -------------------------------------------------------------
    public void insert(int key)  // insert data
    {
        int hashVal = hashFunc(key);   // hash the key
        Tree tree = hashArray[hashVal];
        if (tree.isEmpty() || tree.find(key) == null)
            tree.insert(key); // insert at hashVal
    }  // end insert()

    // -------------------------------------------------------------
    public void delete(int key)       // delete a link
    {
        int hashVal = hashFunc(key);   // hash the key
        hashArray[hashVal].delete(key); // delete link
    }  // end delete()

    // -------------------------------------------------------------
    public Node find(int key)         // find link
    {
        int hashVal = hashFunc(key);   // hash the key
        Node theLink = hashArray[hashVal].find(key);  // get link
        return theLink;                // return link
    }
// -------------------------------------------------------------
}  // end class HashTable

////////////////////////////////////////////////////////////////
class HashChainApp {
    public static void main(String[] args) throws IOException {
        int aKey;
        int size, n, keysPerCell = 100;
        // get sizes
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        // make table
        HashTable theHashTable = new HashTable(size);

        for (int j = 0; j < n; j++)         // insert data
        {
            aKey = (int) (java.lang.Math.random() *
                    keysPerCell * size);
            theHashTable.insert(aKey);
        }
        while (true)                    // interact with user
        {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    theHashTable.insert(aKey);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    Node aDataItem = theHashTable.find(aKey);
                    if (aDataItem != null)
                        System.out.println("Found " + aKey);
                    else
                        System.out.println("Could not find " + aKey);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }  // end switch
        }  // end while
    }  // end main()

    //--------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    //-------------------------------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
//--------------------------------------------------------------
}  // end class HashChainApp
////////////////////////////////////////////////////////////////
