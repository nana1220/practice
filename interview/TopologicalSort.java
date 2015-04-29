/*
Given a dictionary of strings [ strings are in sorted order] you have to find the precedence of characters according to the dictionary..
eat
bxy
e is ranked above b according to the dictionary.

Problem restatement:
There exists an alphabet comprising some number of characters.
The order of the alphabet, i.e. the full-ordering, is not known.
You are given a list of "words" comprised of characters from the alphabet.
The list of words is given to be sorted in lexicographical order.
Write a program to deduce the order of the alphabet.

example:
aardvark
ant
bee
cat
cow
dog
horse
llama
sheep
zebra
aardvark no order clues
ant (a,n) based on column 2
bee (a,b) based on column 1
cat (b,c) based on column 1
cow (a,o) based on column 2
dog (c,d) based on column 1
horse (d,h) based on column 1
llama (h,l) based on column 1
sheep (l,s) based on column 1
zebra (s,z) based on column 1
n
/
a -> b -> c -> d -> h -> l -> s -> z
\
o

 */

/*
Start from the first pair in the dictionary. Compare two strings in this pair. When I am comparing two consecutive
strings then i should compare till first mismatch.
Eg: aad & aab, in this case create an edge d -> b
then go to that character's node "list" (here node list of char "d") and if the new character (char "b") to be linked
is not already present then add it....Do the above steps for all the consecutive pairs in the dictionary.

Finally do topological sort in the following way to return precedence of characters OR error in case the graph has a cycle (cycle in case of wrong input)
 */
L ← Empty list that will contain the sorted elements
    S ← Set of all nodes with no incoming edges
    while S is non-empty do
    remove a node n from S
    insert n into L
    for each node m with an edge e from n to m do
    remove edge e from the graph
    if m has no other incoming edges then
    insert m into S
    if graph has edges then
    return error (graph has at least one cycle)
    else
    return L (a topologically sorted order)


// The idea is to build the precedence between alphabets to create a
// directed graph. If there is cycle, then training set is not correct - error out;
// otherwise do topological sort for constructed DAG to return alphabet in partial order.
class SOlu{
  typedef unordered_map<int, unordered_set<int> > Graph;
  vector<int> topologicalSort(Graph &gr) {
    vector<int> sorted;
    queue<int> degree_zero;
    while(!gr.empty()) {
      for(auto i = gr.begin(), j = i; i != gr.end(); i = j) {
        j++;
        if(i->second.empty()) {
          degree_zero.push(i->first);
          gr.erase(i);
        }
      }
      while(!degree_zero.empty()) {
        int node = degree_zero.front();
        degree_zero.pop();
        sorted.push_back(node);
        for(auto i = gr.begin(); i != gr.end(); i++)
          i->second.erase(node);
      }
    }
    return sorted;
  }
}

//DFS
class SOlu{
  vector<int> topo_sort() {
    vector<int> ret;
    vector<bool> visited(_num_verts, false);
    function<void(int)> visit = [&](int v) {
      visited[v] = true;
      for(auto adj : _adjs[v]) {
        if(!visited[adj]) visit(adj);
      }        ret.push_back(v);
    };
    for(auto i = 0; i < _num_verts; ++i) {
      if(!visited[i]) visit(i);
    }
    return ret;
  }
}
