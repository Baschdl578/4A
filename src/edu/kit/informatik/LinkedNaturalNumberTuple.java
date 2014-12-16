package edu.kit.informatik;

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
        this.tuple = new DualLinkedList();
        if (numbers.length != 0) {

            for (int i: numbers) {
                Number tmp;
                try {
                    tmp = new Number(i);
                } catch (IllegalArgumentException e) {
                    continue;
                }
                if (tuple.getTail() != null && tuple.getStart() != null) {
                    tuple.getTail().setNext(tmp);
                    tmp.setPrevious(tuple.getTail());
                    tuple.setTail(tmp);
                    continue;
                }
                tuple.setTail(tmp);
                tuple.setStart(tmp);
                tmp.setNext(null);
                tmp.setPrevious(null);
            }
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
        Number element;
        try {
            element = new Number(number);
        } catch (IllegalArgumentException e) {
            return;
        }
        if (tuple.getTail() != null && tuple.getStart() != null) {
            element.setPrevious(tuple.getTail());
            tuple.getTail().setNext(element);
            element.setNext(null);
            tuple.setTail(element);
            return;
        }
        tuple.setTail(element);
        tuple.setStart(element);
        element.setPrevious(null);
        element.setNext(null);
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
        int index = this.indexOf(number);

        while (index != -1) {
            out = true;
            this.removePos(index);
            index = this.indexOf(number);
        }
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
        ListElement last;
        ListElement first;
        if (firstPosition < lastPosition) {
            last = this.removePos(lastPosition);
            first = this.removePos(firstPosition);

            if (last == null || first == null) return false;

            this.insertPos((Number) last, firstPosition);
            this.insertPos((Number) first, lastPosition);
            return true;
        } else {
            first = this.removePos(firstPosition);
            last = this.removePos(lastPosition);

            if (last == null || first == null) return false;

            this.insertPos((Number) first, lastPosition);
            this.insertPos((Number) last, firstPosition);
            return true;
        }

    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LinkedNaturalNumberTuple)) return false;

        return this.tuple.equals(((LinkedNaturalNumberTuple) object).tuple);
    }

    @Override
    public String toString() {
        String out = "";
        Number current = (Number) this.tuple.getStart();
        if (current != null) {
            out += current.toString();
            current = (Number) current.getNext();
        } else {
            return "-1";
        }
        while (current != null) {
            out += "," + current.toString();
            current = (Number) current.getNext();
        }

        return out;
    }

    /**
     * Prints the current tuple
     */
    public void print() {
        Terminal.printLine(this.toString());
    }

    /**
     * Removes the Element at a specific position from the tuple
     * @param pos position
     * @return Element at that position, null if unsuccessful
     */
    public ListElement removePos(int pos) {
        if (pos == 0) {
            ListElement start = tuple.getStart();
            if (start != null) {
                tuple.setStart(start.getNext());
                tuple.getStart().setPrevious(null);
                return start;
            }
            return null;
        }
        ListElement prev = tuple.getStart();
        if (prev == null) return null;
        for (int i = 1; i < pos; i++) {
            prev = prev.getNext();
            if (prev == null) return null;
        }
        if (prev.getNext() == null) return null;
        ListElement next = prev.getNext().getNext();
        ListElement out = prev.getNext();
        if (next != null) {
            prev.setNext(next);
            next.setPrevious(prev);
            return out;
        }
        tuple.setTail(prev);
        prev.setNext(null);
        return out;
    }

    /**
     * Inserts an Element at a specific Position
     * @param number Element to insert
     * @param pos Position
     * @return true if successful
     */
    public boolean insertPos(Number number, int pos) {
        if (pos == 0) {
            ListElement next = tuple.getStart();
            tuple.setStart(number);
            number.setPrevious(null);
            if (next != null) {
                next.setPrevious(number);
                number.setNext(next);
            } else {
                tuple.setTail(number);
                number.setNext(null);
            }
            return true;
        }

        ListElement prev = tuple.getStart();
        if (prev == null) return false;
        for (int i = 1; i < pos; i++) {
            prev = prev.getNext();
            if (prev == null) return false;
        }
        ListElement next = prev.getNext();

        prev.setNext(number);
        number.setPrevious(prev);
        if (next != null) {
            next.setPrevious(number);
            number.setNext(next);
            return true;
        }
        tuple.setTail(number);
        number.setNext(null);
        return true;
    }

    /**
     * Waits for input, then calls appropriate methods
     */
    public void getInput() {
        String command = Terminal.readLine();

        String[] commands = command.split(" ");

        if (commands[0].equals("quit")) return;

        if (commands[0].equals("info")) {
            Terminal.printLine(this.toString());
        }

        if (commands[0].equals("min")) {
            String out = (new Integer(this.min())).toString();
            if (out.equals("-1")) {
                Terminal.printLine("The tuple is empty.\nEnd of line.");
            }
            Terminal.printLine(out);
        }

        if (commands[0].equals("max")) {
            String out = (new Integer(this.max())).toString();
            if (out.equals("-1")) {
                Terminal.printLine("The tuple is empty.\nEnd of line.");
            }
            Terminal.printLine(out);
        }

        if (commands[0].equals("swap")) {
            int first = Integer.parseInt(commands[1]);
            int second = Integer.parseInt(commands[2]);

            if (!this.swap(first, second)) {
                Terminal.printLine("Swapping unsuccessful.\nEnd of Line.");
            }
        }

        if (commands[0].equals("remove")) {
            int number = Integer.parseInt(commands[1]);
            if (remove(number)) {
                Terminal.printLine("true");
            } else Terminal.printLine("false");
        }

        if (commands[0].equals("insert")) {
            int number = Integer.parseInt(commands[1]);
            this.insert(number);
        }
        this.getInput();
    }

    /**
     * Main method
     *
     * Initializes new LinkedNaturanNumberTuple and waits for Input
     * @param args Arguments (none)
     */
    public static void main(String args[]) {
        int[] a = new int[0];
        LinkedNaturalNumberTuple mainTuple = new LinkedNaturalNumberTuple(a);
        mainTuple.getInput();
    }
}
//l