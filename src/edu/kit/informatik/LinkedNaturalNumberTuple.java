package edu.kit.informatik;

import java.util.InputMismatchException;

/**
 * The {@code LinkedNaturalNumberTuple} class encapsulates one or more natural numbers (excluding 0) organized as a
 * tuple. This means:
 * <ul>
 * <li>encapsulated numbers are ordered, and</li>
 * <li>the same number may appear more than once.</li>
 * </ul>
 * Note that the ordering is not in numerical order but reflects the order of insertion.
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class LinkedNaturalNumberTuple {
    private DualLinkedList tuple;

    /**
     * Constructs a new tuple comprising the given {@code numbers} in the same order.
     * <p>
     * All values in {@code numbers} that are smaller or equal to 0 are ignored.
     * 
     * @param numbers
     *            the numbers to initialize this tuple
     */
    public LinkedNaturalNumberTuple(int[] numbers) {
        this.tuple = new DualLinkedList(new Number(numbers[0]));

        for (int i = 1; i < numbers.length; i++) {
            Number tmp;
            try {
                tmp = new Number(numbers[i]);
            } catch (InputMismatchException e) {
                continue;
            }
            tuple.getTail().setNext(tmp);
            tmp.setPrevious(tuple.getTail());
            tuple.setTail(tmp);
        }
    }

    /**
     * @return the smallest number stored in this tuple; -1 if this tuple is empty.
     */
    public int min() {
        if (tuple.getStart() == null) return -1;

        Number current = (Number) tuple.getStart();
        int min = current.getNumber();
        while ((current = (Number) current.getNext()) != null) {
            if (min > current.getNumber()) {
                min = current.getNumber();
            }
        }
        return min;
    }

    /**
     * 
     * @return the largest number stored in this tuple; -1 if this tuple is empty.
     */
    public int max() {
        if (tuple.getStart() == null) return -1;

        Number current = (Number) tuple.getStart();
        int max = current.getNumber();
        while ((current = (Number) current.getNext()) != null) {
            if (max < current.getNumber()) {
                max = current.getNumber();
            }
        }
        return max;
    }

    /**
     * Inserts the specified {@code number} at the end of this tuple. If {@code number} is smaller or equal to 0, this
     * method has no effect.
     * 
     * @param number
     *            the number to be inserted
     */
    public void insert(int number) {
        if (number < 1) return;

        Number element = new Number(number);
        element.setPrevious(tuple.getTail());
        tuple.getTail().setNext(element);
        element.setNext(null);
        tuple.setTail(element);
    }

    /**
     * Removes any occurrence of the specified {@code number} in this tuple. If {@code number} is not contained in this
     * tuple, this method has no effect.
     * 
     * @param number
     *            the number to be removed
     * @return {@code true} if one or more numbers have been removed; {@code false} else.
     */
    public boolean remove(int number) {
        boolean out = false;

        Number current = (Number) tuple.getStart();
        do {
            if (current.getNumber() == number) {
                out = true;
                if (current.getNext() != null && current.getPrevious() != null) {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                    continue;
                }
                if (current.getNext() != null) {
                    tuple.setStart(current.getNext());
                    tuple.getStart().setPrevious(null);
                    continue;
                }
                if (current.getPrevious() != null) {
                    tuple.setTail(current.getPrevious());
                    tuple.getTail().setNext(null);
                    continue;
                }
                tuple.setStart(null);
                tuple.setTail(null);
            }
            current = (Number) current.getNext();
        } while (current != null);

        return out;
    }

    /**
     * @param number
     *            the number whose index is to be returned. The first number in this tuple has index 0.
     * @return the index of the first occurrence of the specified {@code number} in this tuple; -1 if this tuple does
     *         not contain the {@code number}.
     */
    public int indexOf(int number) {
        Number current = (Number) tuple.getStart();
        int index = 0;
        do {
            if (number == current.getNumber()) {
                return index;
            }
            index++;
            current = (Number) current.getNext();
        } while (current != null);
        return -1;
    }

    /**
     * Counts the occurrences of {@code number} in this tuple.
     * 
     * @param number
     *            the number to count
     * @return how often the specified {@code number} is contained in this tuple
     */
    public int countNumbers(int number) {
        Number current = (Number) tuple.getStart();
        int counter = 0;
        do {
            if (number == current.getNumber()) {
                counter++;
            }
            current = (Number) current.getNext();
        } while (current != null);
        return counter;
    }

    /**
     * Swaps the numbers stored at positions {@code firstPosition} and {@code secondPosition}.
     * <p>
     * Notice that the first number in this tuple has index 0.
     * 
     * @param firstPosition
     *            the first index
     * @param lastPosition
     *            the second index
     * @return {@code true} if both numbers have been swapped successfully; {@code false} once at least one index is
     *         invalid.
     */
    public boolean swap(int firstPosition, int lastPosition) {
        ListElement first = tuple.getIndex(firstPosition);
        ListElement last = tuple.getIndex(lastPosition);
        if (first != null && last != null) {
            first.getPrevious().setNext(last);
            first.getNext().setPrevious(last);
            last.getNext().setPrevious(first);
            last.getPrevious().setNext(first);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        // TODO implement method, and then REMOVE following method call
        return super.equals(object);
    }

    @Override
    public String toString() {
        // TODO implement method, and then REMOVE following method call
        return super.toString();
    }

}
