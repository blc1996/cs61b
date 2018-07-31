/* DListNode2.java */

/**
 *  A DListNode2 is a node in a DList2 (doubly-linked list).
 */

public class DListNode2 {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public int itemNum;
  public int redVal;
  public int greenVal;
  public int blueVal;
  public DListNode2 prev;
  public DListNode2 next;

  /**
   *  DListNode2() constructor.
   */
  DListNode2() {
    itemNum = 0;
    redVal = 0;
    greenVal = 0;
    blueVal = 0;
    prev = null;
    next = null;
  }

  DListNode2(int Num, int redVal, int greenVal, int blueVal) {
    itemNum = Num;
    this.redVal = redVal;
    this.greenVal = greenVal;
    this.blueVal = blueVal;
    prev = null;
    next = null;
  }

}
