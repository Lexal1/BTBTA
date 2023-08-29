package turniplabs.btb.world.generate;

import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.MapGenCaves;
import net.minecraft.core.world.generate.chunk.perlin.ChunkGeneratorPerlin;
import net.minecraft.core.world.generate.chunk.perlin.nether.ChunkDecoratorNether;
import net.minecraft.core.world.generate.chunk.perlin.overworld.SurfaceGeneratorOverworld;
import net.minecraft.core.world.generate.chunk.perlin.overworld.TerrainGeneratorOverworld;
import net.minecraft.core.world.generate.chunk.perlin.overworld.retro.SurfaceGeneratorOverworldRetro;
import net.minecraft.core.world.generate.chunk.perlin.overworld.retro.TerrainGeneratorOverworldRetro;

public class ChunkGeneratorMoon extends ChunkGeneratorPerlin {
    public ChunkGeneratorMoon(World world) {
        super(world, new ChunkDecoratorMoon(world), new TerrainGeneratorOverworld(world), new SurfaceGeneratorOverworldRetro(world), new MapGenCaves(true));
    }
}
