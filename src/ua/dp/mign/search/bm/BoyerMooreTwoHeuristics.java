package ua.dp.mign.search.bm;

import java.util.Arrays;

class BoyerMooreTwoHeuristics {
  private static final int ALPHABET_SIZE = 256;

  private static int[] buildBadCharacterTable(char[] pattern) {
    int[] table = new int[ALPHABET_SIZE];
    for (int i = 0; i < ALPHABET_SIZE; i++)
      table[i] = -1;
    for (int i = 0; i < pattern.length; i++)
      table[pattern[i]] = i;
    return table;
  }

  private static int[] buildGoodSuffixTable(char[] pattern) {
    int m = pattern.length;

    int[] s = new int[m+1];
    int[] f = new int[m+1];
    
    // case 1
    int i = m;
    int j = m+1;
    f[i] = j;
    while (i > 0) {
      while (j <= m && pattern[i-1] != pattern[j-1]) {
        if (s[j] == 0) s[j] = j-i;
        j = f[j];
      }
      f[--i]=--j;
    }

    // case 2
    j = f[0];
    for (int k = 0; k <=m; k++) {
      if (s[k] == 0) s[k] = j;
      if (k == j) j = f[j];
    }

    return s;
  }

  private static void search(char[] string, char[] pat) {
    int n = string.length;
    int m = pat.length;

    int[] badChar = buildBadCharacterTable(pat);
    int[] goodSuffix = buildGoodSuffixTable(pat);

    int s = 0;
    while (s <= (n - m)) {
      int j = m - 1;

      while (j >= 0 && pat[j] == string[j+s]) {
        j--;
      }

      if (j < 0) {
        System.out.println("Match found at " + s);
        
        s += goodSuffix[0];
      } else {
        s += Math.max(goodSuffix[j+1], j - badChar[string[s+j]]);
      }
    }
  }

  public static void main(String[] args) {
    String text = "ABAAABCD";
    String pattern = "ABC";
    System.out.println("Text: " + text);
    System.out.println("Pattern: " + pattern);
    char[] txt = text.toCharArray();
    char[] pat = pattern.toCharArray();
    search(txt, pat);

    
    text = "abxabcabcaby";
    pattern = "abcaby";
    System.out.println("Text: " + text);
    System.out.println("Pattern: " + pattern);
    txt = text.toCharArray();
    pat = pattern.toCharArray();
    search(txt, pat);
  }
}
