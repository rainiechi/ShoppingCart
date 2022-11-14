/*
 * TCSS 305 Autumn 2022
 * Assignment 2
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import model.Cart;
import model.Item;
import model.ItemOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit Tests for Cart Class.
 *
 * @author Rainie Chi
 * @version 28 Oct 2022
 */
class CartTest {
    /**
     * Empty cart that will be tested on.
     */
    private static Cart cart;
    /**
     * Non-bulk item that will help test functionalities of Cart.
     */
    private static Item nonBulkItem;
    /**
     * Bulk item that will help test functionalities of Cart.
     */
    private static Item bulkItem;
    /**
     * Item Order that contains bulk item, will be used to test functionalities of Cart.
     */
    private static ItemOrder bulkItemOrder;
    /**
     * Item Order that contains non-bulk item, will be used to test functionalities of Cart.
     */
    private static ItemOrder nonBulkItemOrder;

    @BeforeAll
    static void setUp() {
        nonBulkItem = new Item("bag", new BigDecimal("12.99"));
        bulkItem = new Item("highlighter", new BigDecimal("1.50"), 5, new BigDecimal("4.00"));
        bulkItemOrder = new ItemOrder(bulkItem, 5);
        nonBulkItemOrder = new ItemOrder(nonBulkItem, 5);
    }

    @BeforeEach
    void beforeEachSetUp() {
        cart = new Cart();
    }

    @Test
    void addItemOrderCartSizeShouldIncrease() { //tests add() and getCartSize()
        assertEquals(0, cart.getCartSize());
        cart.add(bulkItemOrder);
        assertEquals(1, cart.getCartSize());
    }

    @Test
    void addPreviousItemOrderOfSameItemWillBeReplaced() { //tests add() and getCartSize()
        final ItemOrder dupe = new ItemOrder(bulkItem, 7);
        cart.add(bulkItemOrder);
        assertEquals(1, cart.getCartSize());
        cart.add(dupe);
        assertEquals(1, cart.getCartSize());
    }

    @Test
    void cannotAddNullItemOrderToCart() { //tests add()
        assertThrows(IllegalArgumentException.class, () -> {
            cart.add(null); });
    }

    @Test
    void setMembershipTest() { //tests setMembership() & calculateTotal()
        cart.setMembership(false);
        cart.add(bulkItemOrder);
        assertEquals(new BigDecimal("7.50"), cart.calculateTotal());
        cart.setMembership(true);
        assertEquals(new BigDecimal("4.00")
                , cart.calculateTotal()); //bulk price applies if membership
    }

    @Test
    void calculateTotalTest() { //tests add() & calculateTotal()
        cart.add(bulkItemOrder);
        cart.add(nonBulkItemOrder);
        assertEquals(new BigDecimal("72.45"), cart.calculateTotal());
    }

    @Test
    void calculateTotalWithDuplicateItemTest() { //tests add() & calculateTotal()
        final ItemOrder bulkDupe = new ItemOrder(bulkItem, 1);
        final ItemOrder nonBulkDupe = new ItemOrder(nonBulkItem, 1);
        cart.add(bulkItemOrder); //$7.5
        cart.add(nonBulkItemOrder); //$64.95
        cart.add(bulkDupe); //$1.5
        cart.add(nonBulkDupe); //$12.99
        assertEquals(new BigDecimal("14.49"), cart.calculateTotal());
    }

    @Test
    void calculateTotalOnEmptyCartReturnsZero() { //tests calculateTotal;
        assertEquals(new BigDecimal("0.00"), cart.calculateTotal());
    }

    @Test
    void clearTest() { //Tests clear() & getCartSize()
        cart.add(bulkItemOrder);
        cart.add(nonBulkItemOrder);
        assertEquals(2, cart.getCartSize());
        cart.clear();
        assertEquals(0, cart.getCartSize());
    }

    @Test
    void testToString() { //tests toString()
        cart.add(nonBulkItemOrder);
        cart.add(bulkItemOrder);
        final String result = "Item: bag, $12.99; Quantity: 5"
                + "\nItem: highlighter, $1.50 (5 for $4.00); Quantity: 5\n";
        assertEquals(result, cart.toString());
    }
}