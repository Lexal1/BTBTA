package lexal.btb.block;

import net.minecraft.core.block.BlockLog;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class BlockBirchLog extends BlockLog {
    public BlockBirchLog() {
        super("log.birch", 282);
        setTickOnLoad(true);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (world.seasonManager.getCurrentSeason() != null && world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
            if (rand.nextInt(32) == 0){
                world.setBlockAndMetadataWithNotify(x,y,z, ModBlocks.birchSyrupLog.id, world.getBlockMetadata(x,y,z));
            }
        }
    }
}
