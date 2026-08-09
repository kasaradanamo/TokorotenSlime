package net.kasara.tokorotenslime.fabric.duck;

import net.minecraft.nbt.CompoundTag;

/**
 * 死亡・リスポーン時にも引き継がれる永続データ領域をPlayerに追加するduckインターフェース。
 */
public interface PersistentDataHolder {

    CompoundTag tokorotenslime$getPersistentData();

    void tokorotenslime$setPersistentData(CompoundTag tag);
}
