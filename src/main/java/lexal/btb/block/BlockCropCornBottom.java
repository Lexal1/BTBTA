package lexal.btb.block;

import lexal.btb.item.BTBItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockCropsWheat;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.*;

import java.util.Random;


public class BlockCropCornBottom extends BlockCropsWheat {
    public BlockCropCornBottom(String key, int id) {
        super(key, id);
        this.setTicking(true);
        this.setBlockBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
    }

    private float getGrowthRate(World world, int i, int j, int k) {
        float f = 0.1F;
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        int l1 = world.getBlockId(i - 1, j, k - 1);
        int i2 = world.getBlockId(i + 1, j, k - 1);
        int j2 = world.getBlockId(i + 1, j, k + 1);
        int k2 = world.getBlockId(i - 1, j, k + 1);
        boolean flag = j1 == this.id || k1 == this.id;
        boolean flag1 = l == this.id || i1 == this.id;
        boolean flag2 = l1 == this.id || i2 == this.id || j2 == this.id || k2 == this.id;

        for(int l2 = i - 1; l2 <= i + 1; ++l2) {
            for(int i3 = k - 1; i3 <= k + 1; ++i3) {
                int j3 = world.getBlockId(l2, j - 1, i3);
                float f1 = 0.0F;
                if (j3 == Block.farmlandDirt.id) {
                    f1 = 1.0F;
                    if (world.getBlockMetadata(l2, j - 1, i3) > 0) {
                        f1 = 3.0F;
                    }
                }
                if (l2 != i || i3 != k) {
                    f1 /= 4.0F;
                }
                f += f1;
            }
        }
        if (flag2 || flag && flag1) {
            f /= 2.0F;
        }
        if (world.seasonManager.getCurrentSeason() != null) {
            float growthFactor = 1f;
            Season currentSeason = world.seasonManager.getCurrentSeason();
            if (currentSeason instanceof SeasonSummer){
                growthFactor = 1F;
            } else if (currentSeason instanceof SeasonSpring) {
                growthFactor = .5F;
            } else if (currentSeason instanceof SeasonWinter){
                growthFactor = .25F;
            } else if (currentSeason instanceof SeasonFall){
                growthFactor = 1.5F;
            }
            f *= growthFactor;
        }
        return f;
    }
    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        if(world.getBlockMetadata(x, y, z) == 0){
            return super.canBlockStay(world, x ,y, z);
        }
        if(world.getBlockId(x, y+1, z) == BTBBlocks.cropsCornTop.id && world.getBlockMetadata(x,y,z) >= 0){
            return super.canBlockStay(world, x ,y, z);
        }
        return false;
    }
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (world.seasonManager.getCurrentSeason() != null && world.seasonManager.getCurrentSeason().killFlowers && this.killedByWeather && rand.nextInt(256) == 0) {
            world.setBlockWithNotify(x, y, z, 0);
        }

        if (world.getBlockLightValue(x, y + 1, z) >= 9) {
            int blockMetadata = world.getBlockMetadata(x, y, z);
            if (blockMetadata >= 4){
                return;
            }
            float f = this.getGrowthRate(world, x, y, z);
            int blockAbove = world.getBlockId(x, y+1, z);
            if ((blockAbove == 0 || blockAbove == BTBBlocks.cropsCornTop.id) && rand.nextInt((int) (100.0F / f)) == 0) {
                world.setBlockAndMetadataWithNotify(x, y + 1, z, BTBBlocks.cropsCornTop.id, blockMetadata);
                world.setBlockMetadataWithNotify(x, y, z, ++blockMetadata);

            }
        }
    }


    public void fertilize(World world, int x, int y, int z) {
        world.setBlockMetadataWithNotify(x, y, z,4);
        int blockAbove = world.getBlockId(x, y+1, z);
        if(blockAbove == 0 || blockAbove == BTBBlocks.cropsCornTop.id){
            world.setBlockMetadataWithNotify(x, y, z,4);
            world.setBlockAndMetadataWithNotify(x, y+1, z, BTBBlocks.cropsCornTop.id,4);
        }
    }

    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (meta != 4){
            return new ItemStack[]{new ItemStack(BTBItems.cornSeeds)};
        } else {
            int seedAmount = 1 + (world.rand.nextInt(5)==0 ? 1 : 0);
            return new ItemStack[]{new ItemStack(BTBItems.cornSeeds, seedAmount), new ItemStack(BTBItems.corn, world.rand.nextInt(2) + 1)};
        }
    }
}
