package net.kasara.tokorotenslime.forge;

import net.kasara.tokorotenslime.TokorotenSlimeCommon;
import net.kasara.tokorotenslime.component.player.internal.AddonDataAccessBridge;
import net.kasara.tokorotenslime.block.entity.ModBlockEntitiesCommon;
import net.kasara.tokorotenslime.item.internal.CreativeTabBridge;
import net.kasara.tokorotenslime.forge.block.ModBlocks;
import net.kasara.tokorotenslime.forge.block.entity.ModBlockEntities;
import net.kasara.tokorotenslime.forge.component.player.AddonCustomDataAccess;
import net.kasara.tokorotenslime.forge.item.ModCreativeModeTabs;
import net.kasara.tokorotenslime.forge.item.ModItems;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TokorotenSlime.MOD_ID)
public class TokorotenSlime {

    public static final String MOD_ID = TokorotenSlimeCommon.MOD_ID;
    public static final Logger LOGGER = TokorotenSlimeCommon.LOGGER;

    public TokorotenSlime() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // common APIのアドオンデータアクセスブリッジを注入
        AddonDataAccessBridge.GET_ADDON_DATA = AddonCustomDataAccess::getAddonRoot;
        AddonDataAccessBridge.WRITE_ADDON_DATA = AddonCustomDataAccess::writeAddonRoot;

        // common APIのクリエイティブタブ追加ブリッジを注入
        CreativeTabBridge.setHandler(ModCreativeModeTabs::addItemList);

        // アイテム登録
        ModItems.register(modEventBus);

        // ブロック登録
        ModBlocks.register(modEventBus);

        // 台座ブロックエンティティ登録
        ModBlockEntities.register(modEventBus);

        // クリエイティブタブ登録
        ModCreativeModeTabs.register(modEventBus);

        // クリエイティブタブにアイテムを登録
        modEventBus.addListener(this::addCreativeModeTab);

        // common側の共有ホルダーへBlockEntityTypeを反映
        modEventBus.addListener(this::commonSetup);
    }

    private void addCreativeModeTab(BuildCreativeModeTabContentsEvent event) {
        // アドオンのアイテムをクリエイティブタブに追加
        ModCreativeModeTabs.addAddonItems(event);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        ModBlockEntitiesCommon.PEDESTAL_BE = ModBlockEntities.PEDESTAL_BE.get();
    }
}
