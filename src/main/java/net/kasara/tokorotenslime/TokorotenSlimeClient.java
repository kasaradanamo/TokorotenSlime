package net.kasara.tokorotenslime;

import net.kasara.tokorotenslime.block.entity.ModBlockEntities;
import net.kasara.tokorotenslime.client.render.block.entity.PedestalBlockEntityRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(value = TokorotenSlime.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = TokorotenSlime.MOD_ID, value = Dist.CLIENT)
public class TokorotenSlimeClient {

    public TokorotenSlimeClient(IEventBus modEventBus) {
        // レンダリング登録
        modEventBus.addListener(this::registerRenderers);
    }

    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // 台座の描画
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);

        // 登録完了ログを出力
        TokorotenSlime.LOGGER.info("Registering Mod Renderers for "+ TokorotenSlime.MOD_ID);
    }
}
