/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
 */

/*
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

// since random pointer can return a node that has been cloned, use HashMap like the way in GraphClone
public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
    Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();
    RandomListNode node = head;
    while(node!=null){
      if(!map.containsKey(node)) map.put(node, new RandomListNode(node.label));
      if(node.next!=null) {
        if(!map.containsKey(node.next)) map.put(node.next, new RandomListNode(node.next.label));
        map.get(node).next=map.get(node.next);
      } else{
        map.get(node).next = null;
      }

      if(node.random!=null) {
        if(!map.containsKey(node.random)) map.put(node.random, new RandomListNode(node.random.label));
        map.get(node).random=map.get(node.random);
      } else{
        map.get(node).random=null;
      }
      node = node.next;
    }
    return map.get(head);
  }
}

  // this solution use an extra pointer q to connect clone nodes
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null)
      return null;
    HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
    RandomListNode newHead = new RandomListNode(head.label);

    RandomListNode p = head;
    RandomListNode q = newHead;
    map.put(head, newHead);

    p = p.next;
    while (p != null) {
      RandomListNode temp = new RandomListNode(p.label);
      map.put(p, temp);
      q.next = temp;
      q = temp;
      p = p.next;
    }

    p = head;
    q = newHead;
    while (p != null) {
      if (p.random != null)
        q.random = map.get(p.random);
      else
        q.random = null;

      p = p.next;
      q = q.next;
    }

    return newHead;
  }