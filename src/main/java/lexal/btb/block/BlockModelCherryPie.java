package lexal.btb.block;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockEdible;
import net.minecraft.core.util.helper.Side;

public class BlockModelCherryPie<T extends Block>
        extends BlockModelStandard<T> {
    protected IconCoordinate insideTexture = TextureRegistry.getTexture("btb:block/cherry_pie_inside");
    public int maxSlices;

    public BlockModelCherryPie(Block block) {
        super(block);
        float f = 0.0625f;
        float f1 = 0.375f;
        this.withCustomItemBounds(f, 0.0, f, 1.0f - f, f1, 1.0f - f);
    }

    public void renderSliceSide(Tessellator tessellator, Block block, int x, int y, int z, Side side, boolean overrideTex) {
        if (overrideTex) {
            net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.overrideBlockTexture = this.insideTexture;
        }
        this.renderSide(tessellator, block, x, y, z, side, 0);
        if (overrideTex) {
            net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.overrideBlockTexture = null;
        }
    }

    private void renderSlice(Tessellator tessellator, Block block, int x, int y, int z, int sliceX, int sliceZ) {
        double onePix = 0.0625;
        double sliceWidth = onePix * 8.0;
        double xMin = 0.0 + sliceWidth * (double)sliceX;
        double xMax = xMin + sliceWidth;
        double zMin = 0.0 + sliceWidth * (double)sliceZ;
        double zMax = zMin + sliceWidth;
        double offsetXMin = sliceX == 0 ? onePix : 0.0;
        double offsetXMax = sliceX == 1 ? onePix : 0.0;
        double offsetZMin = sliceZ == 0 ? onePix : 0.0;
        double offsetZMax = sliceZ == 1 ? onePix : 0.0;
        boolean insideSouth = sliceZ == 0;
        boolean insideNorth = sliceZ == 1;
        boolean insideEast = sliceX == 0;
        boolean insideWest = sliceX == 1;
        this.maxSlices = ((BlockEdible)block).maxBites;
        block.setBlockBounds(xMin + offsetXMin, 0.0, zMin + offsetZMin, xMax - offsetXMax, 0.375, zMax - offsetZMax);
        this.renderSide(tessellator, block, x, y, z, Side.TOP, 0);
        this.renderSide(tessellator, block, x, y, z, Side.BOTTOM, 0);
        this.renderSliceSide(tessellator, block, x, y, z, Side.SOUTH, insideSouth);
        this.renderSliceSide(tessellator, block, x, y, z, Side.WEST, insideWest);
        net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.flipTexture = true;
        this.renderSliceSide(tessellator, block, x, y, z, Side.NORTH, insideNorth);
        this.renderSliceSide(tessellator, block, x, y, z, Side.EAST, insideEast);
        net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.flipTexture = false;
    }

    @Override
    public boolean render(Tessellator tessellator, int x, int y, int z) {
        this.block.setBlockBoundsBasedOnState(net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.blockAccess, x, y, z);
        int meta = net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.blockAccess.getBlockMetadata(x, y, z);
        net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.enableAO = true;
        net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.cache.setupCache(this.block, net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.blockAccess, x, y, z);
        int slices = 0;
        for (int xSlice = 0; xSlice < 2; ++xSlice) {
            for (int zSlice = 0; zSlice < 2; ++zSlice) {
                if (meta >= ++slices) continue;
                this.renderSlice(tessellator, this.block, x, y, z, xSlice, zSlice);
            }
        }
        net.minecraft.client.render.block.model.BlockModelPumpkinPie.renderBlocks.enableAO = false;
        return true;
    }
}