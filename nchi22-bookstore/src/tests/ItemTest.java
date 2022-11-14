/*
 * TCSS 305 Autumn 2022
 * Assignment 2
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Unit Tests for Item Class.
 *
 * @author Rainie Chi
 * @version 28 Oct 2022
 */
class ItemTest {
    /**
     * Non-bulk item that will help test functionalities of Items.
     */
    private static Item nonBulkItem;
    /**
     * Bulk item that will help test functionalities of Items.
     */
    private static Item bulkItem;

    @BeforeAll
    static void setUp() {
        nonBulkItem = new Item("pencil", new BigDecimal("2.99"));
        bulkItem = new Item("paper", new BigDecimal("2.00"), 5, new BigDecimal("5.00"));
    }

    @Test
    void itemNameCannotBeNull() { //tests constructor
        assertThrows(IllegalArgumentException.class, () -> {
            final Item nullItemName = new Item(null, new BigDecimal("3.00")); });
    }

    @Test
    void itemPriceCannotBeNull() { //tests constructor
        assertThrows(IllegalArgumentException.class, () -> {
            final Item nullItemPrice = new Item("apple", null); });
    }

    @Test
    void itemPriceCannotBeNegative() { //tests constructor
        assertThrows(IllegalArgumentException.class, () -> {
            final Item negativeItemPrice = new Item("banana", new BigDecimal("-5.00")); });
    }

    @Test
    void bulkPriceCannotBeNull() { //tests constructor
        assertThrows(IllegalArgumentException.class, () -> {
            final Item nullBulkPrice = new Item("grape", new BigDecimal("2.99")
                    , 5, null); });
    }

    @Test
    void bulkPriceCannotBeNegative() { //tests constructor
        assertThrows(IllegalArgumentException.class, () -> {
            final Item negativeBulkPrice = new Item("cake", new BigDecimal("3.00")
                    , 5, new BigDecimal("-7.00")); });
    }

    @Test
    void bulkQuantityCannotBeNegative() { //tests constructor
        assertThrows(IllegalArgumentException.class, () -> {
            final Item negativeBulkQuantity = new Item("bread", new BigDecimal("3.00")
                    , -5, new BigDecimal("-7.00")); });
    }

    @Test
    void getPriceTest() { //tests getPrice()
        assertEquals(new BigDecimal("2.99"), nonBulkItem.getPrice());
    }

    @Test
    void nonBulkItemsGetBulkQuantityShouldReturn0() { //tests getBulkQuantity()
        assertEquals(0, nonBulkItem.getBulkQuantity());
    }

    @Test
     void getBulkQuantityTest() { //tests getBulkQuantity()
        assertEquals(5, bulkItem.getBulkQuantity());
    }

    @Test
    void nonBulkItemsGetBulkPriceShouldReturnNull() { //tests getBulkPrice()
        assertNull(nonBulkItem.getBulkPrice());
    }

    @Test
    void bulkItemsGetBulkPriceTest() { //tests getBulkPrice()
        assertEquals(new BigDecimal("5.00"), bulkItem.getBulkPrice());
    }

    @Test
    void bulkItemIsBulkShouldReturnTrue() { //tests isBulk()
        assertTrue(bulkItem.isBulk());
    }

    @Test
    void nonBulkItemIsBulkShouldReturnFalse() { //tests isBulk()
        assertFalse(nonBulkItem.isBulk());
    }

    @Test
    void bulkItemToStringTest() { //tests toString()
        assertEquals("paper, $2.00 (5 for $5.00)", bulkItem.toString());
    }

    @Test
    void nonBulkItemToStringTest() { //tests toString()
        assertEquals("pencil, $2.99", nonBulkItem.toString());
    }

    @Test
    void equalsNullTest() { //tests equals()
        assertNotEquals(bulkItem, null);
    }

    @Test
    void equalsOtherObjectTest() { //tests equals()
        final String str = new String("I am a String object");
        assertNotEquals(bulkItem, str);
    }

    @Test
    void bulkItemsWithSameDataAreEqual() { //tests equals()
        final Item bulkItem2 = new Item("paper", new BigDecimal("2.00")
                , 5, new BigDecimal("5.00"));
        assertEquals(bulkItem, bulkItem2);
    }

    @Test
    void bulkItemsWithDifferentNamesAreNotEqual() { //tests equals()
        final Item bulkItem3 = new Item("paper1", new BigDecimal("2.00")
                , 5, new BigDecimal("5.00"));
        assertNotEquals(bulkItem, bulkItem3);
    }
    @Test
    void bulkItemsWithDifferentPricesAreNotEqual() { //tests equals()
        final Item bulkItem4 = new Item("paper", new BigDecimal("3.00")
                , 5, new BigDecimal("5.00"));
        assertNotEquals(bulkItem, bulkItem4);
    }

    @Test
    void nonBulkItemsWithSameDataAreEqual() { //tests equals()
        final Item nonBulkItem2 = new Item("pencil", new BigDecimal("2.99"));
        assertEquals(nonBulkItem, nonBulkItem2);
    }

    @Test
    void nonBulkItemsWithDifferentNamesAreNotEqual() { //tests equals()
        final Item nonBulkItem3 = new Item("pen", new BigDecimal("2.99"));
        assertNotEquals(nonBulkItem, nonBulkItem3);
    }

    @Test
    void nonBulkItemsWithDifferentPricesAreNotEqual() { //tests equals()
        final Item nonBulkItem4 = new Item("pencil", new BigDecimal("30.99"));
        assertNotEquals(nonBulkItem, nonBulkItem4);
    }

    @Test
    void twoEqualBulkItemsShareTheSameHashCode() { //test hashCode()
        final Item bulkItem2 = new Item("paper", new BigDecimal("2.00")
                , 5, new BigDecimal("5.00"));
        assertEquals(bulkItem.hashCode(), bulkItem2.hashCode());
    }

    @Test
    void twoEqualNonBulkItemsShareTheSameHashCode() { //test hashCode()
        final Item nonBulkItem2 = new Item("pencil", new BigDecimal("2.99"));
        assertEquals(nonBulkItem.hashCode(), nonBulkItem2.hashCode());
    }
}