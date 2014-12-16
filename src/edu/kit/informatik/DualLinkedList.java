package edu.kit.informatik;

/**
 * @version 1.0
 * @author Sebastian Schindler
 */
public class DualLinkedList {
    private ListElement start;
    private ListElement tail;

    /**
     * Constructor for a new List with one Element
     * @param first First Element
     */
    public DualLinkedList(ListElement first) {
        this.start = first;
        this.tail = first;
        first.setNext(null);
        first.setPrevious(null);
    }

    /**
     * Constructs a new empty list
     */
    public DualLinkedList() {
        this.start = null;
        this.tail = null;
    }

    /**
     * Gets the start of the List
     * @return this.start
     */
    public ListElement getStart() {
        return start;
    }

    /**
     * Sets the start of the List
     * @param start new start
     */
    public void setStart(ListElement start) {
        this.start = start;
    }

    /**
     * Gets the tail of the List
     * @return this.tail
     */
    public ListElement getTail() {
        return tail;
    }

    /**
     * Sets the tail of the List
     * @param tail new tail
     */
    public void setTail(ListElement tail) {
        this.tail = tail;
    }

    /**
     * Returns the Element at the given Index
     * @param index Given Index
     * @return Element at that Index
     */
    public ListElement getIndex(int index) {
        ListElement out = this.getStart();
        for (int i = 0; i < index; i++) {
            if (out != null) {
                out = out.getNext();
            } else break;
        }
        return out;
    }


    @Override
    public boolean equals(Object list) {
        if (!(list instanceof DualLinkedList)) return false;
        DualLinkedList tmp = (DualLinkedList) list;

        Number current1 = (Number) this.getStart();
        Number current2 = (Number) tmp.getStart();

        do {
            if (!current1.equals(current2)) return false;
            current1 = (Number) current1.getNext();
            current2 = (Number) current2.getNext();
        } while (current1 != null && current2 != null);

        if (current1 != null || current2 != null) return false;

        return ((Number) tmp.getTail()).equals((Number) this.getTail());
    }
}

//l