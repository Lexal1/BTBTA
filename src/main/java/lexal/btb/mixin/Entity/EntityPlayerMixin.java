package lexal.btb.mixin.Entity;

import lexal.btb.ModAchievements;
import lexal.btb.block.TileEntityInscriber;
import lexal.btb.entity.IPlayerDisplay;
import net.minecraft.core.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin implements IPlayerDisplay {
    @Unique
    private EntityPlayer thisAs = (EntityPlayer)(Object)this;
    @Inject(method = "tick()V", at = @At("TAIL"))
    private void addAchievement(CallbackInfo ci){
        if (thisAs.world.getWorldTime() / thisAs.world.worldType.getDayNightCycleLengthTicks() >= 100){
            thisAs.triggerAchievement(ModAchievements.DAYS);
        }
    }
    @Override
    public void displayGUIInscriber(TileEntityInscriber tileEntityInscriber) {

    }
}
