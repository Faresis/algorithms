package ua.dp.mign.sorting.advanced;

class ArrayRad {
    private long[] theArray;          // ref to array theArray
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayRad(int max)          // constructor
    {
        theArray = new long[max];      // create the array
        nElems = 0;                    // no items yet
    }

    //--------------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        theArray[nElems] = value;      // insert it
        nElems++;                      // increment size
    }

    //--------------------------------------------------------------
    public int size()                 // return number of items
    {
        return nElems;
    }

    //--------------------------------------------------------------
    public void display()             // displays array contents
    {
        System.out.print("A=");
        for (int j = 0; j < nElems; j++)    // for each element,
            System.out.print(theArray[j] + " ");  // display it
        System.out.println("");
    }
}  // end class ArrayRad

class RadixSortApp {
    public static void main(String[] args) {
        int maxSize = 10;            // array size
        ArrayRad arr = new ArrayRad(maxSize);

        for (int j = 0; j < maxSize; j++)  // fill array with
        {                          // random numbers
            long n = (int) (Math.random() * 199);
            arr.insert(n);
        }
        arr.display();                // display unsorted array
    }  // end main()
}
