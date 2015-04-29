/*
There’s a new language which uses the latin alphabet. However, you don’t know the order among letters.
You receive a list of words lexicographically sorted by the rules of this new language. From this list, derive one valid particular ordering of letters in this language.

e.g. dictionry:
  {"ze", "yf", "xd", "wd", "vd", "ua", "tt", "sz", "rd",
  "qd", "pz", "op", "nw", "mt", "ln", "ko", "jm", "il",
  "ho", "gk", "fa", "ed", "dg", "ct", "bb", "ba"}

Output:
z, y, x, w, v, u, t, s, r, q, p, o, n, m, l, k, j, i, h, g, f, e, d, c, b, a
 */

// topological sort
class Solu{
  string get_order(const vector<string>& dict) {
    Graph g(26);
    auto build_graph = [&](const string& w1, const string& w2) {
      auto len = min(w1.length(), w2.length());
      for(auto i = 0; i < len; ++i) {
        if(w1[i] < w2[i]) {
          g.add_edge(w1[i] - 'a', w2[i] - 'a');
        } else if(w1[i] > w2[i]) {
          g.add_edge(w2[i] - 'a', w1[i] - 'a');
        }
      }
    };
    for(auto i = 0; i < dict.size() - 1; ++i) {
      build_graph(dict[i], dict[i+1]);
    }
    auto sorted = g.topo_sort();
    string ret;
    for(auto n : sorted) {
      ret += 'a' + (char)n;
    }
    return ret;
  }
}