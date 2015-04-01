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
public class CloneGraph {

  // use cache to reduce duplicate computation and avoid cycles
  // Maintain a map<oldNode, newNode>, if the oldNode is in the map, get newNode from the map
  // otherwise, create a new node

  // DFS
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    return clone(node, new HashMap<UndirectedGraphNode, UndirectedGraphNode>());
  }
  public UndirectedGraphNode clone(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> cache){
    if (node == null) return null;
    if (cache.containsKey(node)) return cache.get(node);
    UndirectedGraphNode res = new UndirectedGraphNode(node.label);
    cache.put(node, res);
    if (node.neighbors == null || node.neighbors.size() == 0) return res;
    for (UndirectedGraphNode n : node.neighbors)
      res.neighbors.add(clone(n, cache));
    return res;
  }

  // BFS
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node==null) return null;
    Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    // use cache
    Map<UndirectedGraphNode, UndirectedGraphNode> cache = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    queue.add(node);
    while (!queue.isEmpty()){
      UndirectedGraphNode curr = queue.poll();
      if (!cache.containsKey(curr))
        cache.put(curr, new UndirectedGraphNode(curr.label)); // if not contain, create a new one, put in cache
      for (UndirectedGraphNode adj : curr.neighbors){
        if (!cache.containsKey(adj)){
          cache.put(adj, new UndirectedGraphNode(adj.label));
          queue.add(adj); // don't add to queue if contained in cache
        }
        cache.get(curr).neighbors.add(cache.get(adj)); // get from cache
      }
    }
    return cache.get(node);
  }
}