package lexal.btb.mixin;

import lexal.btb.world.BiomeGlacier;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Biomes.class,remap = false)
public class BiomesMixin {
    @Final
    @Shadow
    public static final Biome OVERWORLD_GLACIER = Biomes.register("minecraft:overworld.glacier", new BiomeGlacier().setColor(16772499).setSurfaceSnow().setTopBlock(Block.blockSnow.id).setFillerBlock(Block.blockSnow.id));
}
