package net.kasara.tokorotenslime.client.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kasara.tokorotenslime.TokorotenSlime;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class ModKeyMappings {

    // TokorotenSlime用のキーマッピングカテゴリ
    public static final KeyMapping.Category TOKOROTENSLIME_CATEGORY =
            new KeyMapping.Category(Identifier.fromNamespaceAndPath(TokorotenSlime.MOD_ID, "main"));
}