package net.kasara.tokorotenslime.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kasara.tokorotenslime.TokorotenSlime;
import net.kasara.tokorotenslime.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    // PedestalBlock用BlockEntityType
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            registerBlockEntities(
                    "pedestal_be",
                    PedestalBlockEntity::new,
                    ModBlocks.PEDESTAL
            );

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntities(String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(TokorotenSlime.MOD_ID, name),
                FabricBlockEntityTypeBuilder.create(factory, blocks).build()
        );
    }

    /**
     * ModBlockEntitiesの登録処理を呼び出す
     */
    public static void registerModBlockEntities() {
        TokorotenSlime.LOGGER.info("Registering Block Entities for " + TokorotenSlime.MOD_ID);
    }
}
