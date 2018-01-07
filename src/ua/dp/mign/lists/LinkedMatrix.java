package ua.dp.mign.lists;

import java.util.Iterator;
import java.util.LinkedList;

public final class LinkedMatrix {

    private final Link first;

    public LinkedMatrix(int columns, int rows) {
        LinkedList<Link> previousColumn = null;
        for (int i = 0; i < columns; i++) {
            LinkedList<Link> column = createColumn(rows);
            linkColumns(column, previousColumn);
            previousColumn = column;
        }
        first = previousColumn.getLast();
    }

    private static LinkedList<Link> createColumn(int rows) {
        LinkedList<Link> result = new LinkedList<>();
        Link previous = null;
        for (int i = 0; i < rows; i++) {
            Link link = new Link();
            link.setDown(previous);
            result.add(link);
            previous = link;
        }
        return result;
    }

    private static void linkColumns(Iterable<Link> column, Iterable<Link> previous) {
        if (previous == null) {
            return;
        }

        Iterator<Link> left = column.iterator();
        Iterator<Link> right = previous.iterator();

        while (left.hasNext() && right.hasNext()) {
            left.next().setRight(right.next());
        }
    }

    public int verifyRows() {
        int sum = 0;
        Link current = first;
        while (current != null) {
            sum++;
            current = current.getDown();
        }
        return sum;
    }

    public int verifyColumns() {
        int sum = 0;
        Link current = first;
        while (current != null) {
            sum++;
            current = current.getRight();
        }
        return sum;

    }

    public int verifyCells() {
        int sum = 0;
        Link currentRow = first;
        while (currentRow != null) {
            Link currentColumn = currentRow;
            while (currentColumn != null) {
                sum++;
                currentColumn = currentColumn.getRight();
            }
            currentRow = currentRow.getDown();
        }
        return sum;
    }

    public void display() {
        Link currentRow = first;
        while (currentRow != null) {
            Link currentColumn = currentRow;
            while (currentColumn != null) {
                System.out.print(currentColumn.getData() + " ");
                currentColumn = currentColumn.getRight();
            }
            System.out.println();
            currentRow = currentRow.getDown();
        }
    }

    public void insert(int col, int row, long data) {
        Link current = first;
        while (col > 0) {
            current = current.getRight();
            col--;
        }
        while (row > 0) {
            current = current.getDown();
            row--;
        }
        current.setData(data);
    }

    private static class Link {
        private Link right;
        private Link down;
        private long data;

        public Link() {
        }

        public Link getRight() {
            return right;
        }

        public Link getDown() {
            return down;
        }

        public void setRight(Link right) {
            this.right = right;
        }

        public void setDown(Link down) {
            this.down = down;
        }

        public long getData() {
            return data;
        }

        public void setData(long data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        int columns = 7;
        int rows = 10;
        LinkedMatrix matrix = new LinkedMatrix(columns, rows);
        System.out.println("Columns: " + matrix.verifyColumns());
        System.out.println("Rows: " + matrix.verifyRows());
        System.out.println("Cells: " + matrix.verifyCells());

        matrix.display();

        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix.insert(j, i, counter);
                counter++;
            }
        }

        System.out.println();
        matrix.display();
    }
}
