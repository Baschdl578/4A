package edu.kit.informatik;

/**
 * @version 1.0
 * @author Sebastian Schindler
 */
public class Number extends ListElement {
    private int number;

    /**
     * Constructor for a new number
     * @param number number value
     * @throws IllegalArgumentException If number smaller than zero
     */
    public Number(int number) throws IllegalArgumentException {
        super();
        if (number > 0) {
            this.number = number;
        } else throw new IllegalArgumentException();
    }

    /**
     * Getter for the value of the number
     * @return this.number
     */
    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object number) {
        if (!(number instanceof Number)) return false;
        Number tmp = (Number) number;
        return tmp.getNumber() == this.number;
    }


    @Override
    public String toString() {
        return (new Integer(this.getNumber())).toString();
    }
}
//l