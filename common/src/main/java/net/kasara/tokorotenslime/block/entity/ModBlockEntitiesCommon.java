package net.kasara.tokorotenslime.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * ローダー側で登録されたBlockEntityTypeをcommonから参照するための保持クラス
 */
public class ModBlockEntitiesCommon {

    public static BlockEntityType<PedestalBlockEntity> PEDESTAL_BE;

    private ModBlockEntitiesCommon() {}
}
