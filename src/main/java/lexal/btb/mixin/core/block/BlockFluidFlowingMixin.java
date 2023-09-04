package lexal.btb.mixin.core.block;

import lexal.btb.world.ModDimensions;
import net.minecraft.core.block.BlockFluid;
import net.minecraft.core.block.BlockFluidFlowing;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BlockFluidFlowing.class, remap = false)
public class BlockFluidFlowingMixin extends BlockFluid {
    public BlockFluidFlowingMixin(String key, int id, Material material) {
        super(key, id, material);
    }

    @Inject(method = "onBlockAdded(Lnet/minecraft/core/world/World;III)V", at = @At("HEAD"), cancellable = true)
    private void evaporate(World world, int x, int y, int z, CallbackInfo ci){
        if (this.blockMaterial == Material.water &&
                (world.dimension == Dimension.nether || world.dimension == ModDimensions.dimensionMoon)){
            world.playSoundEffect(SoundType.WORLD_SOUNDS, (double)z + 0.5, (double)y + 0.5, (double)x + 0.5, "random.fizz", 0.5f, 2.6f + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8f);
            for (int l = 0; l < 8; ++l) {
                world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 0.0, 0.0, 0.0);
            }
            world.setBlock(x,y,z, 0);
            ci.cancel();
        }
    }
}
