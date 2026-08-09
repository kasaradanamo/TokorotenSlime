package net.kasara.tokorotenslime.fabric.mixin;

import net.kasara.tokorotenslime.fabric.duck.PersistentDataHolder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * addAdditionalSaveData/readAdditionalSaveDataへの直接フックで永続NBT領域を追加する。
 */
@Mixin(Player.class)
public abstract class PlayerMixin implements PersistentDataHolder {

    @Unique
    private CompoundTag tokorotenslime$persistentData = new CompoundTag();

    @Override
    public CompoundTag tokorotenslime$getPersistentData() {
        return tokorotenslime$persistentData;
    }

    @Override
    public void tokorotenslime$setPersistentData(CompoundTag tag) {
        this.tokorotenslime$persistentData = tag;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void tokorotenslime$saveData(CompoundTag tag, CallbackInfo ci) {
        tag.put("TokorotenSlimePersistentData", tokorotenslime$persistentData);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void tokorotenslime$loadData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("TokorotenSlimePersistentData")) {
            tokorotenslime$persistentData = tag.getCompound("TokorotenSlimePersistentData");
        }
    }
}
