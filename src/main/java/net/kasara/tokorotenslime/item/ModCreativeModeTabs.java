package net.kasara.tokorotenslime.item;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.kasara.tokorotenslime.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

/**
 * アイテムグループ(クリエイティブタブ)を管理するクラス
 */
public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TokorotenSlime.MOD_ID);

    // アドオンから追加されるアイテムを一時的に保存するリスト
    private static final List<DeferredItem<Item>> TS_TAB_ITEMS = new ArrayList<>();

    // TokorotenSlime用クリエイティブタブ
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOKOROTENSLIME_TAB =
            CREATIVE_MODE_TABS.register(
                    "tokorotenslime_tab",
                    () -> CreativeModeTab.builder()
                            .icon(ModItems.SLIME_ICON.get()::getDefaultInstance)
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
        TokorotenSlime.LOGGER.info(("Registering Mod Creative Mode Tab for " + TokorotenSlime.MOD_ID));
    }

    /**
     * APIから呼ばれれるメソッド
     */
    public static void addItemList(DeferredItem<Item> item) {
        TS_TAB_ITEMS.add(item);
    }

    /**
     * アドオン側がAPI経由で追加したアイテムをクリエイティブタブに追加
     */
    public static void addAddonItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(TOKOROTENSLIME_TAB.getKey())) {
            for (DeferredItem<Item> item : TS_TAB_ITEMS) {
                event.accept(item.get());
            }
        }
    }
}
