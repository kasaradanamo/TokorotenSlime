package net.kasara.tokorotenslime.fabric.component.player;

import net.kasara.tokorotenslime.TokorotenSlimeCommon;
import net.kasara.tokorotenslime.fabric.duck.PersistentDataHolder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

/**
 * アドオン用カスタムデータを{@link PersistentDataHolder}の永続NBT領域経由で保存・取得する。
 */
public class AddonCustomDataAccess {

    public static CompoundTag getAddonRoot(Player player, String addonId) {
        CompoundTag persisted = ((PersistentDataHolder) player).tokorotenslime$getPersistentData();
        CompoundTag tsRoot = persisted.getCompound(TokorotenSlimeCommon.MOD_ID);
        return tsRoot.getCompound(addonId);
    }

    public static void writeAddonRoot(ServerPlayer player, String addonId, CompoundTag addonRoot) {
        PersistentDataHolder holder = (PersistentDataHolder) player;
        CompoundTag persisted = holder.tokorotenslime$getPersistentData();
        CompoundTag tsRoot = persisted.getCompound(TokorotenSlimeCommon.MOD_ID);

        // 指定アドオンのデータのみを上書き
        tsRoot.put(addonId, addonRoot);

        persisted.put(TokorotenSlimeCommon.MOD_ID, tsRoot);
        holder.tokorotenslime$setPersistentData(persisted);
    }

    private AddonCustomDataAccess() {}
}
