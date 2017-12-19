package ua.dp.mign.sorting.simple;

class ArrayInlineNoDuplicates {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayInlineNoDuplicates(int max)          // constructor
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
    public void insertionSort() {
        int in, out;

        final int DUP_KEY = -1;
        for (out = 1; out < nElems; out++)     // out is dividing line
        {
            long temp = a[out];            // remove marked item
            in = out;                      // start shifts at out
            while (in > 0 && a[in - 1] >= 0 && a[in - 1] >= temp) // until one is smaller,
            {
               if (a[in - 1] == temp) {
                   temp = DUP_KEY;
               }
                a[in] = a[in - 1];            // shift item to right
                --in;                       // go left one position
            }
            a[in] = temp;                  // insert marked item
        }  // end for

        int shift = 0;
        for (int i = 0; i < nElems; i++) {
            if (a[i] == DUP_KEY) {
                shift--;
                continue;
            }
            if (shift < 0) {
                a[i + shift] = a[i];
            }
        }
        nElems += shift;
    }  // end insertionSort()
//--------------------------------------------------------------
}  // end class ArrayIns

////////////////////////////////////////////////////////////////
class InlineNoDuplicatesApp {
    public static void main(String[] args) {
        int maxSize = 100;            // array size
        ArrayInlineNoDuplicates arr;                 // reference to array
        arr = new ArrayInlineNoDuplicates(maxSize);  // create the array

        arr.insert(77);               // insert 10 items
        arr.insert(77);               // insert 10 items
        arr.insert(77);               // insert 10 items
        arr.insert(77);               // insert 10 items
        arr.insert(99);
        arr.insert(66);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(99);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        arr.display();                // display items

        arr.insertionSort();          // insertion-sort them

        arr.display();                // display them again
    }  // end main()
}  // end class InsertSortApp
