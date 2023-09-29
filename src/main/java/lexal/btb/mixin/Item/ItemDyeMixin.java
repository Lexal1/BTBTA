package lexal.btb.mixin.Item;


import lexal.btb.block.BlockCropCornBottom;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemDye.class, remap = false)
final class ItemDyeMixin {
    @Inject(at =@At("HEAD"), method = "onItemUse", cancellable = true)
    private void injected(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir) {
        int meta = world.getBlockMetadata(blockX, blockY, blockZ);
        if (itemstack.getMetadata() == 15) {
            int id = world.getBlockId(blockX, blockY, blockZ);
            if (Block.blocksList[id] instanceof BlockCropCornBottom && meta < 5) {
                if (!world.isClientSide) {
                    ((BlockCropCornBottom) Block.blocksList[id]).fertilize(world, blockX, blockY, blockZ);
                    if (entityplayer.getGamemode().consumeBlocks) {
                        --itemstack.stackSize;
                    }
                }
                cir.setReturnValue(true);
            }
        }
    }
}
