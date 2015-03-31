/*
Use the shortest unique prefix to represent each word in the array
input: ["zebra", "dog", "duck",”dot”]
output: {zebra: z, dog: dog, duck: du, dot: dot}

[zebra, dog, duck, dove]
{zebra:z, dog: dog, duck: du, dove: dov}

[bearcat, bear]
{bearcat: bearc, bear: ""}
 */

/*
 * build a trie tree, with each node keeping a duplicate counts
 */
public class ShortestUniquePrefixOfWords {

  class PrefixTreeNode {
    int count;
    PrefixTreeNode[] next;
    PrefixTreeNode(int c) {
      count = c;
      next = new PrefixTreeNode[26]; // a ~ z
      for (int i = 0; i < next.length; ++i)
        next[i] = null;
    }
  };

  class ShortestUniquePrefixTree {
    public PrefixTreeNode root = new PrefixTreeNode(0);

    public void add(String word) {
      PrefixTreeNode tmp = root;
      for (char c : word.toLowerCase().toCharArray()) {
        if (tmp.next[c - 'a'] != null) ++tmp.next[c - 'a'].count;
        else tmp.next[c - 'a'] = new PrefixTreeNode(1);
        tmp = tmp.next[c - 'a'];
      }
    }

    public String getShortestUniquePrefix(String word) {
      if (word == null || word.length() == 0) return null;
      PrefixTreeNode tmp = root;
      char[] array = word.toLowerCase().toCharArray();
      for (int i = 0; i < array.length; ++i) {
        if (tmp.next[array[i] - 'a'] == null)  return null;
        else if (tmp.next[array[i] - 'a'].count == 1) return word.substring(0, i + 1);
        else tmp = tmp.next[array[i] - 'a'];
      }
      return word;
    }
  }

  public static void main(String[] args) {
    String[] input = {"zebra", "dog", "duck", "dot"};
    ShortestUniquePrefixTree supt = new ShortestUniquePrefixTree();
    for (String str : input)
      supt.add(str);
    for (String str : input)
      System.out.println(str + ": " + supt.getShortestUniquePrefix(str));
  }
}