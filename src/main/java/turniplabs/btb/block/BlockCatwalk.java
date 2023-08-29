package turniplabs.btb.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;

public class BlockCatwalk extends Block {
    public BlockCatwalk(String key, int id, Material material) {
        super(key, id, material);
        setBlockBounds(0.0f, 0.9f, 0.0f, 1.0f, 1.0f, 1.0f);

    }
    public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean renderAsNormalBlock()
    {
        return false;
    }

}
