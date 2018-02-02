package ua.dp.mign.sorting.advanced;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class ArrayRad {
    private static final int RADIX = 10;

    private int numOfDigits;
    private long[] theArray;          // ref to array theArray
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayRad(int maxSize, int numOfDigits) {
        this.numOfDigits = numOfDigits;
        theArray = new long[maxSize];      // create the array
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

    public void sort() {
        Map<Long, Queue<Long>> buckets = createBuckets();
        for (int i = 0; i < numOfDigits; i++) {
            for (int j = 0; j < nElems; j++) {
                long num = theArray[j];
                long digit = getDigitByPos(num, i);
                buckets.get(digit).add(num);
            }

            int idx = 0;
            Iterator<Long> subSorted = buckets.values().stream().flatMap(v -> v.stream()).iterator();
            while (subSorted.hasNext()) {
                theArray[idx++] = subSorted.next();
            }
            buckets.values().forEach(l -> l.clear());
        }
    }

    private Map<Long, Queue<Long>> createBuckets() {
        Map<Long, Queue<Long>> queueHashMap = new LinkedHashMap<>();
        for (int i = 0; i < RADIX; i++) {
            queueHashMap.put(Long.valueOf(i), new LinkedList<>());
        }
        return queueHashMap;
    }

    private static long getDigitByPos(long num, int pos) {
        return (long) (num / Math.pow(RADIX, pos)) % RADIX;
    }

}  // end class ArrayRad

class RadixSortApp {
    public static void main(String[] args) {
        int maxSize = 10;            // array size
        int numOfDigits = 3;
        ArrayRad arr = new ArrayRad(maxSize, numOfDigits);

        for (int j = 0; j < maxSize; j++)  // fill array with
        {                          // random numbers
            long n = (int) (java.lang.Math.random() * 199);
            arr.insert(n);
        }
        arr.display();                // display unsorted array

        arr.sort();

        arr.display();
    }  // end main()
}
