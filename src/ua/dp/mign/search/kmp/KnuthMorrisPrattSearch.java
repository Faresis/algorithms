package ua.dp.mign.search.kmp;

import java.util.Arrays;
import ua.dp.mign.search.kmp.KnuthMorrisPrattSearchLps;

public class KnuthMorrisPrattSearch {
  public static int find(char[] string, char[] word) {
    int[] lps = KnuthMorrisPrattSearchLps.buildLps(word);
    int j = 0;
    int comp = 0;
    for (int i = 0; i < string.length; i++) {
      comp++;
      if (string[i] == word[j]) {
        if (++j == word.length) {
          System.out.println("Comparisons performed: " + comp);
          return ++i - j;
        } else {
          continue;
        }
      } else if (j > 0) {
        while (j > 0 && string[i] != word[j]) {
          comp++;
          j = lps[j-1];
        }
        if (string[i] == word[j]) {
          j++;
        }
      }
    }
    System.out.println("Comparisons performed: " + comp);
    return -1;
  }

  public static int findWiki(char[] string, char[] word) {
    int[] lps = KnuthMorrisPrattSearchLps.buildLpsWiki(word);
    int cnd = 0;
    int pos = 0;
    int comp = 0;
    while (pos < string.length) {
      comp++;
      if (string[pos] == word[cnd]) {
        ++pos; ++cnd;
        if (cnd == word.length) {
          System.out.println("Wiki comparisons performed: " + comp);
          return pos - cnd;
        }
      } else {
        cnd = lps[cnd];
        if (cnd < 0) {
          ++pos; ++cnd;
        }
      }
    }
    System.out.println("Wiki comparisons performed: " + comp);
    return -1;
  }

  public static void main(String[] args) {
    char[] string = { 'a', 'b', 'x', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'y' };
    char[] word = { 'a', 'b', 'c', 'a', 'b', 'y' };
    System.out.println("String: " + Arrays.toString(string));
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Result: " + find(string, word));
    System.out.println("Result wiki: " + findWiki(string, word));

    string = new char[] { 'a', 'b', 'c', ' ', 'a', 'b', 'c', 'd', 'a', 'b', ' ', 'a', 'b', 'c', 'd', 'a', 'b', 'c', 'd', 'a', 'b', 'd', 'e' };
    word = new char[] { 'a', 'b', 'c', 'd', 'a', 'b', 'd' };
    System.out.println("String: " + Arrays.toString(string));
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Result: " + find(string, word));
    System.out.println("Result wiki: " + findWiki(string, word));
  }
}
