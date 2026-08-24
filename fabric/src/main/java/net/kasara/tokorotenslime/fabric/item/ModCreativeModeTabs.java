package net.kasara.tokorotenslime.fabric.item;

import net.kasara.tokorotenslime.fabric.TokorotenSlime;
import net.kasara.tokorotenslime.fabric.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * アイテムグループ(クリエイティブタブ)を管理するクラス
 */
public class ModCreativeModeTabs {

    // アドオンから追加されるアイテムを一時的に保存するリスト
    private static final List<Supplier<Item>> TS_TAB_ITEMS = new ArrayList<>();

    // TokorotenSlime用クリエイティブタブ
    public static final CreativeModeTab TOKOROTENSLIME_TAB =
            Registry.register(
                    BuiltInRegistries.CREATIVE_MODE_TAB,
                    ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(TokorotenSlime.MOD_ID, "tokorotenslime_tab")),
                    CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                            .icon(ModItems.SLIME_ICON::getDefaultInstance)
                            .title(Component.translatable("creativetab.tokorotenslime.tokorotenslime_tab"))
                            .displayItems((itemDisplayParameters, output) -> {

                                // このタブに表示するアイテムを追加
                                output.accept(ModItems.AJIFURAI);
                                output.accept(ModItems.BETTER_FISH);
                                output.accept(ModBlocks.PEDESTAL);

                                // アドオン追加分
                                TS_TAB_ITEMS.forEach(item -> output.accept(item.get()));
                            })
                            .build()
    );

    /**
     * ModCreativeModeTabsの登録処理を呼び出す
     */
    public static void register() {
        TokorotenSlime.LOGGER.info(("Registering Mod Creative Mode Tab for " + TokorotenSlime.MOD_ID));
    }

    /**
     * APIから呼ばれれるメソッド
     */
    public static void addItemList(Supplier<Item> item) {
        TS_TAB_ITEMS.add(item);
    }
}
