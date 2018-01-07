package ua.dp.mign.lists;

public final class StackOnCircularList {
    private final SinglyLinkedCircularList list = new SinglyLinkedCircularList();

    public void push(long data) {
        this.list.add(data);
    }

    public long pop() {
        return this.list.remove();
    }

    public void display() {
        this.list.display();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public static void main(String[] args) {
        StackOnCircularList theStack = new StackOnCircularList();
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
}
