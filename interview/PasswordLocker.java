/*
一个数字串有4个数字，每个数字是 0 ~9 这10个数字。
    那么一共有0000 ~ 9999 共10,000个串。
    要求：找出一个最短的串，包含这10，000个数字串
 */
/*
For the "password" lock problem, the best solution is to use graph;

each number is a node, the directed edges between 2 nodes indicate extra
cost
if the dest comes after the source

for example "0000" to "0001" will be 1 while "0001" to "0000" is 4

Then problem becomes a hamilton path (np essentially)
 */
class solu{
  // Assume memory is not an issue here.
  // It is easy to find a memory efficiency way
  string calculate() {
    // assume all the strings are in an array vector<string> input;
    string result;
    for(int i=0; i<input.size(0; ++i) {
      result = input[i];
      unordered_set<string> visited;
      bool succeed = DFS(visited, input[i], result);
      if(succeed)
        return result;
    }
    // Can not generate!
    return "";
  }

  bool DFS(unordered_set<string> &visited, const string &node, string &
      result) {
    visited.insert(node);
    if(visited.size() == 10000)
      return true;
    string nodeseg = node.substr(1, 3);
    for(int i=0; i<10; ++i)  {
      char ch = '0' + i;
      string nextNode = nodeseg;
      nextNode.push_back(ch);
      if(visited.find(nextNode) != visited.end()) {
        result.push_back(ch);
        bool bSucceed = DFS(visited, nextNode, result);
        if(bSucceed)
          return true;
        result.pop_back();
      }
    }
    visited.erase(node);

    return false;
  }
}
