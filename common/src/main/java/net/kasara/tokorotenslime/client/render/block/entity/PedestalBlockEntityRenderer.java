package net.kasara.tokorotenslime.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.kasara.tokorotenslime.client.render.block.entity.state.PedestalRenderState;
import net.kasara.tokorotenslime.client.render.block.entity.internal.PedestalRenderRegistry;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

/**
 * PedestalBlockEntity上に置かれたアイテムの描画をする
 */
public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity, PedestalRenderState> {

    private final ItemModelResolver itemModelResolver;

    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public PedestalRenderState createRenderState() {
        return new PedestalRenderState();
    }

    /**
     * 描画情報の更新
     */
    @Override
    public void extractRenderState(PedestalBlockEntity blockEntity, PedestalRenderState state, float partialTicks, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        // 描画用ItemStackを取得
        state.stack = PedestalRenderRegistry.apply(blockEntity.getItem(0));

        state.rotation = blockEntity.getRenderingRotation(partialTicks);
        state.level = blockEntity.getLevel();

        itemModelResolver.updateForTopItem(
                state.renderState,
                state.stack,
                ItemDisplayContext.GUI,
                state.level,
                null,
                (int) blockEntity.getBlockPos().asLong()
        );
    }

    /**
     * 実際に描画する
     */
    @Override
    public void submit(PedestalRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (state.stack.isEmpty()) return;

        poseStack.pushPose();

        poseStack.translate(0.5f, 1.5f, 0.5f);
        poseStack.scale(0.6f, 0.6f, 0.6f);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.rotation));

        state.renderState.submit(
                poseStack,
                submitNodeCollector,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                0
        );

        poseStack.popPose();
    }
}