/*
If the given Binary Tree is Binary Search Tree, we can store it by either storing preorder or postorder traversal.
In case of Binary Search Trees, only preorder or postorder traversal is sufficient to store structure information.
 */

/*
For a complete Binary Tree, level order traversal is sufficient to store the tree. We know that the first node is root,
next two nodes are nodes of next level, next four nodes are nodes of 2nd level and so on.
 */

/*
a general Binary Tree
1. A simple solution is to store both Inorder and Preorder traversals. This solution requires space twice the size of Binary Tree.
2. We can save space by storing Preorder traversal and a marker for NULL pointers.
 */
class Solu{
  public String serialize(TreeNode root){
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);
    return sb.toString();
  }

  private void serialize(TreeNode x, StringBuilder sb){
    if (x == null) {
      sb.append("# ");
    } else {
      sb.append(x.val + " ");
      serialzie(x.left, sb);
      serialzie(x.right, sb);
    }
  }

  public TreeNode deserialize(String s){
    if (s == null || s.length() == 0) return null;
    // StringTokenizer is a legacy class t
    // prefer to use String[] s = s.split("\\s"); Arrays.asList(s).iterator();
    StringTokenizer st = new StringTokenizer(s, " ");
    // as pass slist.iterator();
    return deserialize(st);
  }

  private TreeNode deserialize(StringTokenizer st){ // deserialize(Iterator<String> it)
    if (!st.hasMoreTokens())
      return null;
    String val = st.nextToken();// use a iterator
    if (val.equals("#"))
      return null;
    TreeNode root = new TreeNode(Integer.parseInt(val));
    root.left = deserialize(st);
    root.right = deserialize(st);
    return root;
  }
}



class Solu{
  void writeBinaryTree(TreeNode *p, ostream &out) {
    if (!p) {
      out << "# ";
    } else {
      out << p->val << " ";
      writeBinaryTree(p->left, out);
      writeBinaryTree(p->right, out);
    }
  }
  void readBinaryTree(TreeNode *&p, ifstream &fin) {
    int token;
    bool isNumber;
    if (!readNextToken(token, fin, isNumber)) // if "#" return
      return;
    if (isNumber) {
      p = new TreeNode(token); // if is number
      readBinaryTree(p->left, fin);
      readBinaryTree(p->right, fin);
    }
  }
}