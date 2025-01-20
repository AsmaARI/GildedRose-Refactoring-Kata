package com.gildedrose;

import java.util.Arrays;

import static utils.Constants.AGED_BRIE;
import static utils.Constants.BACKSTAGE_PASSES;
import static utils.Constants.CONJURED;
import static utils.Constants.MAX_QUALITY;
import static utils.Constants.MIN_QUALITY;
import static utils.Constants.SULFURAS;

class GildedRose {

    private  Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return this.items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        String itemName = item.name;
        switch (itemName) {
            case AGED_BRIE:
                updateItemQuality(item, 1, MAX_QUALITY, true);
                break;
            case BACKSTAGE_PASSES:
                updateBackstagePass(item);
                break;
            case SULFURAS:
                break;
            case CONJURED:
                updateItemQuality(item, -2, MIN_QUALITY, false);
                break;
            default:
                updateItemQuality(item, -1, MIN_QUALITY, false);
                break;
        }
    }

    /**
     * Generic method to update the quality of an item.
     *
     * @param item The item to update.
     * @param qualityChange The quality change (positive or negative).
     * @param qualityLimit The quality limit (MIN_QUALITY or MAX_QUALITY).
     * @param increases If true, quality increases over time. If it is false, it decreases.
     */
    private void updateItemQuality(Item item, int qualityChange, int qualityLimit, boolean increases) {
        if (item.sellIn > 0) {
            item.quality = increases
                ? Math.min(item.quality + qualityChange, qualityLimit)
                : Math.max(item.quality + qualityChange, qualityLimit);
        } else {
            item.quality = increases
                ? Math.min(item.quality + qualityChange * 2, qualityLimit)
                : Math.max(item.quality + qualityChange * 2, qualityLimit);
        }

        item.sellIn = item.sellIn - 1;
    }


    /**
     * Specific method for Backstage Passes.
     * Quality increases depending on sellIn.
     */
    private void updateBackstagePass(Item item) {
        if (item.sellIn <= 0) {
            item.quality = MIN_QUALITY;
        } else if (item.sellIn <6) {
            item.quality = Math.min(item.quality + 3, MAX_QUALITY);
        } else if (item.sellIn <11) {
            item.quality = Math.min(item.quality + 2, MAX_QUALITY);
        } else {
            item.quality = Math.min(item.quality + 1, MAX_QUALITY);
        }

        item.sellIn = item.sellIn - 1;
    }
}
