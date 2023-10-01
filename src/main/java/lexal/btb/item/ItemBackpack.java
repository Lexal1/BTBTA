package lexal.btb.item;

import lexal.btb.gui.guiscreen.GuiBackpack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemRecord;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemBackpack extends Item{
    public ItemBackpack(String name, int id) {
        super(name, id);
        this.maxStackSize = 1;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        Minecraft mc = Minecraft.getMinecraft(this);
        mc.displayGuiScreen(new GuiBackpack(entityplayer, itemstack));
        return itemstack;
    }
}
