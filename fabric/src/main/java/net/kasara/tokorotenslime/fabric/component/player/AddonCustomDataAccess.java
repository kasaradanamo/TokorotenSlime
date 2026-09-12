package net.kasara.tokorotenslime.fabric.component.player;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.kasara.tokorotenslime.fabric.TokorotenSlime;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

/**
 * アドオン用カスタムデータをプレイヤーのデータアタッチメントへ保存・取得する
 */
public class AddonCustomDataAccess {

    // アドオンIDごとのNBTを保持するアタッチメント。死亡してもリスポーン後に引き継ぐ
    public static final AttachmentType<CompoundTag> ADDON_DATA =
            AttachmentRegistry.<CompoundTag>builder()
                    .initializer(CompoundTag::new)
                    .persistent(CompoundTag.CODEC)
                    .copyOnDeath()
                    .buildAndRegister(ResourceLocation.fromNamespaceAndPath(TokorotenSlime.MOD_ID, "addon_data"));

    /**
     * 指定したアドオンIDに対応するNBTルートを取得
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @return アドオン用NbtCompound(存在しない場合は空)
     */
    public static CompoundTag getAddonRoot(Player player, String addonId) {
        return player.getAttachedOrCreate(ADDON_DATA).getCompound(addonId);
    }

    /**
     * 指定したアドオンIDに対応するNBTルートを書き込む
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @param addonRoot 書き込むアドオン用NbtCompound
     */
    public static void writeAddonRoot(ServerPlayer player, String addonId, CompoundTag addonRoot) {
        CompoundTag root = player.getAttachedOrCreate(ADDON_DATA).copy();

        // 指定アドオンのデータのみを上書き
        root.put(addonId, addonRoot);

        player.setAttached(ADDON_DATA, root);
    }

    /**
     * AddonCustomDataAccessの登録処理を呼び出す
     */
    public static void register() {
        TokorotenSlime.LOGGER.info("Registering Mod Attachment Types for " + TokorotenSlime.MOD_ID);
    }

    private AddonCustomDataAccess() {}
}
