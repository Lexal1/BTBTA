package lexal.btb.world.generate;

import lexal.btb.block.ModBlocks;
import lexal.btb.world.worldfeatures.WorldFeatureMoonOre;
import net.minecraft.core.block.BlockSand;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.ChunkDecorator;
import lexal.btb.BTBTA;

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
        for(max = 0; max < 10; ++max) {
            xf = x + rand.nextInt(16);
            yf = minY + rand.nextInt(rangeY - 12) + 2;
            zf = z + rand.nextInt(16);
            (new WorldFeatureMoonOre(ModBlocks.cheeseore.id, 4, false)).generate(this.world, rand, xf, yf, zf);
        }
        for(max = 0; max < 25; ++max) {
            xf = x + rand.nextInt(16);
            yf = minY + rand.nextInt(rangeY - 8) + 4;
            zf = z + rand.nextInt(16);
            (new WorldFeatureMoonOre(ModBlocks.mooniron.id, 8, false)).generate(this.world, rand, xf, yf, zf);
        }
        for(max = 0; max < 5; ++max) {
            xf = x + rand.nextInt(16);
            yf = minY + rand.nextInt(rangeY - 16);
            zf = z + rand.nextInt(16);
            (new WorldFeatureMoonOre(ModBlocks.moongold.id, 4, false)).generate(this.world, rand, xf, yf, zf);
        }
    }
}

