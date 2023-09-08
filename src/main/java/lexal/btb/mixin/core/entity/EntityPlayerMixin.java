package lexal.btb.mixin.core.entity;

import lexal.btb.block.ModMaterials;
import lexal.btb.item.ModItemTags;
import lexal.btb.item.ModItems;
import lexal.btb.world.ModDimensions;
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

@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin extends EntityLiving {
    @Shadow
    public int dimension;
    @Unique
    private long timeOfLastDimensionShift = 0;

    public EntityPlayerMixin(World world) {
        super(world);
    }

    @Inject(method = "canBreatheUnderwater()Z", at = @At("HEAD"), cancellable = true)
    public void breathingMixin(CallbackInfoReturnable<Boolean> cir) {
        EntityPlayer player = (EntityPlayer)(Object)this;
        if (player.isUnderLiquid(ModMaterials.gas)){
            cir.setReturnValue(true);
        }
        ItemStack headSlotItem = player.inventory.armorItemInSlot(3);
        ItemStack chestSlotItem = player.inventory.armorItemInSlot(2);
        ItemStack legsSlotItem = player.inventory.armorItemInSlot(1);
        ItemStack bootsSlotItem = player.inventory.armorItemInSlot(0);
        if (
                headSlotItem!= null && headSlotItem.getItem().hasTag(ModItemTags.breathable)
                && chestSlotItem!= null && chestSlotItem.getItem().hasTag(ModItemTags.breathable)
                && legsSlotItem!= null && legsSlotItem.getItem().hasTag(ModItemTags.breathable)
                && bootsSlotItem!= null && bootsSlotItem.getItem().hasTag(ModItemTags.breathable)
        ){
            cir.setReturnValue(true); // Don't suffocate with helmet on
        }
    }

    @Inject(method = "tick()V", at = @At("TAIL"))
    private void moonThreshold(CallbackInfo ci){
        int delayMillis = 8000;
        long currentTime = System.currentTimeMillis();
        if (this.y > this.world.getWorldType().getMaxY()+5 && !world.isDaytime() && (currentTime-timeOfLastDimensionShift > delayMillis)){
            int targetDim = ModDimensions.dimensionMoon.id;

            if (this.dimension == targetDim) {
                ModDimensions.dimensionShift(0);
            } else {
                ModDimensions.dimensionShift(targetDim);
                this.yd = 0;
            }
            timeOfLastDimensionShift = currentTime;
        }
    }

}
