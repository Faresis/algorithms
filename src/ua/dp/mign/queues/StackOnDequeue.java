package ua.dp.mign.queues;

////////////////////////////////////////////////////////////////
class StackOnDequeue {
    private QueueToDequeue dequeue;

    //--------------------------------------------------------------
    public StackOnDequeue(int s)         // constructor
    {
        this.dequeue = new QueueToDequeue(s);
    }

    //--------------------------------------------------------------
    public void push(long j)    // put item on top of stack
    {
        this.dequeue.insertRight(j);
    }

    //--------------------------------------------------------------
    public long pop()           // take item from top of stack
    {
        return this.dequeue.removeRight();
    }

    //--------------------------------------------------------------
    public long peek()          // peek at top of stack
    {
        long l = this.dequeue.removeRight();
        this.dequeue.insertRight(l);
        return l;
    }

    //--------------------------------------------------------------
    public boolean isEmpty()    // true if stack is empty
    {
        return this.dequeue.isEmpty();
    }

    //--------------------------------------------------------------
    public boolean isFull()     // true if stack is full
    {
        return this.dequeue.isFull();
    }
//--------------------------------------------------------------
}  // end class StackOnDequeue

////////////////////////////////////////////////////////////////
class StackOnDequeueApp {
    public static void main(String[] args) {
        StackOnDequeue theStack = new StackOnDequeue(10);  // make new stack
        theStack.push(20);               // push items onto stack
        theStack.push(40);
        theStack.push(60);
        theStack.push(80);
        theStack.push(99);

        while (!theStack.isEmpty())     // until it's empty,
        {                             // delete item from stack
            long value = theStack.pop();
            System.out.print(value);      // display it
            System.out.print(" ");
        }  // end while
        System.out.println("");
    }  // end main()
}  // end class StackApp
////////////////////////////////////////////////////////////////
