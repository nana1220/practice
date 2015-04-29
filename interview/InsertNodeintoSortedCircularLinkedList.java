/*
Write an algorithm to insert a new value into a circular   sorted linked list
 */

class Solu{
  void sortedInsert(ListNode* & head_ref, ListNode* new_node) {
    ListNode* current = head_ref;

    if (!current) { // if head = null, set head = newNode
      new_node->next = new_node;
      head_ref = new_node;
    } else if (current->val >= new_node->val) { // goto last node, set lastNode.next = newNode, head = newNode
      // If value is smaller than head's value then
      // we need to change next of last node
      while(current->next != head_ref)
        current = current->next;
      current->next = new_node;
      new_node->next = head_ref;
      head_ref = new_node;

    } else {
      // Locate the node before the point of insertion
      while (current->next!= head_ref && current->next->val < new_node->val)
        current = current->next;

      new_node->next = current->next;
      current->next = new_node;
    }
  }
}