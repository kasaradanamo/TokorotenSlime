package net.kasara.tokorotenslime.client.render.block.entity.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;

/**
 * PedestalBlockEntityの描画状態を保持するオブジェクト
 */
public class PedestalRenderState extends BlockEntityRenderState {

    public ItemStack stack = ItemStack.EMPTY;
    public float rotation;

    // アイテム描画用の状態オブジェクト
    public final ItemStackRenderState renderState = new ItemStackRenderState();
}