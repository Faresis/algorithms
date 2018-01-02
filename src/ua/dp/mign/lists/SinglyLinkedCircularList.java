package ua.dp.mign.lists;

public final class SinglyLinkedCircularList {

    private Link current;

    public void add(long data) {
        Link newLink = new Link(data);
        if (current == null) {
            current = newLink;
            current.setNext(current);
        } else {
            newLink.setNext(current.getNext());
            current.setNext(newLink);
        }
    }

    public long remove() {
        long result;
        if (current.getNext() == current) {
            result = current.getData();
            current = null;
        } else {
            result = current.getNext().getData();
            current.setNext(current.getNext().getNext());
        }
        return result;
    }

    public boolean find(long data) {
        boolean found = false;
        Link previous = current;
        Link toFind = current.getNext();
        Link end = toFind;
        do {
            if (toFind.getData() == data) {
                found = true;
                current = previous;
                break;
            }
            previous = toFind;
            toFind = toFind.getNext();
        } while (toFind != end);
        return found;
    }

    public long step() {
        current = current.getNext();
        return current.getData();
    }

    public boolean isEmpty() {
        return current == null;
    }

    public void display() {
        Link toDisplay = current;
        do {
            System.out.print(toDisplay.getData() + " ");
            toDisplay = toDisplay.getNext();
        } while (toDisplay != current);
        System.out.println();
    }

    private static final class Link {
        private final long data;
        private Link next;

        private Link(long data) {
            this.data = data;
        }

        public long getData() {
            return data;
        }

        private Link getNext() {
            return next;
        }

        private void setNext(Link next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedCircularList list = new SinglyLinkedCircularList();

        list.add(33);

        list.display();

        list.add(44);
        list.add(55);
        list.add(66);
        list.add(77);

        list.display();

        System.out.println("Find 55: " + list.find(55));
        list.remove();

        list.display();

        for (int i = 0; i < 10; i++) {
            System.out.print(list.step() + " ");
        }
        System.out.println();

        System.out.println("Removing elements.");
        while (!list.isEmpty()) {
            System.out.print(list.remove() + " ");
        }
        System.out.println();
    }
}
