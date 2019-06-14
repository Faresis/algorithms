package ua.dp.mign.search.bm;

class BoyerMooreBadCharacterHeuristic {
  private static final int ALPHABET_SIZE = 256;

  private static int[] buildBadCharacterTable(char[] pattern) {
    int[] table = new int[ALPHABET_SIZE];
    for (int i = 0; i < ALPHABET_SIZE; i++)
      table[i] = -1;
    for (int i = 0; i < pattern.length; i++)
      table[pattern[i]] = i;
    return table;
  }

  private static void search(char[] text, char[] pattern) {
    int n = text.length;
    int m = pattern.length;
    
    int[] badChars = buildBadCharacterTable(pattern);

    int s = 0;
    while (s <= (n-m)) {
      int j = m - 1;

      while (j >= 0 && pattern[j] == text[j+s])
        j--;

      if (j < 0) {
        System.out.println("Pattern found at: " + s);
 
        s += (s + m) < n ? m - badChars[text[s+m]] : 1;
      } else {
        s += Math.max(1, j - badChars[text[s+j]]);
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
