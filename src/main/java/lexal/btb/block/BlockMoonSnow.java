package lexal.btb.block;

import lexal.btb.item.ModItems;
import net.minecraft.core.block.BlockLayerSnow;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockMoonSnow extends BlockLayerSnow {
    public BlockMoonSnow(String key, int id, Material material) {
        super(key, id, material);
    }
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        switch (dropCause) {
            case SILK_TOUCH:
                return new ItemStack[]{new ItemStack(this, meta + 1)};
            case PICK_BLOCK:
                return new ItemStack[]{new ItemStack(this)};
            case PROPER_TOOL:
                return new ItemStack[]{new ItemStack(ModItems.moondust, meta + 1)};
            default:
                return null;
        }
    }
}
