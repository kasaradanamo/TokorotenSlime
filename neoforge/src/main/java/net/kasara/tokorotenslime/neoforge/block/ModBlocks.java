package net.kasara.tokorotenslime.neoforge.block;

import net.kasara.tokorotenslime.block.PedestalBlock;
import net.kasara.tokorotenslime.neoforge.TokorotenSlime;
import net.kasara.tokorotenslime.neoforge.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(TokorotenSlime.MOD_ID);

    // PedestalBlockのインスタンス。設置アイテム用の台座ブロック
    public static final DeferredBlock<Block> PEDESTAL =
            registerBlock("pedestal", props -> new PedestalBlock(props
                    .mapColor(MapColor.STONE)                       // 地図に表示される色
                    .strength(2f, 6.0f)    // ブロック耐久力・爆発耐性
                    .sound(SoundType.POLISHED_DEEPSLATE)            // サウンドグループ
                    .noOcclusion()                                  // 光を透過する
                    .requiresCorrectToolForDrops()                  // 適切なツールでないと破壊できない
    ));

    private static DeferredBlock<Block> registerBlock(String name, Function<Block.Properties, Block> factory) {
        DeferredBlock<Block> block = BLOCKS.registerBlock(name, factory);
        // BlockItemを登録
        registerBlockItem(name, block);
        // ブロック本体をレジストリに登録
        return block;
    }

    private static void registerBlockItem(String name, DeferredBlock<Block> block) {
        ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    /**
     * ModBlocksの登録処理を呼び出す
     */
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);

        // ログ出力
        TokorotenSlime.LOGGER.info("Registering Mod Blocks for " + TokorotenSlime.MOD_ID);
    }
}
