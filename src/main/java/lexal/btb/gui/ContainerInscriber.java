package lexal.btb.gui;

import lexal.btb.block.TileEntityInscriber;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotFurnace;

import java.util.List;

public class ContainerInscriber extends Container {
    private TileEntityInscriber tileEntity;
    public ContainerInscriber(InventoryPlayer inventoryplayer, TileEntityInscriber tileEntityInscriber) {
        this.tileEntity = tileEntityInscriber;
        this.addSlot(new Slot(tileEntityInscriber,0, 56, 17));
        this.addSlot(new Slot(tileEntityInscriber,1, 56, 53));
        this.addSlot(new Slot(tileEntityInscriber,2, 116, 35));
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }
        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }
    }
    @Override
    public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityplayer) {
        return this.tileEntity.canInteractWith(entityplayer);
    }
}
