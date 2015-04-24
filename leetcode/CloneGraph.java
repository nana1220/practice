/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 */

/*
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

  // use cache to reduce duplicate computation and avoid cycles
  // Maintain a map<oldNode, newNode>, if the oldNode is in the map, get newNode from the map
  // otherwise, create a new node
public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node==null) return null;
    HashMap<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
    return clone(node, map);
  }
  UndirectedGraphNode clone(UndirectedGraphNode node, HashMap<UndirectedGraphNode,UndirectedGraphNode> map){
    if(map.containsKey(node)) return map.get(node);
    map.put(node, new UndirectedGraphNode(node.label));
    for(UndirectedGraphNode aNode : node.neighbors){
      UndirectedGraphNode copy = clone(aNode, map);
      map.get(node).neighbors.add(copy);
    }
    return map.get(node);
  }
}

  // BFS, use HashMap to mark visited and map orig node to its clone node
  public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
      if(node==null) return null;
      LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
      Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
      map.put(node, new UndirectedGraphNode(node.label));
      queue.add(node);
      while(!queue.isEmpty()){
        UndirectedGraphNode orig = queue.pop();
        for( UndirectedGraphNode aNode : orig.neighbors){
          if(!map.containsKey(aNode)){
            map.put(aNode, new UndirectedGraphNode(aNode.label));
            queue.add(aNode);
          }
          map.get(orig).neighbors.add(map.get(aNode));
        }
      }
      return map.get(node);
    }
  }