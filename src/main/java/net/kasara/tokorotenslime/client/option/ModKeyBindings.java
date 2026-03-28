package net.kasara.tokorotenslime.client.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kasara.tokorotenslime.TokorotenSlime;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModKeyBindings {

    // TokorotenSlime用のキーマッピングカテゴリ
    public static final KeyBinding.Category TOKOROTENSLIME_CATEGORY =
            KeyBinding.Category.create(Identifier.of(TokorotenSlime.MOD_ID, "main"));
}