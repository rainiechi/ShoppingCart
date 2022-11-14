/*
 * TCSS 305 Autumn 2022
 * Assignment 2
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Item class that defines an item.
 * @author Rainie Chi
 * @version 24 OCt 2022
 */
public final class Item {
    /**
     * Number formatter that will format the price outputs.
     */
    private static final NumberFormat NUMBER_FORMAT
            = NumberFormat.getCurrencyInstance(Locale.US);
    /**
     * Name of the item that will be declared in the constructor.
     */
    private final String myName;
    /**
     * Price of the item that will be declared in the constructor.
     */
    private final BigDecimal myPrice;
    /**
     * Optional: bulk price of the item that will be declared in the constructor.
     */
    private BigDecimal myBulkPrice;
    /**
     * Optional: bulk quantity of the item that will be declared in the constructor.
     */
    private int myBulkQuantity;

    /**
     * Item constructor for non-bulk items.
     *
     * @param theName  name of the item
     * @param thePrice price of the item
     */
    public Item(final String theName, final BigDecimal thePrice) {
        if (theName == null) {
            throw new IllegalArgumentException("name passed to Item constructor was null.");
        }
        if (thePrice == null || thePrice.doubleValue() < 0) {
            throw new IllegalArgumentException("price passed to "
                    + "item constructor was not valid: " + thePrice);
        }
        myName = theName;
        myPrice = thePrice;
    }

    /**
     * Item constructor for bulk items.
     *
     * @param theName         name of the item
     * @param thePrice        price of the item
     * @param theBulkQuantity bulk quantity of the item
     * @param theBulkPrice    bulk price of the item
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        if (theName == null) {
            throw new IllegalArgumentException("the name passed "
                    + "to Item constructor was null.");
        }
        if (thePrice == null) {
            throw new IllegalArgumentException("the price "
                    + "passed to Item constructor was null.");
        }
        if (theBulkQuantity < 0) {
            throw new IllegalArgumentException("bulk quantity passed to Item "
                    + "constructor was less than 0: " + theBulkQuantity);
        }
        if (theBulkPrice == null || theBulkPrice.doubleValue() < 0) {
            throw new IllegalArgumentException("bulk price passed to Item "
                    + "constructor was not valid: " + theBulkPrice);
        }
        myName = theName;
        myPrice = thePrice;
        myBulkPrice = theBulkPrice;
        myBulkQuantity = theBulkQuantity;
    }

    /**
     * Returns the price of the item.
     *
     * @return myPrice of the item
     */
    public BigDecimal getPrice() {
        return myPrice;
    }

    /**
     * Returns the bulk quantity of the item.
     *
     * @return myBulkQuantity of the item
     */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }

    /**
     * Returns the bulk price of the item.
     *
     * @return myBulkPrice of the item
     */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }

    /**
     * Returns whether the item is a bulk item.
     *
     * @return true if item is bulk; false if item is not bulk
     */
    public boolean isBulk() {
        return myBulkPrice != null;
    }

    /**
     * Prints out the current state of the item.
     *
     * @return examples:
     * non-bulk: "pencil, $15.00"
     * bulk: "paper, $3.00 ($1.00 for 6)"
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder(myName);
        stringBuilder.append(", ");
        stringBuilder.append(NUMBER_FORMAT.format(myPrice));
        if (myBulkQuantity > 0) {
            stringBuilder.append(" (");
            stringBuilder.append(myBulkQuantity);
            stringBuilder.append(" for ");
            stringBuilder.append(NUMBER_FORMAT.format(myBulkPrice));
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

    /**
     * Overriding equals method.
     * @param theOther the other item to compare if instance item is equal to
     * @return true if items are equal, false if not
     */
    @Override
    public boolean equals(final Object theOther) {
        if (theOther == null || !getClass().equals(theOther.getClass())) {
            return false;
        }
        final Item otherItem = (Item) theOther;
        return myName.equalsIgnoreCase(otherItem.myName)
                && myPrice.equals(otherItem.getPrice());
    }

    /**
     * Overriding hashCode method. Two equal items return the same hashcode.
     * @return int value of hashcode for the item
     */
    @Override
    public int hashCode() {
        return Objects.hash(myPrice, myName);
    }

}