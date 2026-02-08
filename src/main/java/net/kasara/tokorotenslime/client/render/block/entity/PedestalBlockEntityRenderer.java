package net.kasara.tokorotenslime.client.render.block.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.kasara.tokorotenslime.client.render.block.entity.state.PedestalRenderState;
import net.kasara.tokorotenslime.client.internal.PedestalRenderRegistry;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

/**
 * PedestalBlockEntity上に置かれたアイテムの描画を担当するレンダラー。
 * カスタムレンダラーがPedestalRenderRegistryに登録されている場合はそれを使用。
 * 登録がない場合は標準のItemRenderStateを使って描画
 */
@Environment(EnvType.CLIENT)
public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity, PedestalRenderState> {

    // アイテムモデル管理クラス。ItemRenderStateの更新などで使用
    private final ItemModelManager itemModelManager;

    /**
     * コンストラクタ
     * @param context BlockEntityRendererFactory.Context
     */
    public PedestalBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.itemModelManager = context.itemModelManager();
    }

    /**
     * 描画状態オブジェクトを生成
     *
     * @return PedestalRenderState の新しいインスタンス
     */
    @Override
    public PedestalRenderState createRenderState() {
        return new PedestalRenderState();
    }

    /**
     * 毎フレーム描画前にレンダリング状態を更新
     */
    @Override
    public void updateRenderState(PedestalBlockEntity blockEntity, PedestalRenderState state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
        BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);

        // 台座の回転角度を描画状態に反映
        state.rotation = blockEntity.getRenderingRotation();

        // 台座上のアイテムスタックを取得
        state.stack = blockEntity.getStack(0);

        // 台座のあるワールド取得
        state.world = blockEntity.getWorld();

        // ItemRenderState を更新して描画準備
        this.itemModelManager.clearAndUpdate(
                state.itemRenderState,
                blockEntity.getStack(0),
                ItemDisplayContext.GUI,
                blockEntity.getWorld(),
                null,
                (int) blockEntity.getPos().asLong() // ブロック位置をシードとして利用
        );
    }

    /**
     * 実際の描画処理
     */
    @Override
    public void render(PedestalRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {

        if (state.itemRenderState.isEmpty()) return;    // アイテムがなければ描画しない

        matrices.push();                                // ここからの行列変換はローカルに限定
        matrices.translate(0.5f, 1.5f, 0.5f);  // 台座中央にアイテムを移動
        matrices.scale(0.6f, 0.6f, 0.6f);       // アイテムサイズを縮小
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.rotation));  // 回転

        int light = state.lightmapCoordinates;

        // 描画用ItemStackを取得
        ItemStack renderStack = PedestalRenderRegistry.apply(state.stack);

        itemModelManager.clearAndUpdate(
                state.itemRenderState,
                renderStack,
                ItemDisplayContext.GUI,
                state.world,
                null,
                renderStack.hashCode()
        );

        state.itemRenderState.render(
                matrices,
                queue,
                light,
                OverlayTexture.DEFAULT_UV,
                0
        );

        matrices.pop(); // 行列スタックを元に戻す
    }
}
