package net.kasara.tokorotenslime.item.internal;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class CreativeTabBridge {

    private static Consumer<Supplier<Item>> handler;
    private static final List<Supplier<Item>> pending = new ArrayList<>();

    public static void setHandler(Consumer<Supplier<Item>> newHandler) {
        handler = newHandler;
        pending.forEach(newHandler);
        pending.clear();
    }

    public static void addItemToTab(Supplier<Item> item) {
        if (handler != null) {
            handler.accept(item);
        } else {
            pending.add(item);
        }
    }

    private CreativeTabBridge() {}
}
