package lexal.btb.block;

import net.minecraft.core.block.BlockGlass;
import net.minecraft.core.block.BlockGlassTinted;
import net.minecraft.core.block.material.Material;

public class BlockFramedGlass extends BlockGlass {
    public BlockFramedGlass(String key, int id, Material material, boolean renderInside) {
        super(key, id, material, renderInside);
    }
    public int getRenderBlockPass() {
        return 1;
    }
}
