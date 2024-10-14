package lexal.btb.block;

import net.minecraft.client.render.block.model.BlockModelPumpkinPie;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;

public class BlockModelPie<T extends Block>
        extends BlockModelPumpkinPie<T> {
    protected IconCoordinate sideTexture = TextureRegistry.getTexture("minecraft:block/cherry_pie_side");
    protected IconCoordinate insideTexture = TextureRegistry.getTexture("minecraft:block/cherry_pie_inside");
    protected IconCoordinate topTexture = TextureRegistry.getTexture("minecraft:block/cherry_pie_top");
    public int maxSlices;
    public BlockModelPie(Block block) {
        super(block);
    }

}
