/*
给你一组Treenode，他们每个有一个id，一个parentId，一个value，让你输出所有subtree的sum of value。
注意这个是没有children node的，只有parentId。
 */

// 总的来说是一个bottom up的解法，先找出每一个level的所有节点，然后从最下面往上做level order traversal。
class Solu {
  void all_sum(const unordered_map<int, int>&parents, const unordered_map<int, int>&values) {
    int max_level = 0;
    unordered_map<int, int> node_levels; // map each node to its level
    function<int (int)>calc_level =[&]( int id){
      if (node_levels.count(id)) return node_levels[id];
      int ret = (parents.at(id) == -1) ? 0 : calc_level(parents.at(id)) + 1; // root level 0, otherwise level = parentLevel+1
      max_level = std::max (max_level, ret);
      node_levels[id] = ret;
      return ret;
    } ;

    for (auto & p:parents)calc_level(p.first); // p.first is id, p.second is parent id
    vector<vector<int>> levels (max_level + 1);
    for (auto & p:node_levels)levels[p.second].push_back(p.first);

    unordered_map<int, int> subtree_sums = values; // leaf nodes and values

    for (int i = (int) levels.size() - 1; i >= 0; --i) {
      for (auto & node:levels[i]){ // bottome up
        if (parents.at(node) != -1) // if has parent, add sum of current node to its parent
          subtree_sums[parents.at(node)] += subtree_sums[node];
      }
    }
    for (auto & p:subtree_sums)
    cout << "Root: " << p.first << " Subtree sum: " << p.second << endl;
  }
}