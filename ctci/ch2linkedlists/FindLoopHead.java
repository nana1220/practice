package ch2linkedlists;

public class FindLoopHead {

  public static void main(String[] args) {

  }

  static <E> ListNode<E> findLoopHead(ListNode<E>, list) {
    ListNode<E> head = list;
    ListNode<E> slow = list;
    ListNode<E> fast = list;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      // assume pos of list head is 0, pos of loop head is k, so non-loop length is k, loop length is N
      // slow and fast will first meet at (N - k % N) position in the loop,
      // slow will then go (k % N + n * N) steps (n = 0, 1, ...) back to loop head
      // list head is (k % N + k / N * N) steps from the loop head
      // so head and slow will meet at the loop head by both moving one step a time
      if (fast == slow) {
        break;
      }
    }
    if (fast == null || fast.next == null) { // no loops
      return null;
    }
    while (head != slow) {
      head = head.next;
      slow = slow.next;
    }
    return head;
  }
}