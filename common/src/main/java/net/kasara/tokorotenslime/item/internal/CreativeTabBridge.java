package net.kasara.tokorotenslime.item.internal;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * アドオンから追加されたクリエイティブタブ用アイテムを保持する内部クラス
 */
public final class CreativeTabBridge {

    private static final List<Supplier<Item>> items = new ArrayList<>();

    /**
     * タブに表示するアイテムを追加
     */
    public static void addItemToTab(Supplier<Item> item) {
        items.add(item);
    }

    public static List<Supplier<Item>> getItems() {
        return items;
    }

    private CreativeTabBridge() {}
}
