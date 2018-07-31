/* DListNode.java */

package list;

/**
 *  A DListNode is a node in a DList (doubly-linked list).
 */

public class LockDListNode extends DListNode{

    boolean locked;

  LockDListNode(Object i, DListNode p, DListNode n) {
      super(i, p, n);
      locked = false;
  }
    
    LockDListNode() {
        item = 0;
        prev = null;
        next = null;
        locked = false;
    }
}
