package net.kasara.tokorotenslime;

import net.fabricmc.api.ModInitializer;
import net.kasara.tokorotenslime.block.ModBlocks;
import net.kasara.tokorotenslime.block.entity.ModBlockEntities;
import net.kasara.tokorotenslime.item.ModItemGroups;
import net.kasara.tokorotenslime.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokorotenSlime implements ModInitializer {
    public static final String MOD_ID = "tokorotenslime";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // アイテム登録
        ModItems.registerModItems();

        // ブロック登録
        ModBlocks.registerBlocks();

        // 台座ブロックエンティティ登録
        ModBlockEntities.registerModBlockEntities();

        // アイテムグループ登録
        ModItemGroups.registerItemGroups();

    }
}