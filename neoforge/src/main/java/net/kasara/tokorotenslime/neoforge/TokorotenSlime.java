package net.kasara.tokorotenslime.neoforge;

import net.kasara.tokorotenslime.TokorotenSlimeCommon;
import net.kasara.tokorotenslime.block.entity.ModBlockEntitiesCommon;
import net.kasara.tokorotenslime.item.internal.CreativeTabBridge;
import net.kasara.tokorotenslime.neoforge.block.ModBlocks;
import net.kasara.tokorotenslime.neoforge.block.entity.ModBlockEntities;
import net.kasara.tokorotenslime.neoforge.item.ModCreativeModeTabs;
import net.kasara.tokorotenslime.neoforge.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

@Mod(TokorotenSlime.MOD_ID)
public class TokorotenSlime {

    public static final String MOD_ID = TokorotenSlimeCommon.MOD_ID;
    public static final Logger LOGGER = TokorotenSlimeCommon.LOGGER;

    public TokorotenSlime(IEventBus modEventBus) {
        // アイテム登録
        ModItems.register(modEventBus);

        // ブロック登録
        ModBlocks.register(modEventBus);

        // 台座ブロックエンティティ登録
        ModBlockEntities.register(modEventBus);

        // クリエイティブタブ登録
        ModCreativeModeTabs.register(modEventBus);

        // レジストリ登録の完了後にcommonのブリッジへ反映する
        modEventBus.addListener(this::commonSetup);

        // クリエイティブタブにアイテムを登録
        modEventBus.addListener(this::addCreativeModeTab);

        // common APIのクリエイティブタブ追加ブリッジを注入
        CreativeTabBridge.setHandler(ModCreativeModeTabs::addItemList);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // DeferredHolderは登録イベント発火後でないと解決できないためここで代入する
        ModBlockEntitiesCommon.PEDESTAL_BE = ModBlockEntities.PEDESTAL_BE.get();
    }

    private void addCreativeModeTab(BuildCreativeModeTabContentsEvent event) {
        // アドオンのアイテムをクリエイティブタブに追加
        ModCreativeModeTabs.addAddonItems(event);
    }
}
