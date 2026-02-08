package net.kasara.tokorotenslime;

import net.fabricmc.api.ClientModInitializer;
import net.kasara.tokorotenslime.block.entity.ModBlockEntities;
import net.kasara.tokorotenslime.client.render.block.entity.PedestalBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class TokorotenSlimeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);  // 台座の描画
    }
}
