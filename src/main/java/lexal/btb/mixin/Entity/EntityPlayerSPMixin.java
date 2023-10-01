package lexal.btb.mixin.Entity;

import lexal.btb.ModAchievements;
import lexal.btb.entity.tile_entity.TileEntityInscriber;
import lexal.btb.entity.IPlayerDisplay;
import lexal.btb.gui.guiscreen.GuiInscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.player.Session;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayerSP.class, remap = false)
public class EntityPlayerSPMixin implements IPlayerDisplay {
    @Unique
    private final Minecraft mc = Minecraft.getMinecraft(this);
    @Unique
    private final EntityPlayerSP thisAs = (EntityPlayerSP)(Object)this;
    @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Lnet/minecraft/core/world/World;Lnet/minecraft/core/player/Session;I)V", at = @At("TAIL"))
    private void init(Minecraft minecraft, World world, Session session, int i, CallbackInfo ci){
        thisAs.addStat(ModAchievements.ROOT, 1);
    }
    @Override
    public void displayGUIInscriber(TileEntityInscriber tileEntityInscriber) {
        mc.displayGuiScreen(new GuiInscriber(thisAs, tileEntityInscriber));
    }
}

