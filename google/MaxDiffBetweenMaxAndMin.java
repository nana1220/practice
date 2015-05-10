/*
给一个list和k（number）。找一个区域k，使得这个区域里k的最大值和最小值的差值最大，返回这个值。
用heap或priority queue做dp。
 */

// sliding window
// 我想的用是两个deque，一个存最大，一个存最小，每次求diff然后和max比较，应该是O(N)吧
// 维护2个单调队列，每次尝试更新头尾，取出两个队列的头，相减后与max比较即可。