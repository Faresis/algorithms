package ua.dp.mign.queues;

////////////////////////////////////////////////////////////////
class PriorityQ {
    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private long[] queArray;
    private int nItems;

    //-------------------------------------------------------------
    public PriorityQ(int s)          // constructor
    {
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }

    //-------------------------------------------------------------
    public void insert(long item)    // insert item
    {
        queArray[nItems++] = item;
    }  // end insert()

    //-------------------------------------------------------------
    public long remove()             // remove minimum item
    {
        int min = 0;
        for (int i = 0; i < nItems; i++) {
            if (queArray[min] > queArray[i]) {
                min = i;
            }
        }
        return remove(min);
    }

    private long remove(int idx) {
        long result = queArray[idx];
        for (int i = idx + 1; i < nItems; i++) {
            queArray[i - 1] = queArray[i];
        }
        nItems--;
        return result;
    }

    //-------------------------------------------------------------
    public long peekMin()            // peek at minimum item
    {
        return queArray[nItems - 1];
    }

    //-------------------------------------------------------------
    public boolean isEmpty()         // true if queue is empty
    {
        return (nItems == 0);
    }

    //-------------------------------------------------------------
    public boolean isFull()          // true if queue is full
    {
        return (nItems == maxSize);
    }
//-------------------------------------------------------------
}  // end class PriorityQ

////////////////////////////////////////////////////////////////
class PriorityQApp {
    public static void main(String[] args) {
        PriorityQ thePQ = new PriorityQ(5);
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);

        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            System.out.print(item + " ");  // 10, 20, 30, 40, 50
        }  // end while
        System.out.println("");
    }  // end main()
//-------------------------------------------------------------
}  // end class PriorityQApp
////////////////////////////////////////////////////////////////
