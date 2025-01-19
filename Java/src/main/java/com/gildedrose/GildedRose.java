package com.gildedrose;

import static utils.Constants.AGED_BRIE;
import static utils.Constants.BACKSTAGE_PASSES;
import static utils.Constants.MAX_QUALITY;
import static utils.Constants.MIN_QUALITY;
import static utils.Constants.SULFURAS;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        boolean isAgedBrieItem = AGED_BRIE.equals(item.name);
        boolean isBackStagePass = BACKSTAGE_PASSES.equals(item.name);
        boolean isSulfurasItem = SULFURAS.equals(item.name);
        if (isAgedBrieItem) {
            updateAgedBrie(item);
        } else if (isBackStagePass) {
                updateBackStagePassItem(item);
            } else if (!isSulfurasItem)   {
                    updateOrdinaryItem(item);
                }

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
