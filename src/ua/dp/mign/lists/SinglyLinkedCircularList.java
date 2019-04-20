package ua.dp.mign.lists;

public final class SinglyLinkedCircularList {

    private Link current;

    public void add(long data) {
        Link toInsert = new Link(data);

        if (this.isEmpty()) {
            this.current = toInsert;
            toInsert.setNext(this.current);
        } else {
            toInsert.setNext(this.current.getNext());
            this.current.setNext(toInsert);
        }
    }

    public long remove() {
    	if (this.isEmpty())
    	    throw new IllegalStateException();

        long result;
        if (this.current.getNext() == this.current) {
            result = this.current.getData();
            this.current = null;
        } else {
            result = this.current.getNext().getData();
            this.current.setNext(this.current.getNext().getNext());
        }
        return result;
    }

    public boolean find(long data) {
    	if (this.isEmpty())
            throw new IllegalStateException();

    	Link start = this.current;
    	do {
      	    if (this.get() == data)
            return true;
    	} while (start != this.next());
    	return false;
    }

    public long get() {
    	if (this.isEmpty())
            throw new IllegalStateException();

    	return this.current.getNext().getData();
    }

    public long step() {
        this.next();
        return this.get();
    }

    private Link next() {
        this.current = this.current.getNext();
        return this.current;
    }

    public boolean isEmpty() {
        return current == null;
    }

    public void display() {
        if (this.isEmpty())
            return;

    	Link start = this.current;
    	do {
            System.out.print(this.get() + " ");
        } while (start != this.next());
    	System.out.println();
    }

    public SinglyLinkedCircularList copy() {
    	SinglyLinkedCircularList copy = new SinglyLinkedCircularList();
    	Link start = this.current;
    	do {
            copy.add(this.get());
            copy.step();
        } while (start != this.next());
        return copy;
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
