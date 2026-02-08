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

    /** 台座上のアイテムスタック */
    public ItemStack stack = ItemStack.EMPTY;

    /** アイテム描画用の状態オブジェクト */
    public ItemRenderState itemRenderState = new ItemRenderState();

    /** 台座の回転角度（度単位） */
    public float rotation;

    /** 台座のあるワールド */
    public World world;

    /**
     * デフォルトコンストラクタ
     */
    public PedestalRenderState() {
        super();
    }
}
