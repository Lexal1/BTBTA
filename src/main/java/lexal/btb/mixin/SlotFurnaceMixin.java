package lexal.btb.mixin;

import lexal.btb.ModAchievements;
import lexal.btb.item.BTBItems;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.SlotFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SlotFurnace.class, remap = false)
public class SlotFurnaceMixin {
    @Shadow
    private EntityPlayer thePlayer;
    @Inject(method = "onPickupFromSlot(Lnet/minecraft/core/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/ItemStack;onCrafting(Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/EntityPlayer;)V", shift = At.Shift.AFTER))
    private void addFurnaceAchievements(ItemStack itemstack, CallbackInfo ci){
        if (itemstack.itemID == BTBItems.popcornBucket.id) {
            thePlayer.addStat(ModAchievements.POPCORN, 1);
        }
    }
}
