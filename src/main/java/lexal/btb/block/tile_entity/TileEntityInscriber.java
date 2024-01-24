package lexal.btb.block.tile_entity;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemRecord;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventorySorter;

public class TileEntityInscriber extends TileEntity implements IInventory {
    public int currentInscribeTime = 0;
    public int maxInscribeTime = 400;
    protected ItemStack[] inscriberItemStacks = new ItemStack[3];
    public int getInscribeProgressScaled(int i) {
        if (this.maxInscribeTime == 0) {
            return 0;
        }
        return this.currentInscribeTime * i / this.maxInscribeTime;
    }
    public boolean canInscribe() {
        if (inscriberItemStacks[0] == null) {return false;}
        if (inscriberItemStacks[0].getItem() != ModItems.recordBlank) {return false;}
        if (inscriberItemStacks[1] != null) { return false;}
        if (inscriberItemStacks[2] == null) { return false;}
        if (!(inscriberItemStacks[2].getItem() instanceof ItemRecord || inscriberItemStacks[2].getItem().id == ModItems.pancake.id)) { return false;}
        return true;
    }
    public void tick() {
        if (canInscribe()){
            ++currentInscribeTime;
            if (currentInscribeTime >= maxInscribeTime){
                currentInscribeTime = 0;
                inscribeItem();
            }
        } else {
            currentInscribeTime = 0;
        }

    }
    public void inscribeItem(){
        if (!canInscribe()) { return;}
        ItemStack itemstack = new ItemStack(inscriberItemStacks[2]);
        if (inscriberItemStacks[1] == null){
            if (inscriberItemStacks[2].getItem().id == ModItems.pancake.id){
                inscriberItemStacks[1] = new ItemStack(ModItems.recordPancake);
            } else {
                inscriberItemStacks[1] = itemstack.copy();
            }

        }
        --inscriberItemStacks[0].stackSize;
        if (this.inscriberItemStacks[0].stackSize <= 0) {
            this.inscriberItemStacks[0] = null;
        }
    }
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
        if (this.worldObj.getBlockTileEntity(this.x, this.y, this.z) != this) {
            return false;
        }
        return entityplayer.distanceToSqr((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) <= 64.0;
    }

    @Override
    public void sortInventory() {
        InventorySorter.sortInventory(inscriberItemStacks);
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
        this.currentInscribeTime = nbttagcompound.getShort("InscribeTime");
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
        nbttagcompound.putShort("InscribeTime", (short) this.currentInscribeTime);
    }
}
