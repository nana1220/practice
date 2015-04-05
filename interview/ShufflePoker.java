class Shuffle {
  void shuffle() {
    for (int i = v.size() - 1; i >= 1; i--) {
      swap(v, v[random_between(0, i - 1)])
    }
  }
}