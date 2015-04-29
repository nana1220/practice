/*
Shuffle an array of size n such that each element has 1/n probability to remain in its original spot. The best solution has O(n) complexity.
 */

class solu{
  void shuffle(vector<int>& arr) {
    for (auto i = arr.size() - 1; i > 0; --i) {
      swap (arr[i], arr[rand() % (i+1)]);
    }
  }
}