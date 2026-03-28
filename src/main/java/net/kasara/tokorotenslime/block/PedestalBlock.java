package net.kasara.tokorotenslime.block;

import com.mojang.serialization.MapCodec;
import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * 台座ブロック
 */
public class PedestalBlock  extends BlockWithEntity {

    public static final MapCodec<PedestalBlock> CODEC = PedestalBlock.createCodec(PedestalBlock::new);

    public PedestalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    /**
     * プレイヤーがブロックを右クリックしたときの処理
     */
    @Override
    protected ActionResult onUseWithItem(ItemStack itemStack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // PedestalBlockEntityかどうか確認
        if (world.getBlockEntity(pos) instanceof PedestalBlockEntity pedestal) {

            ItemStack placed = pedestal.getStack(0);   // 台座上のアイテム

            // 1. 台座が空で、プレイヤーがアイテムを持ってる場合
            if (pedestal.isEmpty() && !itemStack.isEmpty()) {
                pedestal.setStack(0, itemStack.copyWithCount(1));   // 台座に1個置く
                itemStack.decrement(1);                          // プレイヤーの手から1個減らす
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.5f, 2f);
            }
            // 2. 台座にアイテムがあり、プレイヤーがスニークしていない場合
            else if (!placed.isEmpty() && !player.isSneaking()) {
                // メインハンドが空の場合
                if (itemStack.isEmpty()) {
                    player.setStackInHand(hand, placed);
                    pedestal.clear();
                    world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.5f, 1f);
                }
                // 手の中のアイテムと台座のアイテムが同じで、スタックが上限に達していない場合
                else if (ItemStack.areItemsEqual(itemStack, placed) && itemStack.getCount() < itemStack.getMaxCount()) {
                    itemStack.increment(1);
                    pedestal.clear();
                    world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.5f, 1f);
                }
            }
        }
        // 常に成功を返す
        return ActionResult.SUCCESS;
    }
}