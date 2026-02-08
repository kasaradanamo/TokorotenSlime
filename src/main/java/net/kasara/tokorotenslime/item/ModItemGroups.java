package net.kasara.tokorotenslime.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.kasara.tokorotenslime.TokorotenSlime;
import net.kasara.tokorotenslime.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * アイテムグループ(クリエイティブタブ)を管理するクラス
 */
public class ModItemGroups {

    // アドオンから追加されるアイテムを一時的に保存するリスト
    private static final List<Item> EXTRA_ITEMS = new ArrayList<>();

    // TokorotenSlime用クリエイティブタブ
    public static final ItemGroup TOKOROTENSLIME_Group = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TokorotenSlime.MOD_ID, "tokorotenslime_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SLIME_ICON))
                    .displayName(Text.translatable("itemgroup.tokorotenslime.tokorotenslime_group"))
                    .entries((displayContext, entries) -> {

                        // このタブに表示するアイテム一覧を追加
                        entries.add(ModItems.AJIFURAI);
                        entries.add(ModItems.BETTER_FISH);
                        entries.add(ModBlocks.PEDESTAL);

                        // アドオン追加分
                        EXTRA_ITEMS.forEach(entries::add);
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
