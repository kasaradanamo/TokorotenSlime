package net.kasara.tokorotenslime.client.option;

import net.kasara.tokorotenslime.TokorotenSlime;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class ModKeyMappings {

    // TokorotenSlime用のキーマッピングカテゴリ
    public static final KeyMapping.Category TOKOROTENSLIME_CATEGORY =
            new KeyMapping.Category(Identifier.fromNamespaceAndPath(TokorotenSlime.MOD_ID, "main"));
}