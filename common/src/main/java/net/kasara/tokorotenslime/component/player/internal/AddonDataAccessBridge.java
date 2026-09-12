package net.kasara.tokorotenslime.component.player.internal;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiFunction;

/**
 * アドオン用カスタムデータの読み書きをローダー側実装へ橋渡しする内部ブリッジ
 */
public final class AddonDataAccessBridge {

    public interface AddonDataWriter {
        void write(ServerPlayer player, String addonId, CompoundTag data);
    }

    public static BiFunction<Player, String, CompoundTag> GET_ADDON_DATA;
    public static AddonDataWriter WRITE_ADDON_DATA;

    private AddonDataAccessBridge() {}
}
