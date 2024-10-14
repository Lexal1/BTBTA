package lexal.btb.block;

import net.minecraft.core.block.BlockGlass;
import net.minecraft.core.block.material.Material;

public class BlockGlassFramed extends BlockGlass {

    public BlockGlassFramed(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }
}
