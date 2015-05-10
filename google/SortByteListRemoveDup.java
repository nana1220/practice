/*
 * unsorted byte collection, return sorted byte and remove dups
 */

// map byte value it index of bit array, set value of array item to true
// return array index if value of the index is true
class Solution {
  ListNode<Byte> sort(ListNode<Byte> head) {
    boolean[] arr= new boolean[256];
    while(head != null) {
      index = (int) head.val; // byte to int to be used as array index
      if (!arr[index]) {
        arr[index] = true;
      }
      head = head.next;
    }
    ListNode<Byte> res = new ListNode<Byte>();
    curr = res;
    for (int i = 255; i >= 0; i--) {
      if(arr[i]) {
        curr.next = new ListNode<Byte>(i);
        curr = curr.next;
      }
    }
    return res.next;
  }
}