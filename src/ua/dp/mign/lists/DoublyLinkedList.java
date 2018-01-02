package ua.dp.mign.lists;

public final class DoublyLinkedList {

    private Link first;
    private Link last;

    public void addFirst(long data) {
        Link newLink = new Link(data);
        if (isEmpty()) {
            first = newLink;
            last = newLink;
        } else {
            newLink.setNext(first);
            first.setPrevious(newLink);
            first = newLink;
        }
    }

    public void addLast(long data) {
        Link newLink = new Link(data);
        if (isEmpty()) {
            first = newLink;
            last = newLink;
        } else {
            last.setNext(newLink);
            newLink.setPrevious(last);
            last = newLink;
        }
    }

    public long removeFirst() {
        Link tmp = first;
        first = first.getNext();
        if (first == null) {
            last = null;
        } else {
            first.setPrevious(null);
        }
        return tmp.getData();
    }

    public long removeLast() {
        Link tmp = last;
        last = last.getPrevious();
        if (last == null) {
            first = null;
        } else {
            last.setNext(null);
        }
        return tmp.getData();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void displayForward() {
        Link current = first;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void displayBackward() {
        Link current = last;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.previous;
        }
        System.out.println();
    }

    private static final class Link {
        private final long data;
        private Link next;
        private Link previous;

        public Link(long data) {
            this.data = data;
        }

        public long getData() {
            return data;
        }

        public Link getNext() {
            return next;
        }

        public void setNext(Link next) {
            this.next = next;
        }

        public Link getPrevious() {
            return previous;
        }

        public void setPrevious(Link previous) {
            this.previous = previous;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        doublyLinkedList.addFirst(11);
        doublyLinkedList.addFirst(22);
        doublyLinkedList.addFirst(33);
        doublyLinkedList.addLast(44);
        doublyLinkedList.addLast(55);
        doublyLinkedList.addLast(66);

        doublyLinkedList.displayForward();
        doublyLinkedList.displayBackward();

        doublyLinkedList.addFirst(77);
        doublyLinkedList.addLast(88);

        doublyLinkedList.displayForward();
        doublyLinkedList.displayBackward();

        doublyLinkedList.removeFirst();
        doublyLinkedList.removeLast();

        doublyLinkedList.displayForward();
        doublyLinkedList.displayBackward();

        int count = 0;
        while (!doublyLinkedList.isEmpty()) {
            if (count % 2 == 0) {
                System.out.println(doublyLinkedList.removeFirst());
            } else {
                System.out.println(doublyLinkedList.removeLast());
            }
            count++;
        }
    }
}
