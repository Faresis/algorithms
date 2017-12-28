package ua.dp.mign.queues;

////////////////////////////////////////////////////////////////
class QueueToDequeue {
    private int maxSize;
    private long[] queArray;
    private int left;
    private int right;
    private int nItems;

    //--------------------------------------------------------------
    public QueueToDequeue(int s)          // constructor
    {
        maxSize = s;
        queArray = new long[maxSize];
        left = 0;
        right = -1;
        nItems = 0;
    }

    //--------------------------------------------------------------
    public void insertRight(long j)   // put item at right of queue
    {
        if (right == maxSize - 1)         // deal with wraparound
            right = -1;
        queArray[++right] = j;         // increment right and insert
        nItems++;                     // one more item
    }

    public void insertLeft(long j) {
        if (--left < 0) {
            left = maxSize - 1;
        }
        queArray[left] = j;
        nItems++;
    }

    //--------------------------------------------------------------
    public long removeLeft()         // take item from left of queue
    {
        long temp = queArray[left++]; // get value and incr left
        if (left == maxSize)           // deal with wraparound
            left = 0;
        nItems--;                      // one less item
        return temp;
    }

    public long removeRight() {
        long temp = queArray[right--];
        if (right < 0) {
            right = maxSize - 1;
        }
        nItems--;
        return temp;
    }

    //--------------------------------------------------------------
    public long peekFront()      // peek at left of queue
    {
        return queArray[left];
    }

    //--------------------------------------------------------------
    public boolean isEmpty()    // true if queue is empty
    {
        return (nItems == 0);
    }

    //--------------------------------------------------------------
    public boolean isFull()     // true if queue is full
    {
        return (nItems == maxSize);
    }

    //--------------------------------------------------------------
    public int size()           // number of items in queue
    {
        return nItems;
    }

    public void display() {
        int count = nItems;
        int pos = left;
        if (count == 0) {
            System.out.println("The queue is empty.");
        } else {
            System.out.print("[\t");
            while (count > 0) {
                System.out.printf("%d\t", queArray[pos++]);
                if (pos == maxSize) {
                    pos = 0;
                }
                count--;
            }
            System.out.print("]");
            System.out.println();
        }
    }
//--------------------------------------------------------------
}  // end class QueueToDequeue

////////////////////////////////////////////////////////////////
class QueueToDequeueApp {
    public static void main(String[] args) {
        QueueToDequeue theQueue = new QueueToDequeue(5);  // queue holds 5 items

        theQueue.insertRight(10);            // insert 4 items
        theQueue.insertRight(20);
        theQueue.insertRight(30);
        theQueue.insertLeft(40);

        theQueue.display();

        theQueue.removeLeft();              // remove 3 items
        theQueue.removeLeft();              //    (10, 20, 30)
        theQueue.removeLeft();

        theQueue.display();

        theQueue.insertRight(50);            // insert 4 more items
        theQueue.insertRight(60);            //    (wraps around)
        theQueue.insertRight(70);
        theQueue.insertLeft(80);

        theQueue.display();

        int cnt = 0;
        while (!theQueue.isEmpty())    // remove and display
        {                            //    all items
            long n;
            if (cnt % 2 == 0) {
                n = theQueue.removeLeft();
            } else {
                n = theQueue.removeRight();
            }
            System.out.print(n);
            System.out.print(" ");
            cnt++;
        }
        System.out.println("");

        theQueue.display();
    }  // end main()
}  // end class QueueApp
////////////////////////////////////////////////////////////////
