package lexal.btb.mixin.Item;

import lexal.btb.BTBClientContainer;
import lexal.btb.ModAchievements;
import lexal.btb.block.ModBlocks;
import lexal.btb.item.ModItems;
import net.minecraft.core.HitResult;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemJar;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemJar.class,remap = false)
public class ItemJarMixin {
    @Inject(method = "onUseItem(Lnet/minecraft/core/item/ItemStack;Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/EntityPlayer;)Lnet/minecraft/core/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void extraJarActions(ItemStack itemstack, World world, EntityPlayer entityplayer, CallbackInfoReturnable<ItemStack> cir){
        HitResult objectMouseOver = BTBClientContainer.getMouseOver();
        if (objectMouseOver != null && objectMouseOver.hitType == HitResult.HitType.TILE) {
            int blockX = objectMouseOver.x;
            int blockY = objectMouseOver.y;
            int blockZ = objectMouseOver.z;
            if (world.getBlockId(blockX, blockY, blockZ) == ModBlocks.birchSyrupLog.id) {
                world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, Block.logBirch.id, world.getBlockMetadata(blockX, blockY, blockZ));
                itemstack.consumeItem(entityplayer);
                entityplayer.inventory.insertItem(new ItemStack(ModItems.syrupJar), true);
                entityplayer.triggerAchievement(ModAchievements.SYRUP);
                cir.setReturnValue(itemstack);
            }
        }
    }
}
