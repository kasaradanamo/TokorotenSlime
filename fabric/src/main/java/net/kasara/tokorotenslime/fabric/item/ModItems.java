package net.kasara.tokorotenslime.fabric.item;

import net.kasara.tokorotenslime.fabric.TokorotenSlime;
import net.kasara.tokorotenslime.item.ModFoods;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {

    // アジフライ(食べ物アイテム)
    public static final Item AJIFURAI =
            registerItem("ajifurai", props -> new Item(props.food(ModFoods.AJIFURAI)));

    // バッター液付き魚(食べ物アイテム)
    public static final Item BETTER_FISH =
            registerItem("better_fish", props -> new Item(props.food(ModFoods.BETTER_FISH)));

    // 単なるアイコン用のアイテム(GUIやシンボルに利用)
    public static final Item SLIME_ICON = registerItem("slime_icon", Item::new);

    private static Item registerItem(String name, Function<Item.Properties, Item> factory) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(TokorotenSlime.MOD_ID, name);
        return Registry.register(
                BuiltInRegistries.ITEM,
                id,
                factory.apply(new Item.Properties())
        );
    }

    /**
     * ModItemsの登録処理を呼び出す
     */
    public static void register() {
        TokorotenSlime.LOGGER.info("Registering Mod Items for " + TokorotenSlime.MOD_ID);
    }
}
