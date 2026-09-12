package net.kasara.tokorotenslime.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.kasara.tokorotenslime.client.render.block.entity.PedestalBlockEntityRenderer;
import net.kasara.tokorotenslime.fabric.block.entity.ModBlockEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class TokorotenSlimeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // レンダリング登録
        registerRenderers();
    }

    private void registerRenderers() {
        // 台座の描画
        BlockEntityRenderers.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);

        // 登録完了ログを出力
        TokorotenSlime.LOGGER.info("Registering Mod Renderers for "+ TokorotenSlime.MOD_ID);
    }
}
