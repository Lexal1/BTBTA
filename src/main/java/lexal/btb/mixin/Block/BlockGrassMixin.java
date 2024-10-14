package lexal.btb.mixin.Block;

import lexal.btb.block.BTBBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockGrass;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;

@Mixin(value= BlockGrass.class,remap=false)
public class BlockGrassMixin {
    @ModifyVariable(method = "updateTick", at = @At(value = "LOAD"), name = "idToSpawn")
    public int updateId(int id, World world, int x, int y, int z, Random rand) {
        if (!(id == Block.flowerRed.id && rand.nextInt(2) == 0)) return id;
        Biome biome = world.getBlockBiome(x, y, z);
        if (biome == Biomes.OVERWORLD_BOREAL_FOREST ||
                biome == Biomes.OVERWORLD_BIRCH_FOREST ||
                biome == Biomes.PARADISE_PARADISE ||
                biome == Biomes.OVERWORLD_RETRO ||
                biome == Biomes.OVERWORLD_MEADOW) {
            return BTBBlocks.blueRose.id;
        }
        return id;
    }
}