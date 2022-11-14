/*
 * TCSS 305 Autumn 2022
 * Assignment 2
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Cart class that defines the shopping cart function of the bookstore.
 * @author Rainie Chi
 * @version 24 OCt 2022
 */
public class Cart {
    /**
     * Will be set to true if user has membership.
     */
    private boolean myMembership;
    /**
     * Arraylist to store ItemOrders that are in the cart.
     */
    private final List<ItemOrder> myList = new ArrayList<>();

    /**
     * Constructor of the cart.
     */
    public Cart() {

    }

    /**
     * Adds ItemOrders to the cart, if a previous ItemOrder of the same product exists
     * ,it will be replaced.
     * @param theOrder ItemOrder that will be added to the cart
     */
    public void add(final ItemOrder theOrder) {
        if (theOrder == null) {
            throw new IllegalArgumentException("The item order "
                    + "passed to the add method was null.");
        }
        if (myList.isEmpty()) {
            myList.add(theOrder);
        } else {
            for (final ItemOrder io : myList) {
                if (io.getItem().equals(theOrder.getItem())) {
                    myList.remove(io);
                    myList.add(theOrder);
                    break;
                }
            }
            if (!(myList.get(myList.size() - 1).equals(theOrder))) {
                myList.add(theOrder);
            }
        }
    }

    /**
     * Sets membership status of the user.
     * @param theMembership membership status of the user
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }

    /**
     * Calculates the total cost of the entire cart.
     * @return total cost of all itemOrders in the cart
     */
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        if (myMembership) {
            for (final ItemOrder io : myList) {
                int currQuantity = io.getQuantity();
                final Item item = io.getItem();
                if (item.isBulk()) {
                    final int bulkQuantity = item.getBulkQuantity();
                    final int bulks = currQuantity / bulkQuantity;
                    if (bulks >= 1) {
                        total = total.add(item.getBulkPrice().
                                multiply(BigDecimal.valueOf(bulks)));
                        currQuantity -= bulks * bulkQuantity;
                    }
                    if (currQuantity > 0) {
                        total = total.add(item.getPrice().
                                multiply(BigDecimal.valueOf(currQuantity)));
                    }
                } else {
                    total = total.add(item.getPrice().
                            multiply(BigDecimal.valueOf(currQuantity)));
                }
            }
        } else {
            for (final ItemOrder io : myList) {
                final int quantity = io.getQuantity();
                final Item item = io.getItem();
                total = total.add(item.getPrice().multiply(BigDecimal.valueOf(quantity)));
            }
        }
        total = total.setScale(2, RoundingMode.HALF_EVEN);
        return total;
    }

    /**
     * Clears the cart.
     */
    public void clear() {
        myList.clear();
    }

    /**
     * Return numbers of item orders in the cart.
     * @return myList.size();
     */
    public int getCartSize() {
        return myList.size();
    }

    /**
     * Prints out the current state of the cart.
     * @return example: "Item: pencil, $5.00; Quantity: 5
     *                   Item: notebook, $15.00; Quantity: 6
     *                   Item: paper, $3.00 ($1.00 for 6); Quantity: 8"
     */
    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();
        for (final ItemOrder io : myList) {
            str.append(io.toString()).append("\n");
        }
        return String.valueOf(str);
    }
}
