package net.kasara.tokorotenslime.forge.item;

import net.kasara.tokorotenslime.forge.TokorotenSlime;
import net.kasara.tokorotenslime.forge.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * アイテムグループ(クリエイティブタブ)を管理するクラス
 */
public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TokorotenSlime.MOD_ID);

    // アドオンから追加されるアイテムを一時的に保存するリスト
    private static final List<Supplier<Item>> TS_TAB_ITEMS = new ArrayList<>();

    // TokorotenSlime用クリエイティブタブ
    public static final RegistryObject<CreativeModeTab> TOKOROTENSLIME_TAB =
            CREATIVE_MODE_TABS.register(
                    "tokorotenslime_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> ModItems.SLIME_ICON.get().getDefaultInstance())
                            .title(Component.translatable("creativetab.tokorotenslime.tokorotenslime_tab"))
                            .displayItems((itemDisplayParameters, output) -> {

                                // このタブに表示するアイテムを追加
                                output.accept(ModItems.AJIFURAI.get());
                                output.accept(ModItems.BETTER_FISH.get());
                                output.accept(ModBlocks.PEDESTAL.get());
                            })
                            .build()
            );

    /**
     * ModCreativeModeTabsの登録処理を呼び出す
     */
    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);

        // ログ出力
        TokorotenSlime.LOGGER.info("Registering Mod Creative Mode Tab for " + TokorotenSlime.MOD_ID);
    }

    /**
     * APIから呼ばれるメソッド
     */
    public static void addItemList(Supplier<Item> item) {
        TS_TAB_ITEMS.add(item);
    }

    /**
     * アドオン側がAPI経由で追加したアイテムをクリエイティブタブに追加
     */
    public static void addAddonItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(TOKOROTENSLIME_TAB.getKey())) {
            for (Supplier<Item> item : TS_TAB_ITEMS) {
                event.accept(item.get());
            }
        }
    }
}
