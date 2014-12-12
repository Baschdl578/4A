package edu.kit.informatik;

import java.util.InputMismatchException;

/**
 * @version 1.0
 * @author Sebastian Schindler
 */
public class Number extends ListElement {
    private int number;

    /**
     * Constructor for a new number
     * @param number number value
     * @throws InputMismatchException If number smaller than zero
     */
    public Number(int number) throws InputMismatchException {
        super();
        if (number > 0) {
            this.number = number;
        } else throw new InputMismatchException("<=0");
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
        Number tmp = (Number) number;
        return tmp.getNumber() == this.number;
    }


}
