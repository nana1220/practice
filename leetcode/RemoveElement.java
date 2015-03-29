/*
Given an array and a value, remove all instances of that value in place and return the new length.
The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
public class RemoveElement {
  public int removeElement(int[] A, int elem) {
    if (A.length == 0) return 0;
    int last = A.length - 1;
    for (int i = 0; i < last; i++) {
      if (A[i] == elem) {
        while (last > i && A[last] == elem ) { // range check first
          last--;
        }
        if (last == i) return last; // A[last] = elem, so end position is last - 1, length = last
        A[i] = A[last];
        last--;
      }
    }
    if (A[last] != elem) return last + 1;
    else return last;
  }
}