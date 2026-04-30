package net.kasara.tokorotenslime.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/**
 * PedestalBlockに対応するBlockEntity
 * 1スロットのインベントリを持ち、設置アイテムを管理。クライアントへの同期・回転アニメーションも行う
 */
public class PedestalBlockEntity extends BlockEntity implements Container {

    // 台座のアイテム用スロット(1個のみ)
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    // アイテムの回転角度（レンダリング用）
    private float rotation = 0;

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PEDESTAL_BE.get(), pos, state);
    }

    /**
     * Inventoryのサイズを返す
     */
    @Override
    public int getContainerSize() {
        return items.size();
    }

    /**
     * スロットが空かどうか返す
     */
    @Override
    public boolean isEmpty() {
        return items.getFirst().isEmpty();
    }

    /**
     * 指定スロットのアイテムを取得
     */
    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    /**
     * 指定スロットから指定数だけ取り出す
     */
    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(items, slot, amount);
        this.setChanged();
        return result;
    }

    /**
     * 指定スロットの全スタックを取り出す
     */
    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack result = ContainerHelper.takeItem(items, slot);
        this.setChanged();
        return result;
    }

    /**
     * 指定スロットにアイテムをセット
     */
    @Override
    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
        this.setChanged();
    }

    /**
     * プレイヤーが使用できるか判定(8ブロック以内で使用可能)
     *
     * @return ブロックから8ブロック以内ならtrue
     */
    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(
                worldPosition.getX() + 0.5,
                worldPosition.getY() + 0.5,
                worldPosition.getZ() + 0.5
        ) <= 64.0;
    }

    /**
     * スロットにアイテムがなく、アイテムスタックが1つの場合、true
     */
    @Override
    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        return items.get(slot).isEmpty() && itemStack.getCount() == 1;
    }

    /**
     * Maxスタックサイズを1にする
     */
    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public int getMaxStackSize(ItemStack itemStack) {
        return 1;
    }

    /**
     * Inventoryをクリアする
     */
    @Override
    public void clearContent() {
        items.clear();
        this.setChanged();
    }

    /**
     * NBTに保存するデータを書き込む
     */
    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
    }

    /**
     * NBTからデータを読み込む
     */
    @Override
    protected void loadAdditional(ValueInput input) {
        items.clear();
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);
    }

    /**
     * ブロックエンティティの状態を更新した時に呼ばれる
     */
    @Override
    public void setChanged() {
        super.setChanged();
        if (this.level != null && !level.isClientSide()) {
            this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    /**
     * クライアントへの同期パケットを返す
     */
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    /**
     * チャンクデータ送信用の初期NBTを返す
     */
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    /**
     * アイテムの回転角度を1tickごとに更新して返す
     */
    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;   // 360度超えたらリセット
        }
        return rotation;
    }
}