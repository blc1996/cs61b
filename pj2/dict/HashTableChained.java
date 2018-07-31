/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
    List[] dict;
    int size;
    int numOfBuckets;


    
    
    
    public void test(){
        int i, collideTime = 0;
        double expect = 1.0;
        String results = " ";
        for(i = 0; i < numOfBuckets; i++){
            if(dict[i] != null){
                results = results + "[" + String.valueOf(dict[i].length()) +"]";
                collideTime = collideTime + dict[i].length() - 1;
            }
            else
                results = results + "[0]";
        }
        for(i=0;i<size;i++)
            expect = expect * (1 - 1.0/numOfBuckets);
        expect = size - numOfBuckets + numOfBuckets * expect;
        System.out.println(results);
        System.out.println("collide happens "+ collideTime + " times");
        System.out.println("expected to happen "+ String.valueOf(expect) + " times");
    }
    
    public String[] String(){
        try{
            String[] temp = new String[size];
            int count = 0;
            ListNode currentNode;
            for(int i = 0; i < numOfBuckets; i++){
                if(dict[i] != null){
                    currentNode = dict[i].front();
                    while(currentNode.isValidNode()){
                        temp[count] = (String)(((Entry)(currentNode.item())).value);
                        count++;
                        currentNode = currentNode.next();
                    }
                }
            }
            return temp;
        }catch(InvalidNodeException ine){
            System.err.println(ine);
        }
        return null;
    }

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
      dict = new DList[sizeEstimate * 3];
      size = 0;
      numOfBuckets = sizeEstimate * 3;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
      dict = new DList[97];
      size = 0;
      numOfBuckets = 97;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
      int n = (Math.abs(3 * code + 11) % 16908799) % numOfBuckets;
    return n;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    if(size == 0)
        return true;
    else
        return false;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
      try{
          int code = compFunction(key.hashCode());
          Entry temp = new Entry();
          temp.key = key;
          temp.value = value;
          //System.out.println(value.hashCode());
          if(dict[code] == null)
              dict[code] = new DList();
          dict[code].insertBack(temp);
          size++;
          return (Entry)(dict[code].back().item());
      }catch(InvalidNodeException e1){
          System.out.println(e1);
      }
      return null;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
      int code;
      code = compFunction(key.hashCode());
      if(dict[code] == null){
          return null;
      }
      ListNode currentNode = dict[code].front();
      try{
          while(true){
              if(((Entry)(currentNode.item())).key == key)
                  return (Entry)currentNode.item();
              else
                  currentNode = currentNode.next();
          }
      }catch(InvalidNodeException e1){
          //do nothing
      }
      return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
      int code;
      code = compFunction(key.hashCode());
      Entry temp;
      if(dict[code] == null){
          return null;
      }
      ListNode currentNode = dict[code].front();
      try{
          while(true){
              if(((Entry)(currentNode.item())).key == key){
                  temp = (Entry)currentNode.item();
                  currentNode.remove();
                  size--;
                  return temp;
              }
              else
                  currentNode = currentNode.next();
                  }
      }catch(InvalidNodeException e1){
          //do nothing
      }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
      dict = new DList[numOfBuckets];
      size = 0;
  }

}
