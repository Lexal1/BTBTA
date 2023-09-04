package lexal.btb.mixin.core.entity;

import lexal.btb.item.ModItems;
import lexal.btb.world.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import lexal.btb.BTBTA;

@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin extends EntityLiving {
    @Shadow
    public int dimension;

    public EntityPlayerMixin(World world) {
        super(world);
    }

    @Inject(method = "canBreatheUnderwater()Z", at = @At("HEAD"), cancellable = true)
    public void breathingMixin(CallbackInfoReturnable<Boolean> cir) {
        EntityPlayer player = (EntityPlayer)(Object)this;
        ItemStack headSlotItem = player.inventory.armorItemInSlot(3);
        ItemStack chestSlotItem = player.inventory.armorItemInSlot(2);
        ItemStack legsSlotItem = player.inventory.armorItemInSlot(1);
        ItemStack bootsSlotItem = player.inventory.armorItemInSlot(0);
        if (
                headSlotItem!= null && headSlotItem.getItem() == ModItems.armorHelmetSpace
                && chestSlotItem!= null && chestSlotItem.getItem() == ModItems.armorChestplateSpace
                && legsSlotItem!= null && legsSlotItem.getItem() == ModItems.armorLeggingsSpace
                && bootsSlotItem!= null && bootsSlotItem.getItem() == ModItems.armorBootsSpace
        ){
            cir.setReturnValue(true); // Don't suffocate with helmet on
        }
    }

    @Inject(method = "tick()V", at = @At("TAIL"))
    private void moonThreshold(CallbackInfo ci){
        if (this.y > this.world.getWorldType().getMaxY()+5 && !world.isDaytime()){

            int targetDim = ModDimensions.dimensionMoon.id;
            Minecraft mc = Minecraft.getMinecraft(this);

            if (this.dimension == targetDim) {
                mc.usePortal(0);
            } else {
                mc.usePortal(targetDim);
            }
        }
    }

}
