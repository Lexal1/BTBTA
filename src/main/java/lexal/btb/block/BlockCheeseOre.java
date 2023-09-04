package lexal.btb.block;

import lexal.btb.item.ModItems;
import net.minecraft.core.block.BlockOreCoal;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import lexal.btb.BTBTA;

public class BlockCheeseOre extends BlockOreCoal {
    public BlockCheeseOre(String key, int id) {
        super(key, id);
    }
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        switch (dropCause) {
            case SILK_TOUCH:
            case PICK_BLOCK:
                return new ItemStack[]{new ItemStack(this)};
            case PROPER_TOOL:
                return new ItemStack[]{new ItemStack(ModItems.cheese)};
            default:
                return null;
        }
    }
}
