/*
Given a integer array, please determine if it is a postorder traversal result of an arbitrary Binary Search Tree.
For example:

{5,7,6,9,11,10,8} is valid
{7,4,6,5} is not valid
 */

class SOlu{
  bool valid_bst_post(int arr[], int n) {
    if(!arr || n <= 0) return false;
    int root = arr[n - 1]; // last elem is root

    int left = 0;
    for(; left < n - 1; ++left) { // from left find the first value > root, left side of this value is left subtree
      if(arr[left] > root) break;
    }

    for(int right = left; right < n - 1; ++right) { // all value starting from left, is right subtree and shoould be larger than root
      if(arr[right] < root) return false;
    }

    bool l_valid = true, r_valid = true;
    if (left > 0) l_valid = valid_bst_post(arr, left); // check with left subarray
    if (left < n - 1) r_valid = valid_bst_post(arr + left, n - left - 1); // check with right subarray excluding last elem(current root)

    return l_valid && r_valid;
  }
}