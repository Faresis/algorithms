package ua.dp.mign.sorting.simple;

class ArrayBub {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayBub(int max)          // constructor
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
    public void bubbleSort() {
        int left, right, in;

        left = 0;
        right = nElems - 1;
        while (right > left) {
            for (in = left; in < right; in++) {
                if (a[in] > a[in + 1]) {
                    swap(in, in + 1);
                }
            }
            right--;
            for (in = right; in > left; in--) {
                if (a[in] < a[in - 1]) {
                    swap(in, in - 1);
                }
            }
            left++;
        }
    }  // end bubbleSort()

    //--------------------------------------------------------------
    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
//--------------------------------------------------------------
}  // end class ArrayBub

////////////////////////////////////////////////////////////////
class BubbleSortApp {
    public static void main(String[] args) {
        int maxSize = 100;            // array size
        ArrayBub arr;                 // reference to array
        arr = new ArrayBub(maxSize);  // create the array

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

        arr.bubbleSort();             // bubble sort them

        arr.display();                // display them again
    }  // end main()
}  // end class BubbleSortApp
////////////////////////////////////////////////////////////////
