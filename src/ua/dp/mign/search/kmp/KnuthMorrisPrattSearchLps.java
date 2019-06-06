package ua.dp.mign.search.kmp;

import java.util.Arrays;

public class KnuthMorrisPrattSearchLps {
  public static int[] buildLps(char[] word) {
    int[] lps = new int[word.length];
    int j = 0;
    for (int i = 1; i < word.length; i++) {
      if (word[j] == word[i]) {
        lps[i] = ++j;
      } else {
        while (j > 0 && word[j] != word[i]) {
          j = lps[j-1];
        }
        if (word[j] == word[i]) {
          lps[i] = ++j;
        }
      }
    }
    return lps;
  }

  public static void main(String[] args) {
    char[] word = {'a', 'b', 'c', 'd', 'a', 'b', 'c', 'a'};
    int[] lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));

    word = new char[] {'a', 'a', 'b', 'a', 'a', 'b', 'a', 'a', 'a'};
    lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));

    word = new char[] {'a', 'b', 'c', 'a', 'b', 'y'};
    lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));

    word = new char[] {'a', 'b', 'c', 'd', 'a', 'b', 'd'};
    lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));

    word = new char[] {'a', 'a', 'a', 'a'};
    lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));

    word = new char[] {'a', 'b', 'c', 'd', 'e'};
    lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));

    word = new char[] {'a', 'a', 'b', 'a', 'a', 'c', 'a', 'a', 'b', 'a', 'a'};
    lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));

    word = new char[] {'a', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'a', 'c'};
    lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));

    word = new char[] {'a', 'a', 'a', 'b', 'a', 'a', 'a'};
    lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));

    word = new char[] {'a', 'c', 'a', 'c', 'a', 'b', 'a', 'c', 'a', 'c', 'a', 'b', 'a', 'c', 'a', 'c', 'a', 'c'};
    lps = buildLps(word);
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(lps));
  }
}
