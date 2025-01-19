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

}
