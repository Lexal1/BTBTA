package lexal.btb.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;

public class BlockModelPie<T extends Block> extends BlockModelStandard<T> {
    IconCoordinate other = TextureRegistry.getTexture("btb:block/cherry_pie_side");
    public BlockModelPie(Block block) {
        super(block);
    }

    @Override
    public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
        return data > 0 && side == Side.WEST ? other : atlasIndices[side.getId()];
    }
}
