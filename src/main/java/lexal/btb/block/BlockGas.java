package lexal.btb.block;

import lexal.btb.ModMaterials;
import net.minecraft.core.block.BlockFluid;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockGas extends BlockFluid {
    public BlockGas(String key, int id) {
        super(key, id, ModMaterials.gas);
    }
    @Override
    public int tickRate() {
        return 0;
    }
    public static float getPercentAir(int meta) {
        if (meta >= 15) {
            meta = 15;
        }
        return (float)(meta + 1) / 16.0f;
    }
    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }
    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1/16f, 1.0f);
    }
    @Override
    public boolean shouldSideBeRendered(WorldSource blockAccess, int x, int y, int z, int side, int meta) {
        return true;
        /*if (blockAccess.getBlockId(x, y, z) == this.id && blockAccess.getBlockMetadata(x, y, z) == meta) {
            return false;
        }
        return super.shouldSideBeRendered(blockAccess, x, y, z, side);*/
    }
    @Override
    public void onBlockAdded(World world, int i, int j, int k) {
        world.scheduleBlockUpdate(i, j, k, this.id, this.tickRate());
    }
    @Override
    public void setBlockBoundsBasedOnState(World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z) & 0b11111;
        float f = (float)((1 + l)) / 16.0f;
        f = Math.min(f, 1);
        f = Math.max(f, 0);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, f, 1.0f);
    }

    @Override
    public boolean shouldSideBeRendered(WorldSource blockAccess, int x, int y, int z, int side) {
        return true;
        //return super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }
}
