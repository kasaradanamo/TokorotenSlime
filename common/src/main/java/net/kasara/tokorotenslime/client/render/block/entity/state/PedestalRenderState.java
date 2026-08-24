package net.kasara.tokorotenslime.client.render.block.entity.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * PedestalBlockEntityの描画状態を保持するオブジェクト
 */
public class PedestalRenderState extends BlockEntityRenderState {

    public ItemStack stack = ItemStack.EMPTY;
    public float rotation;
    public Level level;

    // アイテム描画用の状態オブジェクト
    public final ItemStackRenderState renderState = new ItemStackRenderState();
}