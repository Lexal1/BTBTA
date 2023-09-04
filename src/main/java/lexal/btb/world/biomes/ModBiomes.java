package lexal.btb.world.biomes;

import lexal.btb.block.ModBlocks;
import lexal.btb.world.biomes.BiomeMoon;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;

public class ModBiomes {
    public static final Biome biomeMoon = Biomes.register("btb:moon.moon", new BiomeMoon());
    static
    {
        biomeMoon.topBlock = (short) ModBlocks.moonsnow.id;
        biomeMoon.fillerBlock = (short) ModBlocks.moonturf.id;
    }
    public static void register() {}
}
