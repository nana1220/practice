/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.
For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,
return 1->4->3->2->5->NULL.
Note:

Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
 */

class solu{
  ListNode* reverse_iter(ListNode* n, int num) {
    if(!n) return nullptr;
    if(num == 1) return n;

    ListNode dummy(0);
    dummy.next = n;
    auto pre = &dummy;

    auto cur = n->next;
    while(cur && --num > 0) {
      n->next = cur->next;
      cur->next = pre->next;
      pre->next = cur;
      cur = n->next;
    }

    return dummy.next;
  }
  ListNode *reverseBetween(ListNode *head, int m, int n) {
    ListNode dummy(0);
    dummy.next = head;
    auto cur = head;
    auto pre = &dummy;
    for(int i = 1; i < m; ++i) {
      pre = cur;
      cur = cur->next;
    }

    pre->next = reverse_iter(cur, n-m+1);

    return dummy.next;
  }

  // reucrsion reverse
  ListNode* reverse(ListNode* n) {
    if(!n || !n->next) return n;

    auto next = n->next;
    auto ret = reverse(next);
    next->next = n;
    n->next = nullptr;
    return ret;
  }

  //print reversely
  void print_reservely( ListNode * node ) {
    if (!node) return;
    if (node->next) print_reservely(node->next);
    printf("%d\n", node->val);
  }

}