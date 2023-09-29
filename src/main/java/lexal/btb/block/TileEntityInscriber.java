package lexal.btb.block;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

public class TileEntityInscriber extends TileEntity implements IInventory {
    protected ItemStack[] inscriberItemStacks = new ItemStack[3];
    @Override
    public int getSizeInventory() {
        return inscriberItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inscriberItemStacks[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (this.inscriberItemStacks[i] != null) {
            if (this.inscriberItemStacks[i].stackSize <= j) {
                ItemStack itemstack = this.inscriberItemStacks[i];
                this.inscriberItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = this.inscriberItemStacks[i].splitStack(j);
            if (this.inscriberItemStacks[i].stackSize <= 0) {
                this.inscriberItemStacks[i] = null;
            }
            return itemstack1;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        this.inscriberItemStacks[i] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {
        return "Inscriber";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        if (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) {
            return false;
        }
        return entityplayer.distanceToSqr((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }
    @Override
    public void readFromNBT(CompoundTag nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        ListTag nbttaglist = nbttagcompound.getList("Items");
        this.inscriberItemStacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            CompoundTag nbttagcompound1 = (CompoundTag)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 < 0 || byte0 >= this.inscriberItemStacks.length) continue;
            this.inscriberItemStacks[byte0] = ItemStack.readItemStackFromNbt(nbttagcompound1);
        }
    }

    @Override
    public void writeToNBT(CompoundTag nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        ListTag nbttaglist = new ListTag();
        for (int i = 0; i < this.inscriberItemStacks.length; ++i) {
            if (this.inscriberItemStacks[i] == null) continue;
            CompoundTag nbttagcompound1 = new CompoundTag();
            nbttagcompound1.putByte("Slot", (byte)i);
            this.inscriberItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.addTag(nbttagcompound1);
        }
        nbttagcompound.put("Items", nbttaglist);
    }
}
