package ch9recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * You have a stack of n boxes, with widths w, heights h and depths d The boxes
 * cannot be rotated and can only be stacked on top of one another if each box in the
 * stack is strictly larger than the box above it in width, height, and depth. Implement
 * a method to build the tallest stack possible, where the heigh t of a stack is the sum of
 * the heights of each box.
 */
public class StackOfBoxes {

  /*
   * edge case, if bottom is null, which should mean that every box can be above, which is the
   * initial state. So canBeAbove should return true if input is null
   */
  static ArrayList<Box> getMaxStack(Box[] boxes, Box bottom) {
    ArrayList<Box> maxStack = null;
    int maxHeight = 0;
    for(Box box : boxes) {
      if (box.canBeAbove(bottom)) { // base case
        ArrayList<Box> stack = getMaxStack(boxes, box);
        int height = getHeight(stack);
        if (height > maxHeight) {
          maxHeight = height;
          maxStack = stack;
        }
      }
    }
    if (maxStack == null) {
      // each top box will create a arraylist representing a stack and itself is on the top of the stack
      maxStack = new ArrayList<Box>();
    }
    if (bottom != null) { // bottom is a box not the "ground" then add it
      maxStack.add(bottom);
    }
    return maxStack;
  }

  /*
   * DP: cache result! Must clone() another copy, one for cache one for return value
   */
  static ArrayList<Box> getMaxStackDP(Box[] boxes, Box bottom, HashMap<Box, ArrayList<Box>> cache) {
    // Note edge case, exclude null (which means "ground")
    // Put cache check at the begining!!
    if (bottom != null && cache.containsKey(bottom)) { // Note interface: containsKey not contains
      return cache.get(bottom);
    }

    ArrayList<Box> maxStack = null;
    int maxHeight = 0;
    for(Box box : boxes) {
      if (box.canBeAbove(bottom)) { // base case
        ArrayList<Box> stack = getMaxStackDP(boxes, box, cache);
        int height = getHeight(stack);
        if (height > maxHeight) {
          maxHeight = height;
          maxStack = stack;
        }
      }
    }
    if (maxStack == null) {
      // each top box will create a arraylist representing a stack and itself is on the top of the stack
      maxStack = new ArrayList<Box>();
    }
    if (bottom != null) { // bottom is a box not the "ground" then add it
      maxStack.add(bottom);
    }
    // cache result anyway because
    // If the map previously contained a mapping for the key, the old value is replaced.
    cache.put(bottom, maxStack);
//    return (ArrayList<Box>) maxStack.clone();
    // clone return object, casting a non-parametrized type to parametrized will throw warning
    // so use copy constructor instead
    ArrayList<Box> res = new ArrayList<Box>(maxStack);
    return res;
  }

  static int getHeight(ArrayList<Box> boxes) {
    if (boxes == null) { // Don't forget edge case!
      return 0;
    }
    int h = 0;
    for (Box b : boxes) {
      h += b.height;
    }
    return h;
  }

  static public class Box {
    public int width;
    public int height;
    public int depth;
    public Box(int w, int h, int d) {
      width = w;
      height = h;
      depth = d;
    }

    public boolean canBeUnder(Box b) {
      if (width > b.width && height > b.height && depth > b.depth) {
        return true;
      }
      return false;
    }

    /*
     * Note edge case: input is null!! which can be used to choose the real bottom box
     */
    public boolean canBeAbove(Box b) {
      if (b == null) {
        return true;
      }
      if (width < b.width && height < b.height && depth < b.depth) {
        return true;
      }
      return false;
    }

    public String toString() {
      return "Box(" + width + "," + height + "," + depth + ")";
    }
  }

  public static void main(String[] args) {
    Box[] boxes = { new Box(3, 4, 1), new Box(5, 6, 2), new Box(7, 8, 3)};
    ArrayList<Box> stack = getMaxStack(boxes, null);
    //ArrayList<Box> stack = createStackR(boxes, null);
    for (int i = stack.size() - 1; i >= 0; i--) {
      Box b = stack.get(i);
      System.out.println(b.toString());
    }

    ArrayList<Box> stackDP = getMaxStackDP(boxes, null, new HashMap<Box, ArrayList<Box>>());
    for (int i = stackDP.size() - 1; i >= 0; i--) {
      Box b = stackDP.get(i);
      System.out.println(b.toString());
    }
  }
}