package lexal.btb.block;

import lexal.btb.BTBTA;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockCrops;
import net.minecraft.core.block.BlockFlower;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;

public class BlockCropCorn extends BlockFlower {
    int[] stage0 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage0.png");
    int[] stage1 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage1.png");
    int[] stage2 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage2.png");
    int[] stage3 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage3.png");
    int[] stage4 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage4.png");

    public final int[] growthStageTextures = new int[]{texCoordToIndex(stage0[0], stage0[1]), texCoordToIndex(stage1[0], stage1[1]), texCoordToIndex(stage2[0], stage2[1]), texCoordToIndex(stage3[0], stage3[1]),texCoordToIndex(stage4[0], stage4[1])};

    public BlockCropCorn(String key, int id) {
        super(key, id);
        this.setTickOnLoad(true);
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
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        if (world.getBlockLightValue(x, y + 1, z) >= 9) {
            int l = world.getBlockMetadata(x, y, z);
            if (l < 5) {
                float f = this.getGrowthRate(world, x, y, z);
                if (rand.nextInt((int)(100.0F / f)) == 0) {
                    ++l;
                    world.setBlockMetadataWithNotify(x, y, z, l);
                }
            }
        }
    }
    public boolean canThisPlantGrowOnThisBlockID(int i) {
        return i == Block.farmlandDirt.id;
    }

    public void fertilize(World world, int i, int j, int k) {
        world.setBlockAndMetadataWithNotify(i, j, k, this.id,5);
    }
    @Override
    public int getBlockTextureFromSideAndMetadata(Side side, int j) {
        if (j < 0 || j > 5) {
            j = 5;
        }
        return this.growthStageTextures[j];
    }

    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return meta != 5 ? new ItemStack[]{new ItemStack(ModItems.cornSeeds)} : new ItemStack[]{new ItemStack(ModItems.cornSeeds, world.rand.nextInt(3) + 1), new ItemStack(ModItems.corn, world.rand.nextInt(2) + 1)};
    }
}
