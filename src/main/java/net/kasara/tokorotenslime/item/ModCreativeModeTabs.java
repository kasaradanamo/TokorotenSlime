package net.kasara.tokorotenslime.item;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.kasara.tokorotenslime.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * アイテムグループ(クリエイティブタブ)を管理するクラス
 */
public class ModCreativeModeTabs {

    // アドオンから追加されるアイテムを一時的に保存するリスト
    private static final List<Item> EXTRA_ITEMS = new ArrayList<>();

    // TokorotenSlime用クリエイティブタブ
    public static final CreativeModeTab TOKOROTENSLIME = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(TokorotenSlime.MOD_ID, "tokorotenslime_group")),
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .icon(() -> new ItemStack(ModItems.SLIME_ICON))
                    .title(Component.translatable("itemgroup.tokorotenslime.tokorotenslime_group"))
                    .displayItems((parameters, entries) -> {

                        // このタブに表示するアイテム一覧を追加
                        entries.accept(ModItems.AJIFURAI);
                        entries.accept(ModItems.BETTER_FISH);
                        entries.accept(ModBlocks.PEDESTAL);

                        // アドオン追加分
                        EXTRA_ITEMS.forEach(entries::accept);
                    })
                    .build()
    );

    /**
     * ModItemGroupsの登録処理を呼び出す
     */
    public static void registerItemGroups() {
        TokorotenSlime.LOGGER.info(("Registering Item Groups for " + TokorotenSlime.MOD_ID));
    }

    /**
     * APIから呼ばれれるメソッド
     */
    public static void addExtraItem(Item item) {
        EXTRA_ITEMS.add(item);
    }
}