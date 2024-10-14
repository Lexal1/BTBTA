package lexal.btb.gui;

import lexal.btb.item.BTBItems;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.slot.Slot;

public class SlotDiscBlank extends Slot {
    public SlotDiscBlank(IInventory inventory, int id, int x, int y) {
        super(inventory, id, x, y);
    }

    @Override
    public boolean canPutStackInSlot(ItemStack itemstack) {
        return itemstack != null && itemstack.itemID == BTBItems.recordBlank.id;
    }
    @Override
    public boolean allowItemInteraction() {
        return false;
    }
}
