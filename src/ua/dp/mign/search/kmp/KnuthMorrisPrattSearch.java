package ua.dp.mign.search.kmp;

import java.util.Arrays;
import ua.dp.mign.search.kmp.KnuthMorrisPrattSearchLps;

public class KnuthMorrisPrattSearch {
  public static int find(char[] string, char[] word) {
    int[] lps = KnuthMorrisPrattSearchLps.buildLps(word);
    int j = 0;
    for (int i = 0; i < string.length; i++) {
      if (string[i] == word[j]) {
        if (++j == word.length)
          return ++i - j;
        else
          continue;
      } else if (j > 0) {
        while (j > 0 && string[i] != word[j]) {
          j = lps[j-1];
        }
        if (string[i] == word[j]) {
          j++;
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    char[] string = { 'a', 'b', 'x', 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'y' };
    char[] word = { 'a', 'b', 'c', 'a', 'b', 'y' };
    System.out.println("String: " + Arrays.toString(string));
    System.out.println("Word: " + Arrays.toString(word));
    System.out.println("Result: " + find(string, word));
  }
}
