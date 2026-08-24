package net.kasara.tokorotenslime.block;

import com.mojang.serialization.MapCodec;
import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * 台座ブロック
 */
public class PedestalBlock extends BaseEntityBlock {

    public static final MapCodec<PedestalBlock> CODEC = simpleCodec(PedestalBlock::new);

    public PedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    /**
     * プレイヤーがブロックを右クリックしたときの処理
     */
    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        //PedestalBlockEntityかどうか確認
        if (level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestal) {

            ItemStack placed = pedestal.getItem(0);   // 台座上のアイテム

            // 1. 台座が空で、プレイヤーがアイテムを持ってる場合
            if (pedestal.isEmpty() && !itemStack.isEmpty()) {
                pedestal.setItem(0, itemStack.copyWithCount(1));   // 台座に1個置く
                itemStack.shrink(1);                          // プレイヤーの手から1個減らす
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5f, 2f);
            }
            // 2. 台座にアイテムがあり、プレイヤーがスニークしていない場合
            else if (!placed.isEmpty() && !player.isShiftKeyDown()) {
                // メインハンドが空の場合
                if (itemStack.isEmpty()) {
                    player.setItemInHand(hand, placed);
                    pedestal.clearContent();
                    level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5f, 1f);
                }
                // 手の中のアイテムと台座のアイテムが同じで、スタックが上限に達していない場合
                else if (ItemStack.isSameItemSameComponents(itemStack, placed) && itemStack.getCount() < itemStack.getMaxStackSize()) {
                    itemStack.grow(1);
                    pedestal.clearContent();
                    level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5f, 1f);
                }
            }
        }
        // 常に成功を返す
        return InteractionResult.SUCCESS;
    }
}