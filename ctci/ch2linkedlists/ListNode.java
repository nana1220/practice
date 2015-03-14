package ch2linkedlists;

/*
 * singly linked list
 */

class ListNode <E> {
  E data;
  ListNode<E> next;

  public ListNode(E data) {
    this.data = data;
    next = null;
  }

  public ListNode(E[] arr){
    ListNode<E> head = buildList(arr);
    this.data = head.data;
    this.next = head.next;
  }

  public static <E> ListNode<E> buildList(E[] arr){
    ListNode<E> head = new ListNode<E>(arr[0]);
    ListNode<E> p = head;
    for (int i = 1; i < arr.length; i++){
      p.next = new ListNode<E>(arr[i]);
      p = p.next; // the last p.next = null
    }
    return head;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.data);
    sb.append("-> ");
    ListNode<E> p = this.next;
    while (p != null) {
      sb.append(p.data);
      sb.append("-> ");
      p = p.next;
    }
    sb.append("null");
    return sb.toString();
  }
}