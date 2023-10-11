package lexal.btb.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLog;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Seasons;

import java.util.Random;

public class BlockBirchLog extends BlockLog {
    private boolean isSyrup;
    public BlockBirchLog(String key, int id,boolean isSyrup) {
        super(key, id);
        this.isSyrup = isSyrup;
        setTickOnLoad(true);
    }
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        switch (dropCause){
            case SILK_TOUCH:
                return new ItemStack[]{new ItemStack(this)};
        }
        return new ItemStack[]{new ItemStack(Block.logBirch)};
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!isSyrup){
            if (world.seasonManager.getCurrentSeason() != null && world.seasonManager.getCurrentSeason() == Seasons.OVERWORLD_FALL) {
                if (rand.nextInt(32) == 0){
                    world.setBlockAndMetadataWithNotify(x,y,z, ModBlocks.birchSyrupLog.id, world.getBlockMetadata(x,y,z));
                }
            }
        }
    }
}
