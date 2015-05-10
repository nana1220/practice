/*
Use the shortest unique prefix to represent each word in the array
input: ["zebra", "dog", "duck",”dot”]
output: {zebra: z, dog: dog, duck: du, dot: dot}

[zebra, dog, duck, dove]
{zebra:z, dog: dog, duck: du, dove: dov}

[bearcat, bear]
{bearcat: bearc, bear: ""} // note for "bear", even last char 'r' has dup 2, so return ""
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
        if (tmp.next[array[i] - 'a'] == null)  return "";
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


class SOlu2{
  class Trie {
    public:
    struct Node {
      Node(char val): value{val}{}
      ~Node() {
        for(int i = 0; i < 27; ++i) {
          if (children[i]) {
            delete children[i];
            children[i] = nullptr;
          }
        }
      }
      Node* get(char c, bool auto_create = true) {
        assert((c >= 'a' && c <= 'z') || c == '\0');
        int id = c == '\0' ? 26 : c - 'a';
        if (!children[id] && auto_create) {
          children[id] = new Node(c);
          children[id]->parent = this;
          num_children++;
        }
        return children[id];
      }

      int num_children { 0 };
      char value{ 0 };
      Node* parent{ nullptr };
      Node* children[27] { nullptr };
    };

    public:
    Trie() {
      _root = new Node(-1);
    }

    ~Trie() {
      if (_root) {
        delete _root;
        _root = nullptr;
      }
    }

    void add_string(const string& str) {
      auto node = _root;
      for (auto c : str) {
        node = node->get(c);
      }
      node->get('\0');
    }
    vector<string> search_pattern(const string& str) {
      vector<string> ret;

      function<void(int,string,Node*)> search = [&](int id, string cur, Node* node){
        if (!node) return;

        if (id > str.length()) {
          if (node->value == '\0') {
            cur.resize(cur.length() - 1);
            ret.push_back(cur);
          }
          return;
        }

        char c = id < str.length() ? str[id] : '\0';
        if (c == '.') {
          for(int i = 0; i < 27; ++i) {
            if (node->children[i] && node->children[i]->value != '\0')
            search(id+1, cur + node->children[i]->value, node->children[i]);
          }
        } else {
          search(id+1, cur + c, node->get(str[id], false));
        }
      };

      search(0, "", _root);

      return ret;
    }

    Node* search_string(const string& str) {
      auto node = _root;
      for (auto c : str) {
        node = node->get(c, false);
        if (!node) return nullptr;
      }
      node = node->get('\0');
      return node;
    }

    string get_prefix(Node* node) {
      string ret;
      while(node && node->value >= 0) {
        ret.insert(ret.begin(), node->value);
        node = node->parent;
      }
      return ret;
    }

    string shortest_prefix(const string& str) {
      auto term_node = search_string(str);
      if (!term_node) return "";

      auto node = term_node;
      while (node->parent) {
        if (node->parent->num_children > 1) {
          return get_prefix(node);
        }
        node = node->parent;
      }
      return "";
    }

    private:
    Node* _root{ nullptr };
  };
}