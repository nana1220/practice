package ch2linkedlists;

import java.lang.Integer;
import java.util.Stack;

public class Palindrome {
  public static void main(String[] args) {
    ListNode<String> list = ListNode.buildList(args);
    System.out.println(reverseList(list, args.length).toString());

    list = ListNode.buildList(args);
    System.out.println(isPalindrome(list));

    list = ListNode.buildList(args);
    System.out.println(isPalindromeRecursive(list, args.length).res);
  }

  /*
   * reverse list iteratively or recursively, compare first half of original list to reverse list
   */
  static <E> ListNode<E> reverseList(ListNode<E> list, int length) { // length of list
    if (list.next == null) {
      return list;
    }
    ListNode<E> head = reverseList(list.next, length - 1);
    ListNode<E> tail = head; // keep the head of tail
    while (length > 2) { // go to tail's tail
      tail = tail.next;
      length--;
    }
    tail.next = list; // attach list head to tail
    list.next = null;
    return head;
  }

  /*
   * use stack, put first half of list into a stack then compare the stack with second half
   */
  static <E> boolean isPalindrome(ListNode<E> list) {
    if (list == null) {
      return false;
    }
    ListNode<E> slow = list;
    ListNode<E> fast = list;
    Stack<E> stack = new Stack<E>();
    while (true) {
      stack.push(slow.data);
      if (fast.next == null || fast.next.next == null) {
        break;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    if (fast.next == null) { // length is odd; length is even if fast.next != null
      stack.pop(); // pop middle if length is odd
    }
    while (slow.next != null) {
      if (!slow.next.data.equals(stack.pop())) {
        return false;
      }
      slow = slow.next;
    }
    return true;
  }

  /*
   * recursive compare from middle to two ends, totally  n / 2 recursive calls
   * need to have the knowledge about list size n.
   */
  static class Result<E> {
    boolean res;
    ListNode<E> node;

    public Result(boolean res, ListNode<E> node) {
      this.res = res;
      this.node = node;
    }
    public Result(boolean res) {
      this.res = res;
      this.node = null;
    }
  }

  static <E> Result<E> isPalindromeRecursive(ListNode<E> list, int length) {
    if (list == null || length == 0) {
      return new Result<E>(false);
    }
    if (length == 1) { // size is odd
      return new Result<E>(true, list.next);
    }
    if (length == 2) { // size is even
      return new Result<E>(list.data.equals(list.next.data), list.next.next);
    }
    Result<E> result = isPalindromeRecursive(list.next, length - 2);
    if (!result.res || result.node == null) {
      return new Result<E>(false);
    }
    result.res = list.data.equals(result.node.data);
    result.node = result.node.next;
    return result;
  }
}