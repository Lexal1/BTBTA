package lexal.btb.block;

import lexal.btb.entity.IPlayerDisplay;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;

public class BlockInscriber extends BlockTileEntity {
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
}
