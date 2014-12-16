package edu.kit.informatik;

/**
 * @version 1.0
 * @author Sebastian Schindler
 */
public class ListElement {
    private ListElement next = null;
    private ListElement previous = null;

    /**
     * Constructs a new ListElement, nulls the pointers
     */
    public ListElement() {
        this.next = null;
        this.previous = null;
    }

    /**
     * Gets net next Element from the List
     * @return next ListElement
     */
    public ListElement getNext() {
        return next;
    }

    /**
     * Sets the next pointer
     * @param next new next ListElement
     */
    public void setNext(ListElement next) {
        this.next = next;
    }

    /**
     * gets the previous Element from the List
     * @return previous ListElement
     */
    public ListElement getPrevious() {
        return previous;
    }

    /**
     * sets the previous pointer
     * @param previous new previous ListElement
     */
    public void setPrevious(ListElement previous) {
        this.previous = previous;
    }


}
//l