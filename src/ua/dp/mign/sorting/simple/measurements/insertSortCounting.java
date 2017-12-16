package ua.dp.mign.sorting.simple.measurements;

class ArrayInsCounting {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayInsCounting(int max)          // constructor
    {
        a = new long[max];                 // create the array
        nElems = 0;                        // no items yet
    }

    //--------------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        a[nElems] = value;             // insert it
        nElems++;                      // increment size
    }

    //--------------------------------------------------------------
    public void display()             // displays array contents
    {
        for (int j = 0; j < nElems; j++)       // for each element,
            System.out.print(a[j] + " ");  // display it
        System.out.println("");
    }

    //--------------------------------------------------------------
    public Stat insertionSort() {
        int in, out;

        int copies = 0, comparisons = 0;

        for (out = 1; out < nElems; out++)     // out is dividing line
        {
            long temp = a[out];            // remove marked item

            in = out;                      // start shifts at out
            while (in > 0 && ++comparisons > 0 && a[in - 1] >= temp) // until one is smaller,
            {
                a[in] = a[in - 1];            // shift item to right
                --in;                       // go left one position
                copies++;
            }
            a[in] = temp;                  // insert marked item
        }  // end for
        return new Stat(copies, comparisons);
    }  // end insertionSort()
//--------------------------------------------------------------
}  // end class ArrayIns

////////////////////////////////////////////////////////////////
class InsertSortCountingApp {
    public static void main(String[] args) {
        int maxSize = 100;            // array size
        ArrayInsCounting arr;                 // reference to array
        arr = new ArrayInsCounting(maxSize);  // create the array

        arr.insert(77);               // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        arr.display();                // display items
        Stat stat = arr.insertionSort();          // insertion-sort them
        arr.display();                // display them again
        System.out.println(stat);
        arr.insert(55);
        arr.display();
        stat = arr.insertionSort();
        arr.display();
        System.out.println(stat);

        arr = new ArrayInsCounting(maxSize);
        arr.insert(99);
        arr.insert(88);
        arr.insert(77);
        arr.insert(66);
        arr.insert(55);
        arr.insert(44);
        arr.insert(33);
        arr.insert(22);
        arr.insert(11);
        arr.insert(00);
        arr.display();
        stat = arr.insertionSort();
        arr.display();
        System.out.println(stat);
    }  // end main()
}  // end class InsertSortApp

final class Stat {
    private final int copies;
    private final int comparisons;

    Stat(int copies, int comparisons) {
        this.copies = copies;
        this.comparisons = comparisons;
    }

    public int getCopies() {
        return copies;
    }

    public int getComparisons() {
        return comparisons;
    }

    @Override
    public String toString() {
        return String.format("Copies: %d, Comparisons: %d", copies, comparisons);
    }
}
