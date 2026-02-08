package net.kasara.tokorotenslime.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

/**
 * PedestalBlockに対応するBlockEntity<p>
 * 1スロットのインベントリを持ち、設置アイテムを管理。クライアントへの同期・回転アニメーションも行う
 */
public class PedestalBlockEntity extends BlockEntity implements Inventory {

    // 台座のアイテム用スロット(1個のみ)
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    // アイテムの回転角度（レンダリング用）
    private float rotation = 0;

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PEDESTAL_BE, pos, state);
    }

    /**
     * Inventoryのサイズを返す
     */
    @Override
    public int size() {
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
    public ItemStack getStack(int slot) {
        return items.get(slot);
    }

    /**
     * 指定スロットから指定数だけ取り出す
     */
    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(items, slot, amount);
        this.markDirty();
        return result;
    }

    /**
     * 指定スロットの全スタックを取り出す
     */
    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(items, slot);
        this.markDirty();
        return result;
    }

    /**
     * 指定スロットにアイテムをセット
     */
    @Override
    public void setStack(int slot, ItemStack stack) {
        items.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        this.markDirty();
    }

    /**
     * プレイヤーが使用できるか判定(8ブロック以内で使用可能)
     *
     * @return ブロックから8ブロック以内ならtrue
     */
    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return player.squaredDistanceTo(Vec3d.ofCenter(this.pos)) <= 64.0;
    }

    /**
     * Inventoryをクリアする
     */
    @Override
    public void clear() {
        items.clear();
        this.markDirty();
    }

    /**
     * NBTに保存するデータを書き込む
     */
    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, items);
    }

    /**
     * NBTからデータを読み込む
     */
    @Override
    protected void readData(ReadView view) {
        items.clear();
        super.readData(view);
        Inventories.readData(view, items);
    }

    /**
     * ブロックエンティティの状態を更新したときに呼ばれる
     */
    @Override
    public void markDirty() {
        super.markDirty();
        if (this.world != null && !world.isClient()) {
            this.world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    /**
     * クライアントへの同期パケットを返す
     */
    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    /**
     * チャンクデータ送信用の初期NBTを返す
     */
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
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
