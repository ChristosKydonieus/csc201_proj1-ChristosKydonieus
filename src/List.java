/**
 * This is a list interface that extends the iterable interface.
 * @param <T> is a generic object type
 */
public interface List<T> extends Iterable<T>{
    /**
     * This method needs to be implemented and will add the item in some way.
     * @param item to be added to list
     */
    void add(T item);

    /**
     * This method needs to be implemented and will check to see if the list has the item being searched.
     * @param item to be looked for in the list
     * @return  whether or not its in the list
     */
    boolean contains(T item);

    /**
     * This method needs to be implemented and will return the size of the list
     * @return the length of the list
     */
    int size();

    /**
     * This method needs to be implemented and will check if the list is empty
     * @return whether or not the list is empty
     */
    boolean isEmpty();
}
