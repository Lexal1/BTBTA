package turniplabs.btb.world.generate;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockSand;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.ChunkDecorator;
import net.minecraft.core.world.generate.feature.WorldFeatureOre;
import turniplabs.btb.BTBTA;
import turniplabs.btb.world.worldfeatures.WorldFeatureMoonOre;

import java.util.Random;

public class ChunkDecoratorMoon implements ChunkDecorator {
    private final World world;
    public ChunkDecoratorMoon(World world) {
        this.world = world;
    }
    @Override
    public void decorate(Chunk chunk) {
        int chunkX = chunk.xPosition;
        int chunkZ = chunk.zPosition;
        int minY = this.world.getWorldType().getMinY();
        int maxY = this.world.getWorldType().getMaxY();
        int rangeY = maxY + 1 - minY;
        Random rand = new Random((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
        BlockSand.fallInstantly = true;
        int x = chunkX * 16;
        int z = chunkZ * 16;
        int max;
        int xf;
        int yf;
        int zf;
        for(max = 0; max < 100; ++max) {
            xf = x + rand.nextInt(16);
            yf = minY + rand.nextInt(rangeY - 8) + 4;
            zf = z + rand.nextInt(16);
            (new WorldFeatureMoonOre(BTBTA.cheeseore.id, 4, false)).generate(this.world, rand, xf, yf, zf);
        }
        for(max = 0; max < 100; ++max) {
            xf = x + rand.nextInt(16);
            yf = minY + rand.nextInt(rangeY - 8) + 4;
            zf = z + rand.nextInt(16);
            (new WorldFeatureMoonOre(BTBTA.mooniron.id, 8, false)).generate(this.world, rand, xf, yf, zf);
        }
        for(max = 0; max < 100; ++max) {
            xf = x + rand.nextInt(16);
            yf = minY + rand.nextInt(rangeY - 8) + 4;
            zf = z + rand.nextInt(16);
            (new WorldFeatureMoonOre(BTBTA.moongold.id, 4, false)).generate(this.world, rand, xf, yf, zf);
        }
    }
}

