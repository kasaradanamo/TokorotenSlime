package net.kasara.tokorotenslime.client.render.block.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * PedestalBlockEntityの描画状態を保持するオブジェクト
 */
@Environment(EnvType.CLIENT)
public class PedestalRenderState extends BlockEntityRenderState {

    public ItemStack stack = ItemStack.EMPTY;
    public float rotation;
    public World world;

    // アイテム描画用の状態オブジェクト
    public ItemRenderState itemRenderState = new ItemRenderState();
}