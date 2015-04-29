/*
一个文件里存着代码和注释，注释为C++风格（/* */ //），要求print所有代码除了注释
 */

class SOlu{
  void print_without_comments(const string& file) {
    ifstream in(file);
    if(!in) return;

    std::string str((std::istreambuf_iterator<char>(in)), std::istreambuf_iterator<char>()); // put all source file into a string

    vector<pair<int, int>> removes;
    int last_head = -1;

    int id = 0;
    while(id < str.length()) {
      if(str[id] == '/' && str[id+1] == '*') {
        if(last_head == -1) {
          last_head = id;
        }
        id+=2;
      } else if(str[id] == '*' && str[id+1] == '/') {
        if(id+2 < str.length() && str[id+2] == '*') {
          // */*
          if(last_head == -1) { // if no /* before, take /*
            last_head = id+1;
            id+=3;
          } else { // if there is /* before, take */
            removes.push_back({last_head, id+1});
            last_head = -1;
            id+=2;
          }
        } else {
          if(last_head != -1) {
            removes.push_back({last_head, id+1});
            last_head = -1;
          }
          id+=2;
        }
      } else if(str[id] == '/' && str[id+1] == '/') {
        if(last_head == -1) {
          auto rtn = str.find('\n', id);
          if(rtn == string::npos) {
            removes.push_back({id, str.length()-1});
          } else {
            removes.push_back({id, rtn-1}); // note don't remove '\n'
          }
          id = removes.back().second + 1;
        } else { // if find "//" after a efective "/*", ignore "//"
          id+=2;
        }
      } else {
        id++; // not /* or */ or */* or
      }
    }
    if(last_head != -1) { // if has an effecitve /* till end
      removes.push_back({last_head, str.length() - 1});
    }

    int last = 0;
    for(auto rem : removes) { // print
      cout << str.substr(last, rem.first - last);
      last = rem.second + 1;
    }
    if(last < str.length()) // if removes's last interval > str.length(), print last
      cout << str.substr(last) << endl;

    in.close();
  }
}