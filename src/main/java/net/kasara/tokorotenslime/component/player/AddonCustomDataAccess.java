package net.kasara.tokorotenslime.component.player;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.CustomData;

/**
 * TokorotenSlime配下にアドオン用のカスタムデータを保存・取得するための内部ユーティリティ
 */
public class AddonCustomDataAccess {

    /**
     * 指定したアドオンIDに対応するNBTルートを取得
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @return アドオン用NbtCompound(存在しない場合は空)
     */
    public static CompoundTag getAddonRoot(Player player, String addonId) {
        CustomData comp = player.get(DataComponents.CUSTOM_DATA);
        CompoundTag base = comp != null ? comp.copyTag() : new CompoundTag();
        CompoundTag tsRoot = base.getCompound(TokorotenSlime.MOD_ID).orElse(new CompoundTag());
        return tsRoot.getCompound(addonId).orElse(new CompoundTag());
    }

    /**
     * 指定したアドオンIDに対応するNBTルートを書き込む
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @param addonRoot 書き込むアドオン用NbtCompound
     */
    public static void writeAddonRoot(ServerPlayer player, String addonId, CompoundTag addonRoot) {
        CustomData comp = player.get(DataComponents.CUSTOM_DATA);
        CompoundTag base = comp != null ? comp.copyTag() : new CompoundTag();
        CompoundTag tsRoot = base.getCompound(TokorotenSlime.MOD_ID).orElse(new CompoundTag());

        // 指定アドオンのデータのみを上書き
        tsRoot.put(addonId, addonRoot);

        // TokorotenSlime配下をbaseに戻して再設定
        base.put(TokorotenSlime.MOD_ID, tsRoot);
        player.setComponent(DataComponents.CUSTOM_DATA, CustomData.of(base));
    }

    private AddonCustomDataAccess() {}
}