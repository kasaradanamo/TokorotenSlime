package net.kasara.tokorotenslime.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

@Environment(EnvType.CLIENT)
public class PedestalRenderRegistry {

    private static final Map<String, BiConsumer<ItemStack, RenderContext>> handlers = new HashMap<>();

    /**
     * @deprecated Since 1.1.0, use {@link TokorotenSlimeAPI#registerPedestalRenderHandler(Item, UnaryOperator)} instead.
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    public static void registerHandler(String id, BiConsumer<ItemStack, RenderContext> handler) {
        handlers.put(id, handler);
    }

    /**
     * @deprecated Since 1.1.0, use {@link TokorotenSlimeAPI#registerPedestalRenderHandler(Item, UnaryOperator)} instead.
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    public static BiConsumer<ItemStack, RenderContext> getHandler(String id) {
        return handlers.get(id);
    }

    /**
     * @deprecated Since 1.1.0, use {@link TokorotenSlimeAPI#registerPedestalRenderHandler(Item, UnaryOperator)} instead.
     */
    @Deprecated(since = "1.1.0", forRemoval = true)
    public record RenderContext(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    }
}
