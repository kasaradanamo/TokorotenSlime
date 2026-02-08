package net.kasara.tokorotenslime.block;

import com.mojang.serialization.MapCodec;
import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.minecraft.block.*;
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

    /** シリアライズ用の CODEC（データ保存・読み込み用） */
    public static final MapCodec<PedestalBlock> CODEC = PedestalBlock.createCodec(PedestalBlock::new);

    public PedestalBlock(Settings settings) {
        super(settings);
    }

    /**
     * CODECを取得
     */
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    /**
     * BlockEntityを作成
     */
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    /**
     * プレイヤーがブロックを右クリックしたときの処理
     */
    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // PedestalBlockEntityかどうか確認
        if (world.getBlockEntity(pos) instanceof PedestalBlockEntity pedestal) {

            ItemStack stackOnPedestal = pedestal.getStack(0);   // 台座上のアイテム

            // 1. 台座が空で、プレイヤーがアイテムを持ってる場合
            if (pedestal.isEmpty() && !stack.isEmpty()) {
                pedestal.setStack(0, stack.copyWithCount(1));   // 台座に1個置く
                stack.decrement(1);                          // プレイヤーの手から1個減らす
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.5f, 2f);
            }
            // 2. 台座にアイテムがあり、プレイヤーがスニークしておらず、プレイヤーの手が空または台座のアイテムと同じで、かつスタックが上限に達していない場合
            else if (!stackOnPedestal.isEmpty() && !player.isSneaking() &&
                    (stack.isEmpty() || (ItemStack.areItemsEqual(stack, stackOnPedestal) && stack.getCount() < stack.getMaxCount()))) {
                pedestal.clear();   // 台座のアイテムをクリア

                // プレイヤーの手が空ならアイテムを渡す、持っていたらスタック数を増やす
                if (stack.isEmpty()) player.setStackInHand(Hand.MAIN_HAND, stackOnPedestal);
                else stack.increment(1);

                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.5f, 1f);
            }
        }
        // 常に成功を返してクライアントに通知
        return ActionResult.SUCCESS;
    }
}