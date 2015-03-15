package ch2linkedlists;

import java.lang.Integer;
import java.lang.System;

/**
 * You have two numbers represented by a linked list, where each node contains a
 * single digit. The digits are stored in reverse order, such that the 1's digit is at the
   head of the list. Write a function that adds the two numbers and returns the sum
   as a linked list.
   EXAMPLE
   Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is, 617 + 295.
   Output: 2 -> 1 -> 9.That is, 912.
   FOLLOW UP
   Suppose the digits are stored in forward order. Repeat the above problem.
   EXAMPLE
   Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is, 617 + 295.
   Output: 9 -> 1 -> 2.That is, 912.
 */

public class SumUpList {

  public static void main(String[] args) {
    ListNode<Integer> list1 = ListNode.buildList(new Integer[] {7, 1, 6});
    ListNode<Integer> list2 = ListNode.buildList(new Integer[] {5, 9, 2});

    ListNode<Integer> res1 = sum(list1, list2);
    System.out.println(res1.toString());

    ListNode<Integer> res2 = forwardSum(list1, list2, 0);
    System.out.println(res2.toString());

    ListNode<Integer> res3 = backwardSum(list1, list2);
    System.out.println(res3.toString());

    list1 = ListNode.buildList(new Integer[]{1, 2, 3, 4});
    list2 = ListNode.buildList(new Integer[]{9, 9, 7, 6, 5});

    res1 = sum(list1, list2);
    System.out.println(res1.toString());

    res2 = forwardSum(list1, list2, 0);
    System.out.println(res2.toString());

    overTen = false;
    res3 = backwardSum(list1, list2);
    System.out.println(res3.toString());
  }

  /*
   * non-recursive method
   */
  static ListNode<Integer> sum(ListNode<Integer> list1, ListNode<Integer> list2) {
    if (list1 == null && list2 == null) {
      return null;
    }
    ListNode<Integer> curr = new ListNode<Integer>(0);
    ListNode<Integer> prev = null;
    ListNode<Integer> head = curr;
    int overTen = 0;
    while (list1 != null && list2 != null) {
      int val = list1.data + list2.data + overTen;
      if (val < 10) {
        overTen = 0;
        curr.data = val;
      } else {
        overTen = 1;
        curr.data = val % 10;
      }
      prev = curr;
      curr.next = new ListNode<Integer>(0);
      curr = curr.next;
      list1 = list1.next;
      list2 = list2.next;
    }
    if (list1 != null) { // always assume list2 is longer, if list1 is longer point list2 to list1
      list2 = list1;
    }
    while (list2 != null) {
      int val = list2.data + overTen;
      if (val < 10) {
        overTen = 0;
        curr.data = val;
        curr.next = list2.next;
        return head;
      } else {
        overTen = 1;
        curr.data = val % 10;
        curr.next = new ListNode<Integer>(0);
        curr = curr.next;
        list2 = list2.next;
      }
    }
    if (overTen == 1) {
      curr.data = 1;
      curr.next = null;
    } else {
      // curr = null; Note: cannot make prev.next = null by setting curr = null
      prev.next = null; // this handles the case when list1, list2 and sum have same size
    }
    return head;
  }

  /*
   * digits are organized in reverse order
   * use forward recursive impl, input parameters change per recursion
   */
  static ListNode<Integer> forwardSum(ListNode<Integer> list1, ListNode<Integer> list2, int overTen) {
    if (list1 == null && list2 == null && overTen == 0) {
      return null;
    }
    ListNode<Integer> res = new ListNode<Integer>(0);
    int val = overTen;
    if (list1 != null) {
      val += list1.data;
    }
    if (list2 != null) {
      val += list2.data;
    }
    res.data = val % 10;
    res.next = forwardSum(list1 == null ? null : list1.next, list2 == null ? null : list2.next,
        val > 9 ? 1 : 0);
    return res;
  }

  /*
   * digits are organized in forward order
   * use backward recursive impl, returned values change per recursion
   *
   * Instead of using static carry or a reference class of carry, can also use a wrapper
   * class for both sumList and carry (overTen), then propagate it through the recursion.
   * Make a helper method for recrusively propagate wrapper class, and
   * use sum method check input list size, call helper method and check if there is an carry
   * for sum of highest digit.
   */
  static boolean overTen = false;
  static ListNode<Integer> backwardSum(ListNode<Integer> list1, ListNode<Integer> list2) {
    int size1 = size(list1);
    int size2 = size(list2);
    if (size1 != size2) {
      if (size1 > size2) {
        list2 = padding(list2, size1 - size2);
      } else {
        list1 = padding(list1, size2 - size1);
      }
    }
    if (list1 == null && list2 == null) {
      return null;
    }
    ListNode<Integer> list = backwardSum(list1.next, list2.next);
    if (overTen) {
      list.data += list1.data + list2.data;
      if (list.data > 9) {
        overTen = true;
        list.data %= 10;
        ListNode<Integer> excess = new ListNode<Integer>(0);
        excess.data = 1;
        excess.next = list;
        return excess;
      } else {
        overTen = false;
        return list;
      }
    } else {
      ListNode<Integer> newDigit = new ListNode<Integer>(0);
      newDigit.next = list;
      newDigit.data += list1.data + list2.data;
      if (newDigit.data > 9) {
        overTen = true;
        newDigit.data %= 10;
        ListNode<Integer> excess = new ListNode<Integer>(0);
        excess.data = 1;
        excess.next = newDigit;
        return excess;
      } else {
        overTen = false;
        return newDigit;
      }
    }
  }

  /*
   * compute list size
   * forward recursion, instructions are made as the instances of function being built
   */
  static int size(ListNode<Integer> list) {
    if (list == null) {
      return 0;
    }
    return 1 + size(list.next);
  }

  /*
   * padding zeros ahead of list
   * backward recursion, instructions are made as the instances of function being destroyed (returned)
   */
  static ListNode<Integer> padding(ListNode<Integer> list, int len) {
    if (len == 0) {
      return list;
    }
    ListNode<Integer> res = padding(list, len - 1);
    ListNode<Integer> zero = new ListNode<Integer>(0);
    zero.next = res;
    return zero;
  }

}