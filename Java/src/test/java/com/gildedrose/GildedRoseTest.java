package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Constants.ORDINARY_ITEM;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item(ORDINARY_ITEM, 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(ORDINARY_ITEM, app.items[0].name);
    }

    /**
     * Ordinary item
     * Expiration has not passed
     * quality decrease by 1
     * sellIn decrease by 1
     */
    @Test
    void qualityAndSellInForOrdinaryItem() {
        Item[] items = new Item[] { new Item(ORDINARY_ITEM, 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(9, app.items[0].quality);
    }

    /**
     * Ordinary item
     * Expiration has passed
     * quality decrease by 2
     * sellIn decrease by 1
     */
    @Test
    void qualityAndSellInForOrdinaryItemWithPassedExpiration() {
        Item[] items = new Item[] { new Item(ORDINARY_ITEM, 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    /**
     * Ordinary item
     * Expiration has passed
     * quality decrease by 2
     * sellIn decrease by 1
     * quality can not be less than 0
     */
    @Test
    void qualityAndSellInForOrdinaryItemNeverNegative() {
        Item[] items = new Item[] { new Item(ORDINARY_ITEM, 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

}
