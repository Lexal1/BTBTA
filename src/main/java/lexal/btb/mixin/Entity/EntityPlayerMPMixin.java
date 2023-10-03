package lexal.btb.mixin.Entity;

import lexal.btb.BTBTA;
import lexal.btb.entity.tile_entity.TileEntityInscriber;
import lexal.btb.entity.IPlayerDisplay;
import lexal.btb.gui.container.ContainerInscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.core.net.packet.Packet100OpenWindow;
import net.minecraft.server.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
@Mixin(value = EntityPlayerMP.class, remap = false)
public abstract class EntityPlayerMPMixin implements IPlayerDisplay {
    @Unique
    private final Minecraft mc = Minecraft.getMinecraft(this);
    @Unique
    private final EntityPlayerMP thisAs = (EntityPlayerMP)(Object)this;
    @Shadow
    private void getNextWindowId() {}
    @Shadow
    private int currentWindowId = 0;
    @Override
    public void displayGUIInscriber(TileEntityInscriber tileEntityInscriber) {
        getNextWindowId();
        thisAs.playerNetServerHandler.sendPacket(new Packet100OpenWindow(currentWindowId, BTBTA.GUI_ID_INSCRIBER, tileEntityInscriber.getInvName(), tileEntityInscriber.getSizeInventory()));
        thisAs.craftingInventory = new ContainerInscriber(thisAs.inventory, tileEntityInscriber);
        thisAs.craftingInventory.windowId = currentWindowId;
        thisAs.craftingInventory.onContainerInit(thisAs);
    }
}
