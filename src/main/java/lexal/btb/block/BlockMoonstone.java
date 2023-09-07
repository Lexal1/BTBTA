package lexal.btb.block;

import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import lexal.btb.BTBTA;

public class BlockMoonstone extends Block {
    public BlockMoonstone(String key, int id, Material material) {
        super(key, id, material);
    }
    /*@Override
    public void onBlockAdded(World world, int i, int j, int k) {
        if (world.getBlockId(i, j - 1, k) == ModBlocks.ironplating.id && ((BlockPortal)ModBlocks.portalmoon).tryToCreatePortal(world, i, j, k)) {
        }
    }*/
    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        switch (dropCause) {
            case WORLD:
            case EXPLOSION:
            case PROPER_TOOL:
                return new ItemStack[]{new ItemStack(ModBlocks.mooncobblestone)};
            case PICK_BLOCK:
            case SILK_TOUCH:
                return new ItemStack[]{new ItemStack(this)};
            default:
                return null;
        }
    }
}
