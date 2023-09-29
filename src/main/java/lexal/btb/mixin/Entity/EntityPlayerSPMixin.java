package lexal.btb.mixin.Entity;

import lexal.btb.block.TileEntityInscriber;
import lexal.btb.entity.IPlayerDisplay;
import lexal.btb.gui.GuiInscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = EntityPlayerSP.class, remap = false)
public class EntityPlayerSPMixin implements IPlayerDisplay {
    @Unique
    private final Minecraft mc = Minecraft.getMinecraft(this);
    @Unique
    private final EntityPlayerSP thisAs = (EntityPlayerSP)(Object)this;
    @Override
    public void displayGUIInscriber(TileEntityInscriber tileEntityInscriber) {
        mc.displayGuiScreen(new GuiInscriber(thisAs, tileEntityInscriber));
    }
}
