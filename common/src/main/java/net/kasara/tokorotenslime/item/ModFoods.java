package net.kasara.tokorotenslime.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    // 「アジフライ」用の食料コンポーネント
    // 満腹度:10(パンが5、ステーキが8)
    // 満腹度回復効率:1.0
    public static final FoodProperties AJIFURAI = new FoodProperties.Builder().nutrition(10)
            .saturationMod(1.0F).build();

    // 「バッター液付き魚」用コンポーネント
    // 満腹度:4
    // 満腹度回復効率:0.7
    public static final FoodProperties BETTER_FISH = new FoodProperties.Builder().nutrition(4)
            .saturationMod(0.7F).build();
}
