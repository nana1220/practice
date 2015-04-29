/*
nterleave two linked-list
for example
Given
1->2->3->4
5->6
return 1->5->2->6->3->4
 */

class Solu{
  ListNode * interleave(ListNode *p, ListNode *q) {
    if(!p && !q) return nullptr;
    if(!p) return q;
    if(!q) return p;
    q->next = interleave(p->next,q->next);
    p->next = q;
    return p;
  }
}