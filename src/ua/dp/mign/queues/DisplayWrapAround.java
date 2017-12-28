package ua.dp.mign.queues;

class DisplayWrapAroundQueue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    //--------------------------------------------------------------
    public DisplayWrapAroundQueue(int s)          // constructor
    {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    //--------------------------------------------------------------
    public void insert(long j)   // put item at rear of queue
    {
        if (rear == maxSize - 1)         // deal with wraparound
            rear = -1;
        queArray[++rear] = j;         // increment rear and insert
        nItems++;                     // one more item
    }

    //--------------------------------------------------------------
    public long remove()         // take item from front of queue
    {
        long temp = queArray[front++]; // get value and incr front
        if (front == maxSize)           // deal with wraparound
            front = 0;
        nItems--;                      // one less item
        return temp;
    }

    //--------------------------------------------------------------
    public long peekFront()      // peek at front of queue
    {
        return queArray[front];
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
//--------------------------------------------------------------
}  // end class DisplayWrapAroundQueue

////////////////////////////////////////////////////////////////
class DisplayWrapAroundApp {
    public static void main(String[] args) {
        DisplayWrapAroundQueue theDisplayWrapAroundQueue = new DisplayWrapAroundQueue(5);  // queue holds 5 items

        theDisplayWrapAroundQueue.insert(10);            // insert 4 items
        theDisplayWrapAroundQueue.insert(20);
        theDisplayWrapAroundQueue.insert(30);
        theDisplayWrapAroundQueue.insert(40);

        theDisplayWrapAroundQueue.remove();              // remove 3 items
        theDisplayWrapAroundQueue.remove();              //    (10, 20, 30)
        theDisplayWrapAroundQueue.remove();

        theDisplayWrapAroundQueue.insert(50);            // insert 4 more items
        theDisplayWrapAroundQueue.insert(60);            //    (wraps around)
        theDisplayWrapAroundQueue.insert(70);
        theDisplayWrapAroundQueue.insert(80);

        while (!theDisplayWrapAroundQueue.isEmpty())    // remove and display
        {                            //    all items
            long n = theDisplayWrapAroundQueue.remove();  // (40, 50, 60, 70, 80)
            System.out.print(n);
            System.out.print(" ");
        }
        System.out.println("");
    }  // end main()
}  // end class QueueApp
////////////////////////////////////////////////////////////////
