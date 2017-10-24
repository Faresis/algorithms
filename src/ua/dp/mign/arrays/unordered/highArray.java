package ua.dp.mign.arrays.unordered;

// highArray.java
// demonstrates array class with high-level interface
// to run this program: C>java HighArrayApp
////////////////////////////////////////////////////////////////
class HighArray {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //-----------------------------------------------------------
    public HighArray(int max)         // constructor
    {
        a = new long[max];             // create the array
        nElems = 0;                    // no items yet
    }

    //-----------------------------------------------------------
    public long getMax() {
        int max = findMax();
        return max >= 0 ? a[max] : max;
    }

    //-----------------------------------------------------------
    public long removeMax() {
        int max = findMax();
        return max >= 0 ? delete(max) : max;
    }

    //-----------------------------------------------------------
    private int findMax() {
        if (a.length == 0)
            return -1;

        int max = 0;                      // init default value
        for (int j = 1; j < nElems; j++)     // traverse all elements
            if (a[j] > a[max])           // compare with max
                max = j;                  // take bigger one
        return max;                     // return found
    }

    //-----------------------------------------------------------
    public boolean find(long searchKey) {                              // find specified value
        int j;
        for (j = 0; j < nElems; j++)            // for each element,
            if (a[j] == searchKey)           // found item?
                break;                       // exit loop before end
        if (j == nElems)                    // gone to end?
            return false;                   // yes, can't find it
        else
            return true;                    // no, found it
    }  // end find()

    //-----------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        a[nElems] = value;             // insert it
        nElems++;                      // increment size
    }

    //-----------------------------------------------------------
    public boolean delete(long value) {
        int j;
        for (j = 0; j < nElems; j++)        // look for it
            if (value == a[j])
                break;
        if (j == nElems)                  // can't find it
            return false;
        else                           // found it
        {
            delete(j);
            return true;
        }
    }

    //-----------------------------------------------------------
    private long delete(int index) {
        if (index < 0 || index >= nElems)
            return -1;

        long result = a[index];
        for (int k = index; k < nElems; k++) // move higher ones down
            a[k] = a[k + 1];
        nElems--;                       // decrement size
        return result;
    }

    //-----------------------------------------------------------
    public void display()             // displays array contents
    {
        for (int j = 0; j < nElems; j++)       // for each element,
            System.out.print(a[j] + " ");  // display it
        System.out.println("");
    }

    //-----------------------------------------------------------
    public int size() {
        return nElems;
    }
    //-----------------------------------------------------------
    public void noDups() {
        int numRemoved = 0;
        for (int i = 0; i < nElems; i++) {
            if (a[i] == -1) {
                continue;
            }
            for (int j = i + 1; j < nElems; j++) {
                if (a[j] == a[i]) {
                    a[j] = -1;
                    numRemoved++;
                }
            }
        }
        for (int i = 0; i < nElems; i++) {
            if (a[i] == -1) {
                int shift = -1;
                for (int j = i + 1; j < nElems; j++) {
                    if (a[j] == -1) {
                        shift--;
                        continue;
                    }
                    a[j + shift] = a[j];
                }
            }
        }
        nElems = nElems - numRemoved;
    }
}  // end class HighArray

////////////////////////////////////////////////////////////////
class HighArrayApp {
    public static void main(String[] args) {
        int maxSize = 100;            // array size
        HighArray arr;                // reference to array
        arr = new HighArray(maxSize); // create the array

        System.out.println("Max value for empty array is: " + arr.getMax());
        System.out.println("Deleting max is: " + arr.removeMax());

        arr.insert(77);               // insert 10 items
        arr.insert(44);
        arr.insert(77);
        arr.insert(88);
        arr.insert(99);
        arr.insert(77);
        arr.insert(99);
        arr.insert(99);
        arr.insert(44);
        arr.insert(22);
        arr.insert(55);
        arr.insert(88);
        arr.insert(22);
        arr.insert(99);
        arr.insert(22);
        arr.insert(88);
        arr.insert(99);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(88);
        arr.insert(66);
        arr.insert(33);

        System.out.println("With duplicates: ");
        arr.display();                // display items with duplicates

        arr.noDups();

        System.out.println("Without duplicates: ");
        arr.display();                // display items without duplicates

        System.out.println("Max value after insertion: " + arr.getMax());

        int searchKey = 35;           // search for item
        if (arr.find(searchKey))
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        arr.delete(00);               // delete 3 items
        arr.delete(55);
        arr.delete(99);

        arr.display();                // display items again

        System.out.println("Max value after deletion: " + arr.getMax());
        System.out.println("Removing max: " + arr.removeMax());
        System.out.println("Max value after second deletion: " + arr.getMax());
        HighArray sorted = new HighArray(arr.size());
        while (arr.size() > 0)
            sorted.insert(arr.removeMax());
        sorted.display();
    }  // end main()
}  // end class HighArrayApp
