package lexal.btb.mixin.core;

import lexal.btb.world.worldType.WorldTypeMoon;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.biome.provider.BiomeProvider;
import net.minecraft.core.world.type.WorldType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = World.class, remap = false)
public abstract class WorldMixin implements WorldSource {

    @Shadow public abstract WorldType getWorldType();

    @Shadow @Final public WorldType worldType;

    @Inject(method = "getStarBrightness(F)F", at = @At(value = "HEAD"), cancellable = true)
    public void renderStarsOnMood(float renderPartialTicks, CallbackInfoReturnable<Float> cir){
        if (this.getWorldType() instanceof WorldTypeMoon){
            cir.setReturnValue(0.75f);
        }
    }

    @Shadow
    public int getBlockId(int i, int j, int k) {
        return 0;
    }

    @Shadow
    public Block getBlock(int i, int j, int k) {
        return null;
    }

    @Shadow
    public TileEntity getBlockTileEntity(int i, int j, int k) {
        return null;
    }

    @Shadow
    public float getBrightness(int i, int j, int k, int l) {
        return 0;
    }

    @Shadow
    public float getLightBrightness(int i, int j, int k) {
        return 0;
    }

    @Shadow
    public int getBlockMetadata(int i, int j, int k) {
        return 0;
    }

    @Shadow
    public Material getBlockMaterial(int i, int j, int k) {
        return null;
    }

    @Shadow
    public boolean isBlockOpaqueCube(int i, int j, int k) {
        return false;
    }

    @Shadow
    public boolean isBlockNormalCube(int i, int j, int k) {
        return false;
    }

    @Shadow
    public BiomeProvider getBiomeProvider() {
        return null;
    }
}
