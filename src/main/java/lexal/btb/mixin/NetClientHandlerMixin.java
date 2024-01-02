package lexal.btb.mixin;

import lexal.btb.BTBTA;
import lexal.btb.block.tile_entity.TileEntityInscriber;
import lexal.btb.entity.IPlayerDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.net.handler.NetClientHandler;
import net.minecraft.core.net.packet.Packet100OpenWindow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value= NetClientHandler.class,remap = false)
public abstract class NetClientHandlerMixin {

    @Final
    @Shadow
    private Minecraft mc;

    @Inject(method="handleOpenWindow",at=@At("TAIL"))
    public void inject(Packet100OpenWindow packet100openwindow, CallbackInfo ci) {
        if (packet100openwindow.inventoryType == BTBTA.GUI_ID_INSCRIBER) {
            TileEntityInscriber tileEntityInscriber = new TileEntityInscriber();
            ((IPlayerDisplay)(mc.thePlayer)).displayGUIInscriber(tileEntityInscriber);
            this.mc.thePlayer.craftingInventory.windowId = packet100openwindow.windowId;
        }
    }
}