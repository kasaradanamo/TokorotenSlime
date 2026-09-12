package net.kasara.tokorotenslime.fabric;

import net.fabricmc.api.ModInitializer;
import net.kasara.tokorotenslime.TokorotenSlimeCommon;
import net.kasara.tokorotenslime.component.player.internal.AddonDataAccessBridge;
import net.kasara.tokorotenslime.fabric.block.ModBlocks;
import net.kasara.tokorotenslime.fabric.block.entity.ModBlockEntities;
import net.kasara.tokorotenslime.fabric.component.player.AddonCustomDataAccess;
import net.kasara.tokorotenslime.fabric.item.ModCreativeModeTabs;
import net.kasara.tokorotenslime.fabric.item.ModItems;
import net.kasara.tokorotenslime.item.internal.CreativeTabBridge;
import org.slf4j.Logger;

public class TokorotenSlime implements ModInitializer {

    public static final String MOD_ID = TokorotenSlimeCommon.MOD_ID;
    public static final Logger LOGGER = TokorotenSlimeCommon.LOGGER;

    @Override
    public void onInitialize() {
        // アイテム登録
        ModItems.register();

        // ブロック登録
        ModBlocks.register();

        // 台座ブロックエンティティ登録
        ModBlockEntities.register();

        // クリエイティブタブ登録
        ModCreativeModeTabs.register();

        // アドオン用データアタッチメント登録
        AddonCustomDataAccess.register();

        // common APIのクリエイティブタブ追加ブリッジを注入
        CreativeTabBridge.setHandler(ModCreativeModeTabs::addItemList);

        // common APIのアドオンデータアクセスブリッジを注入
        AddonDataAccessBridge.GET_ADDON_DATA = AddonCustomDataAccess::getAddonRoot;
        AddonDataAccessBridge.WRITE_ADDON_DATA = AddonCustomDataAccess::writeAddonRoot;
    }
}
