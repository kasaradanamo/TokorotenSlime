package net.kasara.tokorotenslime.client.render.block.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.kasara.tokorotenslime.client.internal.PedestalRenderRegistry;
import net.kasara.tokorotenslime.client.render.block.entity.state.PedestalRenderState;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

/**
 * PedestalBlockEntity上に置かれたアイテムの描画をする
 */
@Environment(EnvType.CLIENT)
public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity, PedestalRenderState> {

    private final ItemModelManager itemModelManager;

    public PedestalBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.itemModelManager = context.itemModelManager();
    }

    @Override
    public PedestalRenderState createRenderState() {
        return new PedestalRenderState();
    }

    /**
     * 描画情報の更新
     */
    @Override
    public void updateRenderState(PedestalBlockEntity blockEntity, PedestalRenderState state, float tickProgress, Vec3d cameraPos, ModelCommandRenderer.@Nullable CrumblingOverlayCommand crumblingOverlay) {
        BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);

        // 描画用ItemStackを取得
        state.stack = PedestalRenderRegistry.apply(blockEntity.getStack(0));

        state.rotation = blockEntity.getRenderingRotation();
        state.world = blockEntity.getWorld();

        this.itemModelManager.clearAndUpdate(
                state.itemRenderState,
                state.stack,
                ItemDisplayContext.GUI,
                state.world,
                null,
                (int) blockEntity.getPos().asLong()
        );
    }

    /**
     * 実際に描画する
     */
    @Override
    public void render(PedestalRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        if (state.stack.isEmpty()) return;

        matrices.push();

        matrices.translate(0.5f, 1.5f, 0.5f);
        matrices.scale(0.6f, 0.6f, 0.6f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.rotation));

        state.itemRenderState.render(
                matrices,
                queue,
                state.lightmapCoordinates,
                OverlayTexture.DEFAULT_UV,
                0
        );

        matrices.pop();
    }
}