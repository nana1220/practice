/*
Design an algorithm that reads a sequence of packets and maintains a uniform random subset of size k of the read
 packets when the n >= k-th packet is read.
 */

class Solu{
  vector<int> reservoir_sampling(istringstream* sin, int k) {
    int x;
    vector<int> R;

    for(int i = 0; i < k && *sin >> x; ++i) {
      R.push_back(x);
    }

    int ele_num = k + 1;
    while(*sin >> x) {
      default_random_engine gen((random_device())());

      uniform_int_distribution<int> dis(0, ele_num++);
      int tar = dis(gen);
      if(tar < k){
        R[tar] = x;
      }
    }
    return R;
  }
}