package lexal.btb.gui;

import lexal.btb.block.tile_entity.TileEntityInscriber;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

import java.util.ArrayList;
import java.util.List;

public class ContainerInscriber extends Container {
    private TileEntityInscriber tileEntity;
    public ContainerInscriber(InventoryPlayer inventoryplayer, TileEntityInscriber tileEntityInscriber) {
        this.tileEntity = tileEntityInscriber;
        this.addSlot(new SlotDiscBlank(tileEntityInscriber,0, 56, 17));
        this.addSlot(new SlotDisc(tileEntityInscriber,1, 56, 53));
        this.addSlot(new SlotDisc(tileEntityInscriber,2, 116, 35));
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
    public List<Integer> getMoveSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
        if (slot.id >= 0 && slot.id <= 2) {
            return this.getSlots(0, 3, false);
        }
        if (action == InventoryAction.MOVE_SIMILAR) {
            return this.getSlots(3, 36, false);
        }
        if (action == InventoryAction.MOVE_ALL) {
            if (slot.id < 27) {
                return this.getSlots(3, 27, false);
            }
            return this.getSlots(30, 9, false);
        }
        return null;
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
        if (slot.id >= 0 && slot.id <= 2) {
            return this.getSlots(3, 36, false);
        }
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(2);
        return list;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityplayer) {
        return this.tileEntity.canInteractWith(entityplayer);
    }
}
