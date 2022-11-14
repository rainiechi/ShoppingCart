/*
 * TCSS 305 Autumn 2022
 * Assignment 2
 */
package model;
/**
 * ItemOrder class that defines an item order that will be added to the cart.
 * @author Rainie Chi
 * @version 24 OCt 2022
 */
public final class ItemOrder {
    /**
     * myItem is the item of the ItemOrder instance, is declared in the constructor.
     */
    private final Item myItem;
    /**
     * myQuantity is the quantity of myItem of the ItemOrder instance,
     * is declared in the constructor.
     */
    private final int myQuantity;

    /**
     * ItemOrder constructor.
     * @param theItem item of the item order
     * @param theQuantity quantity of item of the item order
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        if (theItem == null) {
            throw new IllegalArgumentException("item passed to"
                    + " ItemOrder constructor was null.");
        }
        if (theQuantity < 0) {
            throw new IllegalArgumentException("quantity passed to ItemOrder"
                   + " constructor was less than 0: " + theQuantity);
        }
        myItem = theItem;
        myQuantity = theQuantity;
    }

    /**
     * Returns the item of the item order.
     * @return myItem of the ItemOrder instance
     */
    public Item getItem() {
        return myItem;
    }

    /**
     * Returns the quantity of the item order.
     * @return myQuantity of the ItemOrder instance
     */
    public int getQuantity() {
        return myQuantity;
    }

    /**
     * Prints out the current state of the item order.
     * @return example: "Item: pencil, $5.00; Quantity: 5"
     */
    @Override
    public String toString() {
        return "Item: " + myItem + "; Quantity: " + myQuantity;
    }
}
