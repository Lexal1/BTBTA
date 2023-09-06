package lexal.btb.block;

import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockGasStill extends BlockGas{
    public BlockGasStill(String key, int id) {
        super(key, id);
    }
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        super.onNeighborBlockChange(world, x, y, z, blockId);
        if (blockId == Side.TOP.getId()) {
            return;
        }
        if (world.getBlockId(x, y, z) == this.id) {
            this.setFlowing(world, x, y, z);
        }
    }
    private void setFlowing(World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        world.editingBlocks = true;
        world.setBlockAndMetadata(x, y, z, this.id - 1, l);
        world.markBlocksDirty(x, y, z, x, y, z);
        world.scheduleBlockUpdate(x, y, z, this.id - 1, this.tickRate());
        world.editingBlocks = false;
    }
}
