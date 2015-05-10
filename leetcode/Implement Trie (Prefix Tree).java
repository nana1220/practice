/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
 */

class TrieNode {
  // Initialize your data structure here.
  TrieNode[] children;
  boolean isWord;
  public TrieNode() {
    children = new TrieNode[26];
    isWord=false;
  }
}

public class Trie {
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    if(word.length()==0) return;
    TrieNode curr = root;
    for(int i=0; i<word.length(); i++){
      char c = word.charAt(i);
      int idx = (int)(c - 'a');
      if(curr.children[idx]==null){
        curr.children[idx]=new TrieNode();
      }
      curr=curr.children[idx];
    }
    curr.isWord=true;
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
    if(word.length()==0) return true;
    TrieNode curr=root;
    for(int i=0; i<word.length(); i++){
      int idx = (int)(word.charAt(i) - 'a');
      if(curr.children[idx]==null) return false;
      curr=curr.children[idx];
    }
    return curr.isWord;

  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    TrieNode curr=root;
    for(int i=0; i<prefix.length(); i++){
      int idx = (int)(prefix.charAt(i) - 'a');
      if(curr.children[idx]==null) return false;
      curr=curr.children[idx];
    }
    if(curr.isWord) return true; // if prefix is also a word return true
    for(TrieNode n : curr.children){
      if(n!=null) return true; // otherwise if prefix has children return true
    }
    return false;
  }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");