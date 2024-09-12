package lexal.btb.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.block.model.BlockModelCrossedSquares;

public class BlockModelCropCorn<T extends Block> extends BlockModelCrossedSquares<T> {
    private static final IconCoordinate[] textures_b = new IconCoordinate[] {
        TextureRegistry.getTexture("btb:block/corn_stage0"),
        TextureRegistry.getTexture("btb:block/corn_stage1_bottom"),
        TextureRegistry.getTexture("btb:block/corn_stage2_bottom"),
        TextureRegistry.getTexture("btb:block/corn_stage3_bottom"),
        TextureRegistry.getTexture("btb:block/corn_stage4_bottom")
    };

    private static final IconCoordinate[] textures_t = new IconCoordinate[] {
        TextureRegistry.getTexture("btb:block/corn_stage0"),
        TextureRegistry.getTexture("btb:block/corn_stage1_top"),
        TextureRegistry.getTexture("btb:block/corn_stage2_top"),
        TextureRegistry.getTexture("btb:block/corn_stage3_top"),
        TextureRegistry.getTexture("btb:block/corn_stage4_top")
    };

    private boolean top = false;
    public BlockModelCropCorn(Block block, boolean bottom) {
        super(block);
        top = !bottom;
    }

    @Override
    public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
        if (data < 0 || data > 4) {
            data = 4;
        }
        return (top ? textures_t : textures_b)[data];
    }
}
