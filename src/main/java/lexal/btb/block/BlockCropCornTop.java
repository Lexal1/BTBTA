package lexal.btb.block;

import net.minecraft.core.block.BlockCropsWheat;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;


public class BlockCropCornTop extends BlockCropsWheat {
    public BlockCropCornTop(String key, int id) {
        super(key, id);
        this.setTicking(true);
        this.setBlockBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.5F, 0.6875F);
    }
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
    }
    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return (world.getBlockId(x, y-1, z) == BTBBlocks.cropsCornBottom.id);
    }
    @Override
    public boolean canThisPlantGrowOnThisBlockID(int i) {
        return i == BTBBlocks.cropsCornBottom.id;
    }
    public void fertilize(World world, int i, int j, int k) {
        world.setBlockAndMetadataWithNotify(i, j, k, this.id,4);
    }

    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {return null;}
}
