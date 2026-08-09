package net.kasara.tokorotenslime.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.kasara.tokorotenslime.TokorotenSlimeCommon;
import net.kasara.tokorotenslime.component.player.internal.AddonDataAccessBridge;
import net.kasara.tokorotenslime.item.internal.CreativeTabBridge;
import net.kasara.tokorotenslime.fabric.block.ModBlocks;
import net.kasara.tokorotenslime.fabric.block.entity.ModBlockEntities;
import net.kasara.tokorotenslime.fabric.component.player.AddonCustomDataAccess;
import net.kasara.tokorotenslime.fabric.duck.PersistentDataHolder;
import net.kasara.tokorotenslime.fabric.item.ModCreativeModeTabs;
import net.kasara.tokorotenslime.fabric.item.ModItems;
import org.slf4j.Logger;

public class TokorotenSlime implements ModInitializer {

    public static final String MOD_ID = TokorotenSlimeCommon.MOD_ID;
    public static final Logger LOGGER = TokorotenSlimeCommon.LOGGER;

    @Override
    public void onInitialize() {
        // common APIのアドオンデータアクセスブリッジを注入
        AddonDataAccessBridge.GET_ADDON_DATA = AddonCustomDataAccess::getAddonRoot;
        AddonDataAccessBridge.WRITE_ADDON_DATA = AddonCustomDataAccess::writeAddonRoot;

        // common APIのクリエイティブタブ追加ブリッジを注入
        CreativeTabBridge.setHandler(ModCreativeModeTabs::addItemList);

        // アイテム登録
        ModItems.register();

        // ブロック登録
        ModBlocks.register();

        // 台座ブロックエンティティ登録
        ModBlockEntities.register();

        // クリエイティブタブ登録
        ModCreativeModeTabs.register();

        // 死亡/次元移動でPlayerインスタンスが差し替わる際、永続データ領域を引き継ぐ
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            PersistentDataHolder oldHolder = (PersistentDataHolder) oldPlayer;
            PersistentDataHolder newHolder = (PersistentDataHolder) newPlayer;
            newHolder.tokorotenslime$setPersistentData(oldHolder.tokorotenslime$getPersistentData().copy());
        });
    }
}
