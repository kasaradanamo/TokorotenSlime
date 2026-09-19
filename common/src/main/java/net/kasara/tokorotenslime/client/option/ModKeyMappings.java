package net.kasara.tokorotenslime.client.option;

import net.kasara.tokorotenslime.TokorotenSlimeCommon;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class ModKeyMappings {

    // TokorotenSlime用のキーマッピングカテゴリ
    public static final KeyMapping.Category TOKOROTENSLIME_CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath(TokorotenSlimeCommon.MOD_ID, "main"));
}