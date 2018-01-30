package ua.dp.mign.sorting.measurements;

class ArraySh {
    private long[] theArray;          // ref to array theArray
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArraySh(int max)           // constructor
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
    public void display()             // displays array contents
    {
        System.out.print("A=");
        for (int j = 0; j < nElems; j++)    // for each element,
            System.out.print(theArray[j] + " ");  // display it
        System.out.println("");
    }

    //--------------------------------------------------------------
    public void shellSort() {
        int inner, outer;
        long temp;

        int h = 1;                     // find initial value of h
        while (h <= nElems / 3)
            h = h * 3 + 1;                // (1, 4, 13, 40, 121, ...)

        while (h > 0)                     // decreasing h, until h=1
        {
            // h-sort the file
            for (outer = h; outer < nElems; outer++) {
                temp = theArray[outer];
                inner = outer;
                // one subpass (eg 0, 4, 8)
                while (inner > h - 1 && theArray[inner - h] >= temp) {
                    theArray[inner] = theArray[inner - h];
                    inner -= h;
                }
                theArray[inner] = temp;
            }  // end for
            h = (h - 1) / 3;              // decrease h
        }  // end while(h>0)
    }  // end shellSort()
//--------------------------------------------------------------
}  // end class ArraySh

////////////////////////////////////////////////////////////////
class ShellSortApp {
    public static void main(String[] args) {
        int maxSize = 100_000;            // array size
        // inverse data
        ArraySh inverse = new ArraySh(maxSize);
        fillInversely(maxSize, inverse);
        System.out.println("Inverse data.");
        measure(inverse);

        // random data
        ArraySh random = new ArraySh(maxSize);  // create the array
        fillRandomly(maxSize, random);
        System.out.println("Random data.");
        measure(random);

        // sorted data
        ArraySh sortedData = new ArraySh(maxSize);
        fillSorted(maxSize, sortedData);
        System.out.println("Sorted data.");
        measure(sortedData);
    }  // end main()

    private static void fillInversely(int maxSize, ArraySh inverse) {
        for (int i = maxSize; i > 0; --i) {
            inverse.insert(i);
        }
    }

    private static void fillRandomly(int maxSize, ArraySh random) {
        for (int j = 0; j < maxSize; j++) { // fill array with
            // random numbers
            long n = (long) (Math.random() * (maxSize - 1));
            random.insert(n);
        }
    }

    private static void fillSorted(int maxSize, ArraySh sorted) {
        for (int i = 0; i < maxSize; i++) {
            sorted.insert(i);
        }
    }

    private static void measure(ArraySh arr) {
        long start = System.nanoTime();
        arr.shellSort();   // sort them
        long end = System.nanoTime();

        System.out.printf("Time elapsed: %d milliseconds.\n", (end - start) / 1_000_000);
    }
}  // end class ShellSortApp
////////////////////////////////////////////////////////////////
