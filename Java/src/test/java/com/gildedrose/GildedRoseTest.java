package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Constants.AGED_BRIE;
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

    /**
     * Aged Brie item
     * Expiration has not passed
     * quality increase by 1
     * sellIn decrease by 1
     */
    @Test
    void qualityAndSellInForAgedBrieItem() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(11, app.items[0].quality);
    }

    /**
     * Aged Brie item
     * Expiration has passed
     * quality increase by 2
     * sellIn decrease by 1
     */
    @Test
    void qualityAndSellInForAgedBrieItemWithPassedExpiration() {
        Item[] items = new Item[] { new Item(AGED_BRIE, -2, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-3, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    /**
     * Aged Brie item
     * Expiration has not passed
     * quality is max
     * sellIn decrease by 1
     */
    @Test
    void qualityAndSellInForAgedBrieItemWithMaxQuality() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }
}
