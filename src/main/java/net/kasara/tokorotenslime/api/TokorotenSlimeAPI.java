package net.kasara.tokorotenslime.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kasara.tokorotenslime.TokorotenSlime;
import net.kasara.tokorotenslime.client.option.ModKeyMappings;
import net.kasara.tokorotenslime.client.internal.PedestalRenderRegistry;
import net.kasara.tokorotenslime.item.ModCreativeModeTabs;
import net.kasara.tokorotenslime.storage.AddonCustomDataStorage;
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
     * 指定したアイテムをTokorotenslimeのアイテムグループに追加
     * @param item 追加するItem
     */
    public static void addItemToTab(Item item) {
        ModCreativeModeTabs.addExtraItem(item);
    }

    /**
     * TokorotenSlime用のキーマッピングカテゴリを取得
     * @return KeyMapping.Category
     */
    public static KeyMapping.Category getKeyMappingCategory() {
        return ModKeyMappings.TOKOROTENSLIME_CATEGORY;
    }

    /**
     * 指定したアドオンIDに対応するカスタムデータを取得
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @return 指定アドオン用のNbtCompound(存在しない場合は空)
     */
    public static CompoundTag getAddonData(Player player, String addonId) {
        return AddonCustomDataStorage.getAddonRoot(player, addonId);
    }

    /**
     * 指定したアドオンIDに対応するカスタムデータを書き込む
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @param data 書き込むNbtCompound
     */
    public static void writeAddonData(ServerPlayer player, String addonId, CompoundTag data) {
        AddonCustomDataStorage.writeAddonRoot(player, addonId, data);
    }

    /**
     * 台座上の特定アイテムに対して描画用ItemStackの変換処理を登録する
     *
     * @param item 対象アイテム
     * @param transformer 元のItemStackを受け取り、描画用ItemStackを返す処理
     */
    @Environment(EnvType.CLIENT)
    public static void registerPedestalRenderHandler(Item item, UnaryOperator<ItemStack> transformer) {
        PedestalRenderRegistry.register(item, transformer);
    }

    private TokorotenSlimeAPI() {}
}
