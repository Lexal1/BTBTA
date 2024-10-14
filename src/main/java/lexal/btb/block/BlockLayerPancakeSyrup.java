package lexal.btb.block;

import lexal.btb.item.BTBItems;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.block.BlockLayerBase;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockLayerPancakeSyrup extends BlockLayerBase {
    public BlockLayerPancakeSyrup(String key, int id, Material material) {
        super(key, id, material);
    }
    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (dropCause == EnumDropCause.PICK_BLOCK ) {
            return new ItemStack[]{new ItemStack(BTBItems.pancake)}; // Get singular pancake from pick block
        }
        return new ItemStack[]{new ItemStack(BTBItems.pancake, meta+1)}; // Return all stacked pancakes
    }
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        int l = world.getBlockId(x, y - 1, z);
        if (l == 0 || l == id) { // Cant place on pancakes or air
            return false;
        }
        Material material = world.getBlockMaterial(x, y - 1, z);
        return material == Material.leaves || material.blocksMotion();
    }
    @Override
    public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xPlaced, double yPlaced) {
        if (player.getHeldItem() != null || player.isSneaking()) {return false;} // not empty hand or sneaking then do nothing
        this.eatPancakeLayer(world, x, y, z, player); // Eat layer
        return true;
    }

    public void eatPancakeLayer(World world, int i, int j, int k, EntityPlayer entityplayer) {
        if (entityplayer.getHealth() < 20) { // Not max health
            entityplayer.heal(7); // heal 3 and 1/2 hearts
            int l = world.getBlockMetadata(i, j, k) - 1; // new layer meta data
            if (l < 0) { // if no more layers
                world.setBlockWithNotify(i, j, k, 0); // replace with air
            } else {
                world.setBlockMetadataWithNotify(i, j, k, l); // shrink layer height
                world.markBlockDirty(i, j, k);
            }
        }
    }
}
