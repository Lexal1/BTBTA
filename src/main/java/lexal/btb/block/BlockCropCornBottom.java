package lexal.btb.block;

import lexal.btb.BTBTA;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockCrops;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.TextureHelper;


import java.util.Random;


public class BlockCropCornBottom extends BlockCrops {
    int[] stage0 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage0.png");
    int[] stage1 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage1_bottom.png");
    int[] stage2 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage2_bottom.png");
    int[] stage3 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage3_bottom.png");
    int[] stage4 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage4_bottom.png");

    public final int[] growthStageTextures = new int[]{texCoordToIndex(stage0[0], stage0[1]), texCoordToIndex(stage1[0], stage1[1]), texCoordToIndex(stage2[0], stage2[1]), texCoordToIndex(stage3[0], stage3[1]),texCoordToIndex(stage4[0], stage4[1])};

    public BlockCropCornBottom(String key, int id) {
        super(key, id);
        this.setTickOnLoad(true);
        this.setBlockBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.5F, 0.6875F);
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
            f *= world.seasonManager.getCurrentSeason().cropGrowthFactor;
        }
        return f;
    }
    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        if(world.getBlockMetadata(x, y, z) == 0){
            return true;
        }
        if(world.getBlockId(x, y+1, z) == ModBlocks.cornCropTop.id && world.getBlockMetadata(x,y,z) >= 0){
            return true;
        }
        return false;
    }
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        if (world.getBlockLightValue(x, y + 1, z) >= 9) {

            int blockMetadata = world.getBlockMetadata(x, y, z);
            float f = this.getGrowthRate(world, x, y, z);
            if (blockMetadata > 0) {
                if (rand.nextInt((int) (100.0F / f)) == 0) {
                    ++blockMetadata;
                    world.setBlockMetadataWithNotify(x, y, z, blockMetadata);
                    world.setBlockMetadataWithNotify(x, y + 1, z, world.getBlockMetadata(x, y+1, z)+1);
                }
            }
            if (blockMetadata == 0) {
                if (rand.nextInt((int) (100.0F / f)) == 0) {
                    ++blockMetadata;
                    world.setBlockMetadataWithNotify(x, y, z, blockMetadata);
                    world.setBlockAndMetadataWithNotify(x, y + 1, z, ModBlocks.cornCropTop.id, 0);
                }
            }
        }
    }


    public void fertilize(World world, int i, int j, int k) {
        world.setBlockMetadataWithNotify(i, j, k,1);
        world.setBlockAndMetadataWithNotify(i, j+1, k, ModBlocks.cornCropTop.id,1);
    }
    @Override
    public int getBlockTextureFromSideAndMetadata(Side side, int meta) {
        if (meta < 0 || meta > 4) {
            meta = 4;
        }
        return this.growthStageTextures[meta];
    }

    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return meta != 4 ? new ItemStack[]{new ItemStack(ModItems.cornSeeds)} : new ItemStack[]{new ItemStack(ModItems.cornSeeds, world.rand.nextInt(3) + 1), new ItemStack(ModItems.corn, world.rand.nextInt(2) + 1)};
    }
}
