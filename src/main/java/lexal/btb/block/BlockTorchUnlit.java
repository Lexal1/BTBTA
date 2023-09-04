package lexal.btb.block;

import net.minecraft.core.block.BlockTorch;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockTorchUnlit extends BlockTorch {
    public BlockTorchUnlit(String key, int id) {
        super(key, id);
    }
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        return;
    }
}
