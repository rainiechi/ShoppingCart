/*
 * TCSS 305 Autumn 2022
 * Assignment 2
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



/**
 * Unit Tests for Item Order Class.
 *
 * @author Rainie Chi
 * @version 28 Oct 2022
 */
class ItemOrderTest {
    /**
     * Non-bulk item that will help test ItemOrder.
     */
    private static Item nonBulkItem;
    /**
     * Bulk item that will help test ItemOrder.
     */
    private static Item bulkItem;
    /**
     * Item Order that contains bulk item, will be used to test different functionalities.
     */
    private static ItemOrder bulkItemOrder;
    /**
     * Item Order that contains non bulk item, will be used to test different functionalities.
     */
    private static ItemOrder nonBulkItemOrder;

    @BeforeAll
    static void setUp() {
        nonBulkItem = new Item("bag", new BigDecimal("12.99"));
        bulkItem = new Item("highlighter", new BigDecimal("1.50"), 5, new BigDecimal("4.00"));
        bulkItemOrder = new ItemOrder(bulkItem, 6);
        nonBulkItemOrder = new ItemOrder(nonBulkItem, 5);
    }

    @Test
    void itemOrderQuantityCannotBeNegative() { //tests constructor
        assertThrows(IllegalArgumentException.class, () -> {
            final ItemOrder badItemOrder = new ItemOrder(bulkItem, -6); });
    }

    @Test
    void itemCannotBeNull() { //tests constructor
        assertThrows(IllegalArgumentException.class, () -> {
            final ItemOrder badItemOrder = new ItemOrder(null, 6); });
    }

    @Test
    void getItemTest() { //tests getItem()
        assertEquals(nonBulkItem, nonBulkItemOrder.getItem());
        assertEquals(bulkItem, bulkItemOrder.getItem());
    }

    @Test
    void getQuantityTest() { //tests getQuantity()
        assertEquals(6, bulkItemOrder.getQuantity());
    }
    
    @Test
    void nonBulkItemOrderToStringTest() { //tests toString()
        assertEquals("Item: bag, $12.99; Quantity: 5", nonBulkItemOrder.toString());
    }

    @Test
    void bulkItemOrderToStringTest() { //tests toString()
        assertEquals("Item: highlighter, "
                + "$1.50 (5 for $4.00); Quantity: 6", bulkItemOrder.toString());
    }
}