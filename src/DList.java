/**
 * This class is a doubly linked list utilizing the java api linked list.
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

// first in first out
public class DList<T> implements List<T>, Iterable<T> {
    protected LinkedList<T> list = new LinkedList<T>();

    /**
     * this method adds a datapoint to the end of the list
     *
     * @param data to be added
     */
    public void add(T data) {
        list.add(data);
    }

    /**
     * this method checks if the list contains a specified data point
     *
     * @param data that is being looked for
     * @return true if in there, false if not
     */
    public boolean contains(T data) {
        return list.contains(data);
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
