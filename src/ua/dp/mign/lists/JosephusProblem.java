package ua.dp.mign.lists;

public final class JosephusProblem {
    public static void main(String[] args) {
        int numberOfPeople = 7;
        int countingOff = 2;

        SinglyLinkedCircularList list = new SinglyLinkedCircularList();
        list.add(1);
        for (int i = numberOfPeople; i > 1; i--) {
            list.add(i);
        }

        while (!list.isEmpty()) {
            for (int i = 0; i < countingOff; i++) {
                list.step();
            }
            System.out.println(list.remove());
            if (!list.isEmpty())
                list.step();
        }
    }
}
