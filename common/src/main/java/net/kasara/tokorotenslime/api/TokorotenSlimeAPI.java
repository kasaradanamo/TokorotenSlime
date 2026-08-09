package net.kasara.tokorotenslime.api;

import net.kasara.tokorotenslime.TokorotenSlimeCommon;
import net.kasara.tokorotenslime.component.player.internal.AddonDataAccessBridge;
import net.kasara.tokorotenslime.item.internal.CreativeTabBridge;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

/**
 * Tokorotenslimeの外部向けAPI
 */
public final class TokorotenSlimeAPI {

    /**
     * TokorotenslimeのMOD_IDを返す
     */
    public static String getModId() {
        return TokorotenSlimeCommon.MOD_ID;
    }

    /**
     * 指定したアドオンIDに対応するカスタムデータを取得
     *
     * @param player  対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @return 指定アドオン用のNbtCompound(存在しない場合は空)
     */
    public static CompoundTag getAddonData(Player player, String addonId) {
        return AddonDataAccessBridge.GET_ADDON_DATA.apply(player, addonId);
    }

    /**
     * 指定したアドオンIDに対応するカスタムデータを書き込む
     *
     * @param player  対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @param data    書き込むNbtCompound
     */
    public static void writeAddonData(ServerPlayer player, String addonId, CompoundTag data) {
        AddonDataAccessBridge.WRITE_ADDON_DATA.write(player, addonId, data);
    }

    /**
     * 指定したアイテムをTokorotenslimeのクリエイティブタブに追加
     *
     * @param item 追加するItemを返すSupplier
     */
    public static void addItemToTab(Supplier<Item> item) {
        CreativeTabBridge.addItemToTab(item);
    }

    private TokorotenSlimeAPI() {}
}
