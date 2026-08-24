package net.kasara.tokorotenslime.fabric.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kasara.tokorotenslime.block.entity.ModBlockEntitiesCommon;
import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.kasara.tokorotenslime.fabric.TokorotenSlime;
import net.kasara.tokorotenslime.fabric.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    // PedestalBlock用BlockEntityType
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            registerBlockEntities(
                    "pedestal_be",
                    PedestalBlockEntity::new,
                    ModBlocks.PEDESTAL
            );

    static {
        // commonから参照できるようブリッジへ反映する
        ModBlockEntitiesCommon.PEDESTAL_BE = PEDESTAL_BE;
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntities(String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(TokorotenSlime.MOD_ID, name),
                FabricBlockEntityTypeBuilder.create(factory, blocks).build()
        );
    }

    /**
     * ModBlockEntitiesの登録処理を呼び出す
     */
    public static void register() {
        TokorotenSlime.LOGGER.info("Registering Mod Block Entities for " + TokorotenSlime.MOD_ID);
    }
}
