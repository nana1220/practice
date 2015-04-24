/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
(each operation is counted as 1 step.)
You have the following 3 operations permitted on a word:
a) Insert a character
b) Delete a character
c) Replace a character
*/

// Levenshtein distance
// a string metric for measuring the difference
// between two sequences. Informally, the Levenshtein distance between two
// words is the minimum number of single-character edits (i.e. insertions,
// deletions or substitutions) required to change one word into the other. The
// phrase edit distance is often used to refer specifically to Levenshtein
// distance

// Another string distance measurement is Hamming distance, only allows substitions

// DP
// edit word1 to transform word1 to word2
// Let dp[i][j] stands for the edit distance between two strings with length i and j,
// i.e., word1[0,...,i-1] and word2[0,...,j-1].
// if x == y, then dp[i][j] == dp[i-1][j-1]
// if x != y, and we insert y for word1, then dp[i][j] = dp[i][j-1] + 1
// if x != y, and we delete x for word1, then dp[i][j] = dp[i-1][j] + 1
// if x != y, and we replace x with y for word1, then dp[i][j] = dp[i-1][j-1] + 1
// When x!=y, dp[i][j] is the min of the three situations.
// Initial condition:
// dp[i][0] = i, dp[0][j] = j

// time: O(m*n); space: O(m*n)
public class Solution {
  public int minDistance(String word1, String word2) {
    int len1 = word1.length();
    int len2 = word2.length();

    // len1+1, len2+1, because finally return dp[len1][len2]
    int[][] dp = new int[len1 + 1][len2 + 1];

    for (int i = 0; i <= len1; i++) {
      dp[i][0] = i;
    }

    for (int j = 0; j <= len2; j++) {
      dp[0][j] = j;
    }

    //iterate though, and check last char
    for (int i = 0; i < len1; i++) {
      char c1 = word1.charAt(i);
      for (int j = 0; j < len2; j++) {
        char c2 = word2.charAt(j);

        //if last two chars equal
        if (c1 == c2) {
          //update dp value for +1 length
          dp[i + 1][j + 1] = dp[i][j];
        } else {
          int replace = dp[i][j] + 1;
          int insert = dp[i][j + 1] + 1;
          int delete = dp[i + 1][j] + 1;

          int min = replace > insert ? insert : replace;
          min = delete > min ? min : delete;
          dp[i + 1][j + 1] = min;
        }
      }
    }

    return dp[len1][len2];
  }
}