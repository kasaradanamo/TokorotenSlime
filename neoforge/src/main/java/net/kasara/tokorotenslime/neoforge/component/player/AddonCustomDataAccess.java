package net.kasara.tokorotenslime.neoforge.component.player;

import net.kasara.tokorotenslime.neoforge.TokorotenSlime;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * アドオン用カスタムデータをプレイヤーのデータアタッチメントへ保存・取得する
 */
public class AddonCustomDataAccess {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, TokorotenSlime.MOD_ID);

    // アドオンIDごとのNBTを保持するアタッチメント。死亡してもリスポーン後に引き継ぐ
    public static final Supplier<AttachmentType<CompoundTag>> ADDON_DATA =
            ATTACHMENT_TYPES.register(
                    "addon_data",
                    () -> AttachmentType.builder(() -> new CompoundTag())
                            .serialize(CompoundTag.CODEC)
                            .copyOnDeath()
                            .build()
            );

    /**
     * 指定したアドオンIDに対応するNBTルートを取得
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @return アドオン用NbtCompound(存在しない場合は空)
     */
    public static CompoundTag getAddonRoot(Player player, String addonId) {
        return player.getData(ADDON_DATA).getCompound(addonId);
    }

    /**
     * 指定したアドオンIDに対応するNBTルートを書き込む
     *
     * @param player 対象プレイヤー
     * @param addonId アドオン識別子(アドオンのMOD_ID)
     * @param addonRoot 書き込むアドオン用NbtCompound
     */
    public static void writeAddonRoot(ServerPlayer player, String addonId, CompoundTag addonRoot) {
        CompoundTag root = player.getData(ADDON_DATA).copy();

        // 指定アドオンのデータのみを上書き
        root.put(addonId, addonRoot);

        player.setData(ADDON_DATA, root);
    }

    /**
     * AddonCustomDataAccessの登録処理を呼び出す
     */
    public static void register(IEventBus modEventBus) {
        ATTACHMENT_TYPES.register(modEventBus);

        // ログ出力
        TokorotenSlime.LOGGER.info("Registering Mod Attachment Types for " + TokorotenSlime.MOD_ID);
    }
}
