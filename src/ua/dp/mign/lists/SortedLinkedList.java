package ua.dp.mign.lists;

public final class SortedLinkedList {

    private Link first;

    public SortedLinkedList() {
        first = null;
    }

    public void add(long data) {
        Link newLink = new Link(data);
        if (isEmpty()) {
            first = newLink;
        } else {
            Link previous = null;
            Link current = first;
            while (current != null) {
                if (current.getData() > newLink.getData()) {
                    break;
                }
                previous = current;
                current = current.getNext();
            }
            if (previous == null) {
                newLink.setNext(first);
                first = newLink;
            } else {
                previous.setNext(newLink);
                newLink.setNext(current);
            }
        }
    }

    public long removeFirst() {
        Link tmp = first;
        first = first.getNext();
        return tmp.getData();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void display() {
        Link current = first;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    private static final class Link {
        private final long data;
        private Link next;

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
    }

    public static void main(String[] args) {
        SortedLinkedList sortedLinkedList = new SortedLinkedList();

        sortedLinkedList.add(77);
        sortedLinkedList.add(66);
        sortedLinkedList.add(33);
        sortedLinkedList.add(44);
        sortedLinkedList.add(88);
        sortedLinkedList.add(99);
        sortedLinkedList.add(11);
        sortedLinkedList.add(22);
        sortedLinkedList.add(00);
        sortedLinkedList.add(55);

        sortedLinkedList.display();

        while (!sortedLinkedList.isEmpty()) {
            System.out.println(sortedLinkedList.removeFirst());
        }
    }
}
