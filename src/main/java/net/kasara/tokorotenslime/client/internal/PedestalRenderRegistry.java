package net.kasara.tokorotenslime.client.internal;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * Pedestal描画用ItemStack変換処理の内部レジストリ
 * APIからのみ登録されて、Rendererからのみ参照される
 */
@Environment(EnvType.CLIENT)
public class PedestalRenderRegistry {

    // Item → 描画用 ItemStack へ変換処理を保持するマップ
    private static final Map<Item, UnaryOperator<ItemStack>> TRANSFORMERS = new HashMap<>();

    /**
     * 指定したItemに対する描画用ItemStack変換処理を登録
     *
     * @param item 返還対象となるアイテム
     * @param transformer 元のItemStackを受け取り、描画用に変換したItemStackを返す処理
     */
    public static void register(Item item, UnaryOperator<ItemStack> transformer) {
        TRANSFORMERS.put(item, transformer);
    }

    /**
     * 登録されている変換処理を適用し、描画用の ItemStack を取得。
     * 対応する変換処理が存在しない場合は、元の ItemStack をそのまま返す
     *
     * @param original 元となるItemStack
     * @return 変換後のItemStack、もしくは元のItemStack
     */
    public static ItemStack apply(ItemStack original) {
        var transformer = TRANSFORMERS.get(original.getItem());
        if (transformer == null) return original;
        return transformer.apply(original);
    }

    private PedestalRenderRegistry() {}
}