package ch17moderate;

public class InplaceSwap {

  void swap1(int a, int b) {
    a = a + b;
    b = a - b;
    a = a - b;
  }
  /*
   * The benefit of bit manipulation is it works for more data types than just integers
   */
  void swap2(int a, int b) {
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
  }
}
