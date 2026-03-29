package net.kasara.tokorotenslime.client.render.block.entity;

import net.kasara.tokorotenslime.block.entity.PedestalBlockEntity;
import net.kasara.tokorotenslime.client.internal.PedestalRenderRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

/**
 * PedestalBlockEntity上に置かれたアイテムの描画をする
 */
public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {

    private final ItemRenderer itemRenderer;

    public PedestalBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    /**
     * レンダリング処理
     */
    @Override
    public void render(PedestalBlockEntity blockEntity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        ItemStack stack = PedestalRenderRegistry.apply(blockEntity.getStack(0));
        if (stack.isEmpty()) return;

        matrices.push();

        matrices.translate(0.5f, 1.5f, 0.5f);
        matrices.scale(0.6f, 0.6f, 0.6f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(blockEntity.getRenderingRotation()));

        this.itemRenderer.renderItem(
                stack,
                ItemDisplayContext.GUI,
                light,
                overlay,
                matrices,
                vertexConsumers,
                blockEntity.getWorld(),
                (int) blockEntity.getPos().asLong()
        );

        matrices.pop();
    }
}
