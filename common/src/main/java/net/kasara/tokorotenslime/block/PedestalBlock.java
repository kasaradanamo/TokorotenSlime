package net.kasara.tokorotenslime.block;

import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

/**
 * 台座ブロック
 */
public class PedestalBlock extends BaseEntityBlock {

    public PedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    /**
     * ブロックが破壊/置換されたとき、台座上のアイテムをドロップする
     */
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof PedestalBlockEntity pedestal) {
                Containers.dropContents(level, pos, pedestal);
            }
            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    /**
     * プレイヤーがブロックを右クリックしたときの処理
     */
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        // PedestalBlockEntityかどうか確認
        if (level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestal) {

            ItemStack itemStack = player.getItemInHand(hand);
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
                else if (ItemStack.isSameItemSameTags(itemStack, placed) && itemStack.getCount() < itemStack.getMaxStackSize()) {
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
