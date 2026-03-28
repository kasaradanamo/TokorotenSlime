package net.kasara.tokorotenslime.storage;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * TokorotenSlime配下にアドオン用のカスタムデータを保存・取得するための内部ユーティリティ
 */
public class AddonCustomDataStorage {

    /**
     * 指定したアドオンIDに対応するNBTルートを取得
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @return アドオン用NbtCompound(存在しない場合は空)
     */
    public static NbtCompound getAddonRoot(PlayerEntity player, String addonId) {
        NbtComponent comp = player.get(DataComponentTypes.CUSTOM_DATA);
        NbtCompound base = comp != null ? comp.copyNbt() : new NbtCompound();
        NbtCompound tsRoot = base.getCompound(TokorotenSlime.MOD_ID).orElse(new NbtCompound());
        return tsRoot.getCompound(addonId).orElse(new NbtCompound());
    }

    /**
     * 指定したアドオンIDに対応するNBTルートを書き込む
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @param addonRoot 書き込むアドオン用NbtCompound
     */
    public static void writeAddonRoot(ServerPlayerEntity player, String addonId, NbtCompound addonRoot) {
        NbtComponent comp = player.get(DataComponentTypes.CUSTOM_DATA);
        NbtCompound base = comp != null ? comp.copyNbt() : new NbtCompound();
        NbtCompound tsRoot = base.getCompound(TokorotenSlime.MOD_ID).orElse(new NbtCompound());

        // 指定アドオンのデータのみを上書き
        tsRoot.put(addonId, addonRoot);

        // TokorotenSlime配下をbaseに戻して再設定
        base.put(TokorotenSlime.MOD_ID, tsRoot);
        player.setComponent(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(base));
    }

    private AddonCustomDataStorage() {}
}