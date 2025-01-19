package com.gildedrose;

import static utils.Constants.AGED_BRIE;
import static utils.Constants.BACKSTAGE_PASSES;
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
        if (isAgedBrieItem) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (BACKSTAGE_PASSES.equals(item.name)) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }

            if (!SULFURAS.equals(item.name)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        } else {
            if (!BACKSTAGE_PASSES.equals(item.name)) {
                if (item.quality > 0) {
                    if (!SULFURAS.equals(item.name)) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (BACKSTAGE_PASSES.equals(item.name)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!SULFURAS.equals(item.name)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!BACKSTAGE_PASSES.equals(item.name)) {
                    if (item.quality > 0) {
                        if (!SULFURAS.equals(item.name)) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = 0;
                }
            }
        }

    }

}
