package net.kasara.tokorotenslime.forge.block.entity;

import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.kasara.tokorotenslime.forge.TokorotenSlime;
import net.kasara.tokorotenslime.forge.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TokorotenSlime.MOD_ID);

    // PedestalBlock用BlockEntityType
    public static final RegistryObject<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register(
                    "pedestal_be",
                    () -> BlockEntityType.Builder.of(PedestalBlockEntity::new, ModBlocks.PEDESTAL.get()).build(null)
            );

    /**
     * ModBlockEntitiesの登録処理を呼び出す
     */
    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);

        // ログ出力
        TokorotenSlime.LOGGER.info("Registering Mod Block Entities for " + TokorotenSlime.MOD_ID);
    }
}
