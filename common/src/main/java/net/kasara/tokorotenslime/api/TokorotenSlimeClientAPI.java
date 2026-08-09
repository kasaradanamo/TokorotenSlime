package net.kasara.tokorotenslime.api;

import net.kasara.tokorotenslime.client.option.ModKeyMappings;
import net.kasara.tokorotenslime.client.render.block.entity.internal.PedestalRenderRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.UnaryOperator;

/**
 * Tokorotenslimeの外部向けClientAPI
 */
public final class TokorotenSlimeClientAPI {

    /**
     * TokorotenSlime用のキーマッピングカテゴリを取得
     *
     * @return カテゴリキー(翻訳キー文字列)
     */
    public static String getKeyMappingCategory() {
        return ModKeyMappings.TOKOROTENSLIME_CATEGORY;
    }

    /**
     * 台座上の特定アイテムに対して描画用ItemStackの変換処理を登録する
     *
     * @param item        対象アイテム
     * @param transformer 元のItemStackを受け取り、描画用ItemStackを返す処理
     */
    public static void registerPedestalTransformer(Item item, UnaryOperator<ItemStack> transformer) {
        PedestalRenderRegistry.register(item, transformer);
    }

    private TokorotenSlimeClientAPI() {}
}
