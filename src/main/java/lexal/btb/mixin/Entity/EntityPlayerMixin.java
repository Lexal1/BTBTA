package lexal.btb.mixin.Entity;

import lexal.btb.ModAchievements;
import lexal.btb.entity.tile_entity.TileEntityInscriber;
import lexal.btb.entity.IPlayerDisplay;
import net.minecraft.core.achievement.stat.StatBase;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.Dimension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin implements IPlayerDisplay {
    @Unique
    private double[] lastOverworldCoords = null;
    @Unique
    private EntityPlayer thisAs = (EntityPlayer)(Object)this;
    @Inject(method = "tick()V", at = @At("TAIL"))
    private void addAchievement(CallbackInfo ci){
        if (thisAs.world.getWorldTime() / thisAs.world.worldType.getDayNightCycleLengthTicks() >= 100){
            thisAs.triggerAchievement(ModAchievements.DAYS);
        }
        if (lastOverworldCoords != null && thisAs.distanceToSqr(lastOverworldCoords[0], lastOverworldCoords[1], lastOverworldCoords[2]) > 10000 && thisAs.dimension == Dimension.overworld.id){ // Not actually the proper way to implement this
            thisAs.triggerAchievement(ModAchievements.NETHERDIST);
        }
    }
    @Redirect(method = "attackTargetEntityWithCurrentItem(Lnet/minecraft/core/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/player/EntityPlayer;addStat(Lnet/minecraft/core/achievement/stat/StatBase;I)V"))
    private void damageAchievement(EntityPlayer instance, StatBase statbase, int i){
        instance.addStat(statbase, i);
        if (i >= 13){ // Diamond sword damage
            thisAs.triggerAchievement(ModAchievements.OVERKILL);
        }
    }
    @Inject(method = "handlePortal(I)V", at = @At("TAIL"))
    private void portal(int portalTileId, CallbackInfo ci){
        if (thisAs.dimension == Dimension.overworld.id){
            lastOverworldCoords = new double[3];
            lastOverworldCoords[0] = thisAs.x;
            lastOverworldCoords[1] = thisAs.y;
            lastOverworldCoords[2] = thisAs.z;
        }
    }
    @Override
    public void displayGUIInscriber(TileEntityInscriber tileEntityInscriber) {

    }
}
