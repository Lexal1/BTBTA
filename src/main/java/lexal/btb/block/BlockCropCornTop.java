package lexal.btb.block;

import lexal.btb.BTBTA;
import net.minecraft.core.block.BlockCropsWheat;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.Random;


public class BlockCropCornTop extends BlockCropsWheat {
    int[] stage0 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage1_top.png");
    int[] stage1 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage2_top.png");
    int[] stage2 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage3_top.png");
    int[] stage3 = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "corn_stage4_top.png");

    public final int[] growthStageTextures = new int[]{texCoordToIndex(stage0[0], stage0[1]), texCoordToIndex(stage1[0], stage1[1]), texCoordToIndex(stage2[0], stage2[1]), texCoordToIndex(stage3[0], stage3[1])};

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
        return (world.getBlockId(x, y-1, z) == ModBlocks.cornCropBottom.id);
    }
    @Override
    public boolean canThisPlantGrowOnThisBlockID(int i) {
        return i == ModBlocks.cornCropBottom.id;
    }
    public void fertilize(World world, int i, int j, int k) {
        world.setBlockAndMetadataWithNotify(i, j, k, this.id,4);
    }
    @Override
    public int getBlockTextureFromSideAndMetadata(Side side, int j) {
        if (j < 0 || j > 3) {
            j = 3;
        }
        return this.growthStageTextures[j];
    }
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {return null;}
}
