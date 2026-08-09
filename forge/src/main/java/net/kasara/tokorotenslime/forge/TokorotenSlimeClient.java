package net.kasara.tokorotenslime.forge;

import net.kasara.tokorotenslime.client.render.block.entity.PedestalBlockEntityRenderer;
import net.kasara.tokorotenslime.forge.block.entity.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TokorotenSlime.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TokorotenSlimeClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // 台座の描画
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);

        // 登録完了ログを出力
        TokorotenSlime.LOGGER.info("Registering Mod Renderers for " + TokorotenSlime.MOD_ID);
    }
}
