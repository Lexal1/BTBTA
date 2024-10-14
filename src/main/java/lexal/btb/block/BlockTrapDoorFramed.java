package lexal.btb.block;

import net.minecraft.core.block.BlockTrapDoor;
import net.minecraft.core.block.material.Material;

public class BlockTrapDoorFramed extends BlockTrapDoor {

    public BlockTrapDoorFramed(String key, int id, Material material, boolean isIron) {
        super(key, id, material, isIron);
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

}
