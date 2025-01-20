package com.gildedrose;

import static utils.Constants.AGED_BRIE;
import static utils.Constants.BACKSTAGE_PASSES;
import static utils.Constants.CONJURED;
import static utils.Constants.MAX_QUALITY;
import static utils.Constants.MIN_QUALITY;
import static utils.Constants.SULFURAS;

class GildedRose {
    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
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
                    updateAgedBrie(item);
                    break;
                case BACKSTAGE_PASSES:
                    updateBackStagePassItem(item);
                    break;
                case SULFURAS:
                    break;
                case CONJURED:
                    updateConjuredItem(item);
                    break;
                default:
                    updateOrdinaryItem(item);
                    break;
        }

    }

    private void updateConjuredItem(Item item) {
        if (item.sellIn > 0) {
            item.quality = Math.max(item.quality - 2, MIN_QUALITY);
        } else {
            item.quality = Math.max(item.quality - 4, MIN_QUALITY);
        }
        item.sellIn = item.sellIn - 1;
    }

    private void updateOrdinaryItem(Item item) {
        if (item.sellIn > 0) {
            item.quality = Math.max(item.quality - 1, MIN_QUALITY);
        } else {
            item.quality = Math.max(item.quality - 2, MIN_QUALITY);
        }
        item.sellIn = item.sellIn - 1;
    }

    private void updateBackStagePassItem(Item item) {
        item.quality = Math.min(item.quality + 1, MAX_QUALITY);
        if (item.sellIn < 11) {
            item.quality = Math.min(item.quality + 1, MAX_QUALITY);
        }
        if (item.sellIn < 6) {
            item.quality = Math.min(item.quality + 1, MAX_QUALITY);
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = MIN_QUALITY;
        }
    }

    private void updateAgedBrie(Item item) {
        item.quality = Math.min(item.quality + 1, MAX_QUALITY);

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = Math.min(item.quality + 1, MAX_QUALITY);
        }
    }

}
