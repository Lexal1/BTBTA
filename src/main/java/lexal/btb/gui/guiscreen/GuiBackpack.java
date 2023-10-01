package lexal.btb.gui.guiscreen;

import lexal.btb.BTBTA;
import lexal.btb.gui.container.ContainerBackpack;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;

public class GuiBackpack extends GuiContainer {
    private final ItemStack itemStack;
    private int GUIx;
    private int GUIy;
    private ContainerBackpack backpack;
    public GuiBackpack(EntityPlayer player, ItemStack stack) {
        super(new ContainerBackpack(player.inventory, stack));
        backpack = (ContainerBackpack)inventorySlots;
        itemStack = stack;
    }

    @Override
    public void initGui() {
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;
        super.initGui();
    }
    @Override
    protected void drawGuiContainerForegroundLayer() {
        this.fontRenderer.drawString(backpack.backpackInventory.getInvName(), 8, 16, BTBTA.GUI_LABEL_COLOR);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, BTBTA.GUI_LABEL_COLOR);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/assets/btb/gui/backpack.png"));
        drawTexturedModalRect(GUIx, GUIy, 0, 0, this.xSize, this.ySize);
    }
}
