/*
Give you an array which has n integers,it has both positive and negative integers.Now you need sort this array in a
special way.After that,the negative integers should in the front,and the positive integers should in the back.Also the
 relative position should not be changed.

eg. -1 1 3 -2 2 ans: -1 -2 1 3 2.
o(n)time complexity and o(1) space complexity is perfect.
 */

/*
This can be done in O(nlogn) using divide and conquer scheme. Before starting the algorithm, please see the following observation:

Observation: given an array A, say [1, -2, ..., 4], with n elements, we can get the inverse of A, denoted as A’ (4, …, -2, 1),
in \theta(n) time with O(1) space complexity.

The basic idea of the algorithm is as follows:
1.	We recursively ‘sort’ two smaller arrays of size n/2 (here ‘sort’ is defined in the question)
2.	Then we spend \theta(n) time merging the two sorted smaller arrays with O(1) space complexity.
How to merge?
Suppose the two sorted smaller array is A and B. A1 denotes the negative part of A, and A2 denotes positive part of A.
Similarly, B1 denotes the negative part of B, and B2 denotes positive part of B.
2.1.	Compute the inverse of A2 (i.e., A2’) in \theta(|A2|) time; compute the inverse of B1 (i.e., B1’) in \theta(|B1|) time.
 [See observation; the total time is \theta(n) and space is O(1)]
Thus the array AB (i.e., A1A2B1B2) becomes A1A2’B1’B2.
2.2.	Compute the inverse of A2’B1’ (i.e., B1A2) in \theta(|A2|) time. [See observation; the total time is \theta(n) and space is O(1)]
Thus the array A1A2’B1’B2 becomes A1B1A2B2. We are done.
mergesort also return mid pointer, for easy locate last minues element

Time complexity analysis:
T(n) = 2T(n/2) + \theta(n) = O(nlogn)
 */