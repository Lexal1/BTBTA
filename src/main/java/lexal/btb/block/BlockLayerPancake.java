package lexal.btb.block;

import lexal.btb.BTBTA;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLayerBase;
import net.minecraft.core.block.BlockLeavesBase;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Objects;

public class BlockLayerPancake extends BlockLayerBase {
    public BlockLayerPancake(String key, int id, Material material) {
        super(key, id, material);
    }
    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (dropCause == EnumDropCause.PICK_BLOCK ) {
            return new ItemStack[]{new ItemStack(ModItems.pancake)}; // Get singular pancake from pick block
        }
        return new ItemStack[]{new ItemStack(ModItems.pancake, meta+1)}; // Return all stacked pancakes
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
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.syrupJar){
            world.setBlockAndMetadataWithNotify(x,y,z, ModBlocks.layerPancakeSyrup.id,world.getBlockMetadata(x,y,z));
            player.getHeldItem().consumeItem(player);
            player.inventory.insertItem(new ItemStack(Item.jar), true);
        }
        if (player.getHeldItem() != null || player.isSneaking()) {return false;}// not empty hand or sneaking then do nothing
    this.eatPancakeLayer(world, x, y, z, player); // Eat layer
    return true;
    }

    private void eatPancakeLayer(World world, int i, int j, int k, EntityPlayer entityplayer) {
        if (entityplayer.getHealth() < 20) { // Not max health
            entityplayer.heal(3); // heal 1 1/2 hearts
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
