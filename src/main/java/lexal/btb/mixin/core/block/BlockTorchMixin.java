package lexal.btb.mixin.core.block;

import lexal.btb.BTBTA;
import lexal.btb.block.ModBlocks;
import lexal.btb.world.ModDimensions;
import net.minecraft.core.block.BlockTorch;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = BlockTorch.class,remap = false)
public class BlockTorchMixin {

    @Inject(method = "randomDisplayTick(Lnet/minecraft/core/world/World;IIILjava/util/Random;)V", at = @At("HEAD"), cancellable = true)
    private void burnOut(World world, int x, int y, int z, Random rand, CallbackInfo ci){
        if (world.dimension == ModDimensions.dimensionMoon && rand.nextInt(5) == 0){
            world.setBlockWithNotify(x,y,z, ModBlocks.torchUnlit.id);
            ci.cancel();
        }
    }
}
