package net.kasara.tokorotenslime.forge.item;

import net.kasara.tokorotenslime.item.ModFoods;
import net.kasara.tokorotenslime.forge.TokorotenSlime;
import net.kasara.tokorotenslime.forge.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TokorotenSlime.MOD_ID);

    // アジフライ(食べ物アイテム)
    public static final RegistryObject<Item> AJIFURAI =
            ITEMS.register("ajifurai", () -> new Item(new Item.Properties().food(ModFoods.AJIFURAI)));

    // バッター液付き魚(食べ物アイテム)
    public static final RegistryObject<Item> BETTER_FISH =
            ITEMS.register("better_fish", () -> new Item(new Item.Properties().food(ModFoods.BETTER_FISH)));

    // 単なるアイコン用のアイテム(GUIやシンボルに利用)
    public static final RegistryObject<Item> SLIME_ICON =
            ITEMS.register("slime_icon", () -> new Item(new Item.Properties()));

    // PedestalBlock用のBlockItem
    public static final RegistryObject<Item> PEDESTAL =
            ITEMS.register("pedestal", () -> new BlockItem(ModBlocks.PEDESTAL.get(), new Item.Properties()));

    /**
     * ModItemsの登録処理を呼び出す
     */
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);

        // ログ出力
        TokorotenSlime.LOGGER.info("Registering Mod Items for " + TokorotenSlime.MOD_ID);
    }
}
