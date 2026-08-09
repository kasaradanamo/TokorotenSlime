package net.kasara.tokorotenslime.forge.component.player;

import net.kasara.tokorotenslime.TokorotenSlimeCommon;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

/**
 * アドオン用カスタムデータを{@link Player#PERSISTED_NBT_TAG}経由で保存・取得する。
 */
public class AddonCustomDataAccess {

    public static CompoundTag getAddonRoot(Player player, String addonId) {
        CompoundTag persisted = player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG);
        CompoundTag tsRoot = persisted.getCompound(TokorotenSlimeCommon.MOD_ID);
        return tsRoot.getCompound(addonId);
    }

    public static void writeAddonRoot(ServerPlayer player, String addonId, CompoundTag addonRoot) {
        CompoundTag persistentData = player.getPersistentData();
        CompoundTag persisted = persistentData.getCompound(Player.PERSISTED_NBT_TAG);
        CompoundTag tsRoot = persisted.getCompound(TokorotenSlimeCommon.MOD_ID);

        // 指定アドオンのデータのみを上書き
        tsRoot.put(addonId, addonRoot);

        // TokorotenSlime配下をpersistedに戻して再設定
        persisted.put(TokorotenSlimeCommon.MOD_ID, tsRoot);
        persistentData.put(Player.PERSISTED_NBT_TAG, persisted);
    }

    private AddonCustomDataAccess() {}
}
