package net.kasara.tokorotenslime.api;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.kasara.tokorotenslime.client.option.ModKeyMappings;
import net.kasara.tokorotenslime.client.render.block.entity.internal.PedestalRenderRegistry;
import net.kasara.tokorotenslime.item.ModCreativeModeTabs;
import net.kasara.tokorotenslime.component.player.AddonCustomDataAccess;
import net.minecraft.client.KeyMapping;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.UnaryOperator;

/**
 * Tokorotenslimeの外部向けAPI
 */
public final class TokorotenSlimeAPI {

    /**
     * TokorotenslimeのMOD_IDを返す
     */
    public static String getModId() {
        return TokorotenSlime.MOD_ID;
    }

    /**
     * 指定したアイテムをTokorotenslimeのクリエイティブタブに追加
     * @param item 追加するItem
     */
    public static void addItemToTab(Item item) {
        ModCreativeModeTabs.addItemList(item);
    }

    /**
     * 指定したアドオンIDに対応するカスタムデータを取得
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @return 指定アドオン用のNbtCompound(存在しない場合は空)
     */
    public static CompoundTag getAddonData(Player player, String addonId) {
        return AddonCustomDataAccess.getAddonRoot(player, addonId);
    }

    /**
     * 指定したアドオンIDに対応するカスタムデータを書き込む
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @param data 書き込むNbtCompound
     */
    public static void writeAddonData(ServerPlayer player, String addonId, CompoundTag data) {
        AddonCustomDataAccess.writeAddonRoot(player, addonId, data);
    }

    /**
     * Deprecated Use {@link TokorotenSlimeClientAPI#getKeyMappingCategory()} instead.
     */
    @Deprecated(since = "1.2.0")
    public static KeyMapping.Category getKeyMappingCategory() {
        return ModKeyMappings.TOKOROTENSLIME_CATEGORY;
    }

    /**
     * Deprecated Use {@link TokorotenSlimeClientAPI#registerPedestalTransformer(Item, UnaryOperator)} instead.
     */
    @Deprecated(since = "1.2.0")
    public static void registerPedestalRenderHandler(Item item, UnaryOperator<ItemStack> transformer) {
        PedestalRenderRegistry.register(item, transformer);
    }

    private TokorotenSlimeAPI() {}
}
