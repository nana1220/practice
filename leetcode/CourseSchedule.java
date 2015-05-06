/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
There are several ways to represent a graph. For example, the input prerequisites is a graph represented by a list of edges. Is this graph representation appropriate?
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
 */

public class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if(numCourses==0) return true;
    boolean[] indegrees = new boolean[numCourses];
    for(int[] edge : prerequisites){
      indegrees[edge[1]] = true;
    }
    boolean[] visited=new boolean[numCourses];
    boolean[] backedgeNodes=new boolean[numCourses];
    int countIndegree =0;
    for(boolean in : indegrees){
      if(in) countIndegree++;
    }
    if(countIndegree==numCourses) return false;
    for(int i=0; i<numCourses; i++){ // start from nodes with no incoming edges
      if(!indegrees[i]){
        if(hasCycle(i, prerequisites, visited, backedgeNodes)) return false;
      }
    }
    int visitCount=0;
    for(boolean v : visited){
      if(v) visitCount++;
    }
    return visitCount==numCourses; // check if indegree nodes can visit all nodes, that is there are no isolated cycle
  }

  // dfs
  boolean hasCycle(int node, int[][] edges, boolean[] visited, boolean[] backedgeNodes){
    visited[node]=true;
    backedgeNodes[node]=true;
    for(int[] edge : edges){
      if(edge[0]==node){
        if(!visited[edge[1]]){
          if(hasCycle(edge[1],edges, visited, backedgeNodes))
            return true;
        } else if (backedgeNodes[edge[1]]) return true;

      }
    }
    backedgeNodes[node] = false;
    return false;
  }
}

class solu2{
  拓扑排序。用一个队列存入度为0的节点，依次出队，将与出队节点相连的节点的入度减1，直到队列为空。如里最后还有入度不为0的节点的话，说明有环，否则无环。

  复制代码
  1 class Solution {
    2 public:
        3     bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
      4         vector<vector<int>> graph(numCourses, vector<int>(0));
      5         vector<int> inDegree(numCourses, 0);
      6         for (auto u : prerequisites) {
        7             graph[u[1]].push_back(u[0]);
        8             ++inDegree[u[0]];
        9         }
      10         queue<int> que;
      11         for (int i = 0; i < numCourses; ++i) {
        12             if (inDegree[i] == 0) que.push(i);
        13         }
      14         while (!que.empty()) {
        15             int u = que.front();
        16             que.pop();
        17             for (auto v : graph[u]) {
          18                 --inDegree[v];
          19                 if (inDegree[v] == 0) que.push(v);
          20             }
        21         }
      22         for (int i = 0; i < numCourses; ++i) {
        23             if (inDegree[i] != 0) return false;
        24         }
      25         return true;
      26     }
    27 };
}