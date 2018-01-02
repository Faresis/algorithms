package ua.dp.mign.lists;

public final class DequeueOnDoublyLinkedList {
    private final DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

    public void insertRight(long g) {
        this.doublyLinkedList.addLast(g);
    }

    public void insertLeft(long j) {
        this.doublyLinkedList.addFirst(j);
    }

    public long removeLeft() {
        return this.doublyLinkedList.removeFirst();
    }

    public long removeRight() {
        return this.doublyLinkedList.removeLast();
    }

    public boolean isEmpty() {
        return this.doublyLinkedList.isEmpty();
    }

    public void display() {
        this.doublyLinkedList.displayForward();
    }

    public static void main(String[] args) {
        DequeueOnDoublyLinkedList theQueue = new DequeueOnDoublyLinkedList();
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
    }
}
