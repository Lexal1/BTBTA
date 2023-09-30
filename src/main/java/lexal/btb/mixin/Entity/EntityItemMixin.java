package lexal.btb.mixin.Entity;

import lexal.btb.ModAchievements;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityItem.class,remap = false)
public class EntityItemMixin {
    @Unique
    private int previousStackSize;
    @Unique
    private EntityItem thisAs = (EntityItem) (Object)this;
    @Inject(method = "playerTouch(Lnet/minecraft/core/entity/player/EntityPlayer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/player/inventory/InventoryPlayer;insertItem(Lnet/minecraft/core/item/ItemStack;Z)V", shift = At.Shift.BEFORE))
    private void stackSize(EntityPlayer player, CallbackInfo ci){
        previousStackSize = thisAs.item.stackSize;
    }
    @Inject(method = "playerTouch(Lnet/minecraft/core/entity/player/EntityPlayer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/player/inventory/InventoryPlayer;insertItem(Lnet/minecraft/core/item/ItemStack;Z)V", shift = At.Shift.AFTER))
    private void addAchievements(EntityPlayer player, CallbackInfo ci){
        if (thisAs.item.stackSize < previousStackSize) {
            boolean collectedFlowers = true;
            for (int id: ModAchievements.flowersList) {
                collectedFlowers = collectedFlowers && ((player.getStat(StatList.pickUpItemStats[id]) > 0) || thisAs.item.itemID == id);
            }
            if (collectedFlowers){
                player.triggerAchievement(ModAchievements.FLOWERS);
            }
        }
    }
}
