/*
[Array] Counting Array Having a int array A[], B[] is a generated array based on A[], which B[i] is the count of elements in A[i+1] ~ A[n-1] 
which is smaller than A[i]. Given B[] find A[], assume elements in A is 1-N. For example: B is {3, 0, 1, 0}, A should be {4, 1, 3, 2}

HINT: create a increasing options list 1-N, select B[i]-th element in the options as A[i], and delete it from option.

Solution 1: create a buffer [1,B.length], select B[i]-th element in buffer as A[i], and delete it. 
If use array to do select O(1) and delete O(N), so the entire algorithm is O(N^2). 

Optimize Solution: use BST, create a balanced BST, and implements topK and deleteNode method, 
for each B[i], select(root, B[i]+1) node as A[i], and delete that node, each operation is O(lgN), 
so the entire time complexity is O(NlgN).
*/

package com.interview.flag.g;

import com.interview.utils.ConsoleWriter;
 
public class G1_RecoverFromCountingArray {
    private class BSTNode {
        int value;
        int size;
        BSTNode left, right;

        public BSTNode(int value) {
            this.value = value;
            this.size = 1;
        }
    }

    private BSTNode create(int N) {
        return create(1, N);
    }

    private BSTNode create(int low, int high) {
        if (low > high) return null;
        int mid = low + (high - low) / 2;
        BSTNode node = new BSTNode(mid);
        node.size = high - low + 1;
        node.left = create(low, mid - 1);
        node.right = create(mid + 1, high);
        return node;
    }

    private BSTNode select(BSTNode node, int K) {
        if (node == null) return null;
        int leftSize = node.left == null ? 0 : node.left.size;// check null
        if (K == leftSize + 1) return node;
        else if (K <= leftSize) return select(node.left, K);
        else return select(node.right, K - leftSize - 1);
    }

    private static BSTNode delete(BSTNode node, BSTNode target) {
        if (node == null) return null;
        if (target.value < node.value) node.left = delete(node.left, target);
        else if (target.value > node.value) node.right = delete(node.right, target);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BSTNode successor = node.right;
            BSTNode parent = node;
            while (successor.left != null) {
                parent = successor;
                successor = successor.left;
            }
            node.value = successor.value;
            if (parent.left == successor) parent.left = successor.right;
            else parent.right = successor.right;
        }
        node.size--; // don't forget
        return node;
    }

    public int[] recover(int[] B) {
        BSTNode root = create(B.length);
        int[] A = new int[B.length];
        for (int i = 0; i < B.length; i++) {
            BSTNode node = select(root, B[i] + 1);
            A[i] = node.value;
            root = delete(root, node);
        }
        return A;
    }

    public static void main(String[] args) {

        G1_RecoverFromCountingArray recover = new G1_RecoverFromCountingArray();
        int[] B = new int[]{4, 0, 1, 1, 0};
        //5, 1, 3, 4, 2
        ConsoleWriter.printIntArray(recover.recover(B));
        B = new int[]{0, 1, 0, 0, 0};
        //1, 3, 2, 4, 5
        ConsoleWriter.printIntArray(recover.recover(B));
        B = new int[]{5, 0, 1, 1, 0, 0};
        //6, 1, 3, 4, 2, 5
        ConsoleWriter.printIntArray(recover.recover(B));
    }
}
