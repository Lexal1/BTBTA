package lexal.btb.gui;

import lexal.btb.block.TileEntityInscriber;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import org.lwjgl.opengl.GL11;

public class GuiInscriber extends GuiContainer {
    private static int GUIx;
    private static int GUIy;
    private final TileEntityInscriber tileEntityInscriber;
    public GuiInscriber(EntityPlayer player, TileEntityInscriber tileEntityInscriber) {
        super(new ContainerInscriber(player.inventory, tileEntityInscriber));
        this.tileEntityInscriber = tileEntityInscriber;
    }
    public void initGui() {
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int i = this.mc.renderEngine.getTexture("/assets/btb/gui/inscriber.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.bindTexture(i);
        drawTexturedModalRect(GUIx, GUIy, 0, 0, this.xSize, this.ySize);

        if (tileEntityInscriber.canInscribe()){
            drawTexturedModalRect(GUIx + 78, GUIy + 35, 176, 0, 25, 16);
        }
        int arrowheight = tileEntityInscriber.getInscribeProgressScaled(15);
        drawTexturedModalRect(GUIx + 58, GUIy + 36, 176, 16, 12, arrowheight);

    }
}
