/**
 * This class is a sorted linked list where each value is added in ascending sorted order and has an efficient contains
 * method that recognizes whether or not to keep looping based on the value of the searched item. It extends the c
 * comparable interface and extends the list and iterable interfaces.
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;

public class SortedList <T extends Comparable<? super T>> implements List <T> , Iterable <T> {
    protected LinkedList <T > list = new LinkedList <T >();

    /** This method adds an item to list in ascending sorted order by adding them to the list then sorting the list.
     *
     * @param item to be added to list
     */
    public void add(T item) {
        list.add(item);
        Collections.sort(list);
    }

    /** This method looks through the list for the T item. Since the list is in ascending sorted order, it checks if the
     * item is larger than the items in the list and if so, returns false and ends early.
     *
     * @param item is the item to be looked for in the list
     */
    public boolean contains(T item){
        Iterator<T> iterate = iterator();
        while (iterate.hasNext()){
            T cur = iterate.next();
            if (item.compareTo(cur) < 0){
                return false;
            }
            if (item.compareTo(cur) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns the length of the list
     *
     * @return the size of the list
     */
    public int size() {
        return list.size();
    }

    /**
     * This method checks if the list is empty
     *
     * @return true if the list is empty, false if otherwise
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * this overrides the toString function to return the list in a readable order
     *
     * @return the list in a readable format
     */
    @Override
    public String toString() {
        String str = "";
        for(T data: list)
            str += ("[" + data + "] ");
        return str;
    }

    /**
     * this overrides the iterator method to return an iterator object for the list
     *
     * @return a list iterator
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
