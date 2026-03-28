package net.kasara.tokorotenslime.item;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    // アジフライ(食べ物アイテム)
    public static final Item AJIFURAI = registerItem("ajifurai", setting -> new Item(setting.food(ModFoods.AJIFURAI)));

    // バッター液付き魚(食べ物アイテム)
    public static final Item BETTER_FISH = registerItem("better_fish", setting -> new Item(setting.food(ModFoods.BETTER_FISH)));

    // 単なるアイコン用のアイテム(GUIやシンボルに利用)
    public static final Item SLIME_ICON = registerItem("slime_icon", Item::new);

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(TokorotenSlime.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TokorotenSlime.MOD_ID, name)))));
    }

    /**
     * ModItemsの登録処理を呼び出す
     */
    public static void registerModItems() {
        TokorotenSlime.LOGGER.info("Registering Mod Items for " + TokorotenSlime.MOD_ID);
    }
}