package ua.dp.mign.lists;

public final class JosephusProblem {
    public static void main(String[] args) {
        SinglyLinkedCircularList list = new SinglyLinkedCircularList();

        int numOfElements = 7;
        long numToBeLast = 2;

        fillList(list, numOfElements);

        list.display();

        int countBy = findCountByForLast(list, numToBeLast);
 
        System.out.println("In order to have " + numToBeLast + " to be the last from " + numOfElements);
        System.out.println("Count by " + countBy + " should be performed.");
    }

    private static int findCountByForLast(SinglyLinkedCircularList list, long numToBeLast) {
      for (int numOfSteps = 1; numOfSteps <= 100; numOfSteps++) {
        SinglyLinkedCircularList specimen = list.copy();

        long last = -1;
        while(!specimen.isEmpty()) {
          for (int i = 0; i < numOfSteps; i++) {
            specimen.step();
          }
          last = specimen.remove();
        }

        System.out.println("For " + numOfSteps + " number of steps the last number is " + last);

        if (numToBeLast == last) return numOfSteps;
      }
      throw new IllegalArgumentException("Coudn't find number of steps for " + numToBeLast);
    }

    private static void fillList(SinglyLinkedCircularList list, int numOfElements) {
      for (int i = 1; i <= numOfElements; i++) {
        list.add(i);
        list.step();
      }
    }
}
