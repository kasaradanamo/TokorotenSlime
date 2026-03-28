package net.kasara.tokorotenslime.block;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class ModBlocks {

    // PedestalBlockのインスタンス。設置アイテム用の台座ブロック
    public static final Block PEDESTAL = registerBlock("pedestal", props -> new PedestalBlock(props
            .mapColor(MapColor.STONE)                       // 地図に表示される色
            .strength(2f, 6.0f)    // ブロック耐久力・爆発耐性
            .sound(SoundType.POLISHED_DEEPSLATE)            // サウンドグループ
            .noOcclusion()                                  // 光を透過する
            .requiresCorrectToolForDrops()                  // 適切なツールでないと破壊できない
    ));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TokorotenSlime.MOD_ID, name));
        Block block = factory.apply(BlockBehaviour.Properties.of().setId(key));
        // BlockItem を登録
        registerBlockItem(key, block);
        // ブロック本体をレジストリに登録
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    private static void registerBlockItem(ResourceKey<Block> blockKey, Block block) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, blockKey.identifier());
        Registry.register(
                BuiltInRegistries.ITEM,
                itemKey,
                new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix())
        );
    }

    /**
     * ModBlocksの登録処理を呼び出す
     */
    public static void registerBlocks() {
        TokorotenSlime.LOGGER.info("Registering Blocks for " + TokorotenSlime.MOD_ID);
    }
}
