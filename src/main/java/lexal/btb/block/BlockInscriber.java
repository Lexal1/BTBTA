package lexal.btb.block;

import lexal.btb.entity.IPlayerDisplay;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockInscriber extends BlockTileEntity {
    protected Random rand = new Random();
    public BlockInscriber(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityInscriber();
    }
    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        IPlayerDisplay playerDisplay = (IPlayerDisplay) player;
        if (!world.isClientSide) {
            TileEntityInscriber tileEntityInscriber = (TileEntityInscriber) world.getBlockTileEntity(x, y, z);
            playerDisplay.displayGUIInscriber(tileEntityInscriber);
        }
        return true;
    }
    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        switch (dropCause) {
            case PICK_BLOCK:
            case PROPER_TOOL:
            case SILK_TOUCH: {
                return new ItemStack[]{new ItemStack(ModBlocks.inscriber)};
            }
        }
        return null;
    }
    @Override
    public void onBlockRemoval(World world, int x, int y, int z) {
        TileEntityInscriber tileEntityInscriber = (TileEntityInscriber) world.getBlockTileEntity(x, y, z);
        for (int l = 0; l < tileEntityInscriber.getSizeInventory(); ++l) {
            ItemStack itemstack = tileEntityInscriber.getStackInSlot(l);
            if (itemstack == null) continue;
            float f = rand.nextFloat() * 0.8f + 0.1f;
            float f1 = rand.nextFloat() * 0.8f + 0.1f;
            float f2 = rand.nextFloat() * 0.8f + 0.1f;
            while (itemstack.stackSize > 0) {
                int i1 = rand.nextInt(21) + 10;
                if (i1 > itemstack.stackSize) {
                    i1 = itemstack.stackSize;
                }
                itemstack.stackSize -= i1;
                EntityItem entityitem = new EntityItem(world, (float)x + f, (float)y + f1, (float)z + f2, new ItemStack(itemstack.itemID, i1, itemstack.getMetadata()));
                float f3 = 0.05f;
                entityitem.xd = (float)rand.nextGaussian() * f3;
                entityitem.yd = (float)rand.nextGaussian() * f3 + 0.2f;
                entityitem.zd = (float)rand.nextGaussian() * f3;
                world.entityJoinedWorld(entityitem);
            }
        }
        super.onBlockRemoval(world, x, y, z);
    }
}
