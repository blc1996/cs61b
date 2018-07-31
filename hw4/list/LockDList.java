/* DList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class LockDList extends DList {
    
    protected LockDListNode head;

    public void lockNode(DListNode node){
        ((LockDListNode)node).locked = true;
    }
    
    protected DListNode newNode(Object item, DListNode prev, DListNode next){
        DListNode temp = new LockDListNode(item, prev, next);
        return temp;
    }
    
    public void remove(DListNode node){
        if(((LockDListNode)node).locked != true){
            super.remove(node);
        }
    }
    
}
