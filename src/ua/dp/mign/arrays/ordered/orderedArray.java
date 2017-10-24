package ua.dp.mign.arrays.ordered;

// orderedArray.java
// demonstrates ordered array class
// to run this program: C>java OrderedApp
////////////////////////////////////////////////////////////////
class OrdArray {

    private static final SearchArrayPredicate EQUAL = (arr, idx, key) -> arr[idx] == key;
    private static final SearchArrayPredicate FIRST_BIGGER = (arr, idx, key) ->
            arr[idx] > key && (idx == 0 || arr[idx - 1] <= key);

    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //-----------------------------------------------------------
    private OrdArray(long[] source) {
        a = source.clone();
        nElems = source.length;
    }
    //-----------------------------------------------------------
    public OrdArray(int max)          // constructor
    {
        a = new long[max];             // create array
        nElems = 0;
    }

    //-----------------------------------------------------------
    public int size() {
        return nElems;
    }

    //-----------------------------------------------------------
    public int find(long searchKey) {
        return findIndex(searchKey, EQUAL);
    }

    private int findIndex(long searchKey, SearchArrayPredicate p) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (p.test(a, curIn, searchKey))
                return curIn;              // found it
            else if (lowerBound > upperBound)
                return nElems;             // can't find it
            else                          // divide range
            {
                if (a[curIn] > searchKey)
                    upperBound = curIn - 1; // it's in lower half
                else
                    lowerBound = curIn + 1; // it's in upper half
            }  // end else divide range
        }  // end while
    }  // end find()

    //-----------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        int j = findIndex(value, FIRST_BIGGER);
        for (int k = nElems; k > j; k--)    // move bigger ones up
            a[k] = a[k - 1];
        a[j] = value;                  // insert it
        nElems++;                      // increment size
    }  // end insert()

    //-----------------------------------------------------------
    public boolean delete(long value) {
        int j = find(value);
        if (j == nElems)                  // can't find it
            return false;
        else                           // found it
        {
            for (int k = j; k < nElems; k++) // move bigger ones down
                a[k] = a[k + 1];
            nElems--;                   // decrement size
            return true;
        }
    }  // end delete()

    //-----------------------------------------------------------
    public void display()             // displays array contents
    {
        for (int j = 0; j < nElems; j++)       // for each element,
            System.out.print(a[j] + " ");  // display it
        System.out.println("");
    }
    //-----------------------------------------------------------
    public static OrdArray merge(OrdArray a, OrdArray b) {
        long[] result = new long[a.size() + b.size()];
        int firstIdx = 0;
        int secondIdx = 0;
        int curElem = 0;
        while (firstIdx < a.size() || secondIdx < b.size()) {
            if (firstIdx < a.size() && secondIdx < b.size()) {
                long firstVal = a.a[firstIdx];
                long secondVal = b.a[secondIdx];
                if (firstVal <= secondVal) {
                    result[curElem] = firstVal;
                    firstIdx++;
                } else {
                    result[curElem] = secondVal;
                    secondIdx++;
                }
            } else if (firstIdx < a.size()) {
                long firstVal = a.a[firstIdx];
                result[curElem] = firstVal;
                firstIdx++;
            } else if (secondIdx < b.size()) {
                long secondVal = b.a[secondIdx];
                result[curElem] = secondVal;
                secondIdx++;
            }
            curElem++;
        }
        return new OrdArray(result);
    }
    //-----------------------------------------------------------
}  // end class OrdArray

////////////////////////////////////////////////////////////////
class OrderedApp {
    public static void main(String[] args) {
        int maxSize = 100;             // array size
        OrdArray arr;                  // reference to array
        arr = new OrdArray(maxSize);   // create the array

        arr.insert(77);                // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        arr.display();

        OrdArray brr = new OrdArray(maxSize);

        brr.insert(14);
        brr.insert(71);
        brr.insert(15);
        brr.insert(25);
        brr.insert(43);
        brr.insert(68);
        brr.insert(87);
        brr.insert(23);
        brr.insert(21);
        brr.insert(65);
        brr.insert(39);
        brr.insert(81);

        brr.display();

        OrdArray merge = OrdArray.merge(arr, brr);

        merge.display();

        int searchKey = 55;            // search for item
        if (arr.find(searchKey) != arr.size())
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        arr.display();                 // display items

        System.out.println("Removing 00, 55, 99.");
        arr.delete(00);                // delete 3 items
        arr.delete(55);
        arr.delete(99);

        arr.display();                 // display items again
    }  // end main()

}  // end class OrderedApp

@FunctionalInterface
interface SearchArrayPredicate {
    boolean test(long[] array, int currentIndex, long searchKey);
}
