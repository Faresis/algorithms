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

  public static int[] buildLpsWiki(char[] word) {
    int[] lps = new int[word.length];
    int cnd = 0;
    int pos = 1;
    lps[0] = -1;
    while (pos < word.length) {
      if (word[pos] == word[cnd]) {
        lps[pos] = lps[cnd];
      } else {
        lps[pos] = cnd;
        cnd = lps[cnd];
        while (cnd >= 0 && word[pos] != word[cnd]) {
          cnd = lps[cnd];
        }
      }
      ++pos; ++cnd;
    }
    return lps;
  }

  public static void main(String[] args) {
    char[] word = {'a', 'b', 'c', 'd', 'a', 'b', 'c', 'a'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'a', 'b', 'a', 'a', 'b', 'a', 'a', 'a'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'b', 'c', 'a', 'b', 'y'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'b', 'c', 'd', 'a', 'b', 'd'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'a', 'a', 'a'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'b', 'c', 'd', 'e'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'a', 'b', 'a', 'a', 'c', 'a', 'a', 'b', 'a', 'a'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'a', 'a', 'c', 'a', 'a', 'a', 'a', 'a', 'c'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'a', 'a', 'b', 'a', 'a', 'a'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'c', 'a', 'c', 'a', 'b', 'a', 'c', 'a', 'c', 'a', 'b', 'a', 'c', 'a', 'c', 'a', 'c'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));

    word = new char[] {'a', 'b', 'a', 'c', 'a', 'b', 'a', 'b', 'c'};
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Lps: " + Arrays.toString(buildLps(word)));
    System.out.println("Lps wiki: " + Arrays.toString(buildLpsWiki(word)));
  }
}
