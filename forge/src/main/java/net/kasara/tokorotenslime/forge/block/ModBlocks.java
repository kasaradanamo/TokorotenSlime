package net.kasara.tokorotenslime.forge.block;

import net.kasara.tokorotenslime.block.PedestalBlock;
import net.kasara.tokorotenslime.forge.TokorotenSlime;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TokorotenSlime.MOD_ID);

    // PedestalBlockのインスタンス。設置アイテム用の台座ブロック
    public static final RegistryObject<Block> PEDESTAL =
            BLOCKS.register("pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)                       // 地図に表示される色
                    .strength(2f, 6.0f)                             // ブロック耐久力・爆発耐性
                    .sound(SoundType.POLISHED_DEEPSLATE)            // サウンドグループ
                    .noOcclusion()                                  // 光を透過する
                    .requiresCorrectToolForDrops()                  // 適切なツールでないと破壊できない
            ));

    /**
     * ModBlocksの登録処理を呼び出す
     */
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);

        // ログ出力
        TokorotenSlime.LOGGER.info("Registering Mod Blocks for " + TokorotenSlime.MOD_ID);
    }
}
