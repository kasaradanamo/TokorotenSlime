package net.kasara.tokorotenslime.fabric.block;

import net.kasara.tokorotenslime.block.PedestalBlock;
import net.kasara.tokorotenslime.fabric.TokorotenSlime;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class ModBlocks {

    // PedestalBlockのインスタンス。設置アイテム用の台座ブロック
    public static final Block PEDESTAL =
            registerBlock("pedestal", props -> new PedestalBlock(props
                    .mapColor(MapColor.STONE)                       // 地図に表示される色
                    .strength(2f, 6.0f)    // ブロック耐久力・爆発耐性
                    .sound(SoundType.POLISHED_DEEPSLATE)            // サウンドグループ
                    .noOcclusion()                                  // 光を透過する
                    .requiresCorrectToolForDrops()                  // 適切なツールでないと破壊できない
    ));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(TokorotenSlime.MOD_ID, name);
        Block block = factory.apply(BlockBehaviour.Properties.of());

        // BlockItemを登録
        registerBlockItem(id, block);

        // ブロック本体をレジストリに登録
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    private static void registerBlockItem(ResourceLocation blockId, Block block) {
        Registry.register(
                BuiltInRegistries.ITEM,
                blockId,
                new BlockItem(block, new Item.Properties())
        );
    }

    /**
     * ModBlocksの登録処理を呼び出す
     */
    public static void register() {
        TokorotenSlime.LOGGER.info("Registering Mod Blocks for " + TokorotenSlime.MOD_ID);
    }
}
