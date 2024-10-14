package lexal.btb.modmodule;

import lexal.btb.BTBTA;
import lexal.btb.block.BTBBlocks;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;
import useless.terrainapi.api.TerrainAPI;
import useless.terrainapi.generation.overworld.api.ChunkDecoratorOverworldAPI;

public class TerrainAPIPlugin implements TerrainAPI {
    @Override
    public String getModID() {
        return BTBTA.MOD_ID;
    }

    @Override
    public void onInitialize() {
        ChunkDecoratorOverworldAPI.randomFeatures.addFeature(new WorldFeatureFlowers(BTBBlocks.blueRose.id), 6, -1, 1,
                new Biome[]{Biomes.OVERWORLD_SWAMPLAND, Biomes.OVERWORLD_SWAMPLAND_MUDDY, Biomes.PARADISE_PARADISE, Biomes.OVERWORLD_RETRO, Biomes.OVERWORLD_MEADOW});
    }
}
