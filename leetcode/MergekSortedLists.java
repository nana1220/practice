/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */

/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class MergekSortedLists {
  /*
   * use a min heap maintain heads of every linked list
   * poll the head with min val, and add next node of that head into min heap
   * time: O(nlog(k)), heap size k, heap insert O(log(k)), n total nodes, n loops O(n)
   */
  public ListNode mergeKLists(List<ListNode> lists) {
    // NOTE: check error, if lists.size() = 0, minheap cannot be created
    if (lists == null || lists.size() == 0) return null;

    Comparator<ListNode> listComp = new Comparator<ListNode>() {
      public int compare(ListNode n1, ListNode n2) {
        return n1.val - n2.val;
      }
    };
    // lists.size must > 0, every where has edge cases
    PriorityQueue<ListNode> minheap = new PriorityQueue<ListNode>(lists.size(), listComp);
    for (ListNode node : lists) {
      if (node != null) { // NOTE: dont forget to check!!!!!!
        minheap.add(node);
      }
    }
    ListNode curr = new ListNode(0);
    ListNode head = curr; // also use dummy node maintain head
    while (!minheap.isEmpty()) {
      curr.next = minheap.poll();
      curr = curr.next;
      if (curr.next != null) {
        minheap.add(curr.next);
      }
    }
    return head.next; // dummy node.next is head
  }
}