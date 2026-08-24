package net.kasara.tokorotenslime.neoforge.block.entity;

import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.kasara.tokorotenslime.neoforge.TokorotenSlime;
import net.kasara.tokorotenslime.neoforge.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TokorotenSlime.MOD_ID);

    // PedestalBlock用BlockEntityType
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register(
                    "pedestal_be",
                    () -> new BlockEntityType<>(
                            PedestalBlockEntity::new,
                            Set.of(ModBlocks.PEDESTAL.get())
            ));

    /**
     * ModBlockEntitiesの登録処理を呼び出す
     */
    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);

        // ログ出力
        TokorotenSlime.LOGGER.info("Registering Mod Block Entities for " + TokorotenSlime.MOD_ID);
    }
}
