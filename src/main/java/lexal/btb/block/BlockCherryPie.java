package lexal.btb.block;

import lexal.btb.item.BTBItems;
import net.minecraft.core.block.BlockEdible;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.WorldSource;

public class BlockCherryPie extends BlockEdible {

    public BlockCherryPie(String key, int id) {
        super(key, id, 4, 5, () -> BTBItems.foodCherryPie);
    }

    @Override
    public void setBlockBoundsBasedOnState(WorldSource world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        float f = 0.0625f;
        float xMin = l >= 2 ? 0.5f : f;
        float zMin = l >= 3 ? 0.5f : f;
        float f2 = 0.4375f;
        this.setBlockBounds(xMin, 0.0, zMin, 1.0f - f, f2, 1.0f - f);
    }

    @Override
    public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        float f = 0.0625f;
        float xMin = l >= 2 ? 0.5f : f;
        float zMin = l >= 3 ? 0.5f : f;
        float f2 = 0.4375f;
        return AABB.getBoundingBoxFromPool((float)x + xMin, y, (float)z + zMin, (float)(x + 1) - f, (float)y + f2 - f, (float)(z + 1) - f);
    }

    @Override
    public AABB getSelectedBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        float f = 0.0625f;
        float xMin = l >= 2 ? 0.5f : f;
        float zMin = l >= 3 ? 0.5f : f;
        float f2 = 0.4375f;
        return AABB.getBoundingBoxFromPool((float)x + xMin, y, (float)z + zMin, (float)(x + 1) - f, (float)y + f2 - f, (float)(z + 1) - f);
    }
}
