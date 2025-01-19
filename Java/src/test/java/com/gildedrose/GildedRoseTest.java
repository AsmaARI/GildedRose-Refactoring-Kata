package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Constants.AGED_BRIE;
import static utils.Constants.BACKSTAGE_PASSES;
import static utils.Constants.ORDINARY_ITEM;
import static utils.Constants.SULFURAS;

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

    /**
     * Sulfuras item
     * quality do not change is always 80
     * sellIn do not change
     */

    @Test
    void qualityAndSellInForSulfurasItem() {
        Item[] items = new Item[] { new Item(SULFURAS, 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    /**
     * Backstage passes item
     * Expiration more than 10 days
     * quality increase by 1
     * sellIn decrease by 1
     */
    @Test
    void qualityAndSellInForBackstageItem() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 13, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].sellIn);
        assertEquals(16, app.items[0].quality);
    }

    /**
     * Backstage passes item
     * Expiration 10 days or less
     * quality increase by 2
     * sellIn decrease by 1
     */
    @Test
    void qualityAndSellInForBackstageItemWithLessThan_10_Days() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 9, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].sellIn);
        assertEquals(17, app.items[0].quality);
    }

    /**
     * Backstage passes item
     * Expiration 5 days or less
     * quality increase by 3
     * sellIn decrease by 1
     */
    @Test
    void qualityAndSellInForBackstageItemWithLessThan_5_Days() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 4, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);
    }

    /**
     * Backstage passes item
     * Expiration has passed
     * quality drop to 0
     * sellIn decrease by 1
     */
    @Test
    void qualityAndSellInForBackstageItemAfterConcert() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, -1, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

}
