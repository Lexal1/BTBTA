package lexal.btb.gui.slots;

import lexal.btb.item.ItemBackpack;
import lexal.btb.item.ItemBackpackInventory;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;

public class SlotBackpack extends Slot {
    public SlotBackpack(ItemBackpackInventory inventory, int id, int x, int y) {
        super(inventory, id, x, y);
    }
    @Override
    public boolean canPutStackInSlot(ItemStack itemstack) {
        return itemstack.getItem() != null && !(itemstack.getItem() instanceof ItemBackpack);
    }
}
