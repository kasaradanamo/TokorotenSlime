package net.kasara.tokorotenslime;

import com.mojang.logging.LogUtils;
import net.kasara.tokorotenslime.block.ModBlocks;
import net.kasara.tokorotenslime.block.entity.ModBlockEntities;
import net.kasara.tokorotenslime.item.ModCreativeModeTabs;
import net.kasara.tokorotenslime.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

@Mod(TokorotenSlime.MOD_ID)
public class TokorotenSlime {

    public static final String MOD_ID = "tokorotenslime";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TokorotenSlime(IEventBus modEventBus) {
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
    }

    private void addCreativeModeTab(BuildCreativeModeTabContentsEvent event) {
        // アドオンのアイテムをクリエイティブタブに追加
        ModCreativeModeTabs.addAddonItems(event);
    }
}
