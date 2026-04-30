package net.kasara.tokorotenslime.item;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(TokorotenSlime.MOD_ID);

    // アジフライ(食べ物アイテム)
    public static final DeferredItem<Item> AJIFURAI =
            registerItem("ajifurai", props -> new Item(props.food(ModFoods.AJIFURAI)));

    // バッター液付き魚(食べ物アイテム)
    public static final DeferredItem<Item> BETTER_FISH =
            registerItem("better_fish", props -> new Item(props.food(ModFoods.BETTER_FISH)));

    // 単なるアイコン用のアイテム(GUIやシンボルに利用)
    public static final DeferredItem<Item> SLIME_ICON = registerItem("slime_icon", Item::new);

    private static DeferredItem<Item> registerItem(String name, Function<Item.Properties, Item> factory) {
        return ITEMS.registerItem(name, factory);
    }

    /**
     * ModItemsの登録処理を呼び出す
     */
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);

        // ログ出力
        TokorotenSlime.LOGGER.info("Registering Mod Items for " + TokorotenSlime.MOD_ID);
    }
}
