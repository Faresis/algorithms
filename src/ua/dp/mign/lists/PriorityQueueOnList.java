package ua.dp.mign.lists;

public final class PriorityQueueOnList {
    private final SortedLinkedList sortedLinkedList = new SortedLinkedList();

    public void insert(long item) {
        this.sortedLinkedList.add(item);
    }

    public long remove() {
        return this.sortedLinkedList.removeFirst();
    }

    public boolean isEmpty() {
        return this.sortedLinkedList.isEmpty();
    }

    public static void main(String[] args) {
        PriorityQueueOnList priorityQueueOnList = new PriorityQueueOnList();

        priorityQueueOnList.insert(7);
        priorityQueueOnList.insert(1);
        priorityQueueOnList.insert(3);
        priorityQueueOnList.insert(4);
        priorityQueueOnList.insert(9);
        priorityQueueOnList.insert(8);
        priorityQueueOnList.insert(2);
        priorityQueueOnList.insert(0);
        priorityQueueOnList.insert(5);
        priorityQueueOnList.insert(6);

        while (!priorityQueueOnList.isEmpty())
            System.out.println(priorityQueueOnList.remove());
    }
}
