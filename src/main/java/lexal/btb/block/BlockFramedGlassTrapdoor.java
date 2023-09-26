package lexal.btb.block;

import net.minecraft.core.block.BlockTrapDoor;
import net.minecraft.core.block.material.Material;

public class BlockFramedGlassTrapdoor extends BlockTrapDoor {
    public BlockFramedGlassTrapdoor(String key, int id, Material material, boolean isIron) {
        super(key, id, material, isIron);
    }
    public int getRenderBlockPass() {
        return 1;
    }

}
