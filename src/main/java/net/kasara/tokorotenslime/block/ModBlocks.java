package net.kasara.tokorotenslime.block;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

/**
 * TokorotenSlime内で使用するブロックを登録・管理するクラス
 */
public class ModBlocks {

    /** PedestalBlockのインスタンス。設置アイテム用の台座ブロック */
    public static final Block PEDESTAL = registerBlock("pedestal", properties -> new PedestalBlock(properties
            .strength(2f, 6.0f)         // ブロック耐久力・爆発耐性
            .sounds(BlockSoundGroup.POLISHED_DEEPSLATE) // サウンドグループ
            .nonOpaque()                                // 光を透過する
            .requiresTool()                             // 適切なツールでないと破壊できない
    ));

    /**
     * ブロックと対応するBlockItemを登録する共通メソッド
     *
     * @param name     登録するブロック名
     * @param function AbstractBlock.Settingsを受け取ってBlockを生成する関数
     * @return 登録されたBlockインスタンス
     */
    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        // ブロックのインスタンス生成
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK,
                Identifier.of(TokorotenSlime.MOD_ID, name))));
        // BlockItem を登録
        registerBlockItem(name, toRegister);

        // ブロック本体をレジストリに登録
        return Registry.register(Registries.BLOCK, Identifier.of(TokorotenSlime.MOD_ID, name), toRegister);
    }

    /**
     * Blockに対応するBlockItemを登録
     *
     * @param name  登録名
     * @param block 対応するBlock
     */
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TokorotenSlime.MOD_ID, name),
                new BlockItem(block, new Item.Settings()
                        .useBlockPrefixedTranslationKey()   // ブロック名を自動翻訳キーに利用
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TokorotenSlime.MOD_ID, name)))));
    }

    /**
     * ModBlocksの登録処理を呼び出す
     */
    public static void registerBlocks() {
        TokorotenSlime.LOGGER.info("Registering Blocks for " + TokorotenSlime.MOD_ID);
    }
}
