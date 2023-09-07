package lexal.btb.block;

import lexal.btb.BTBTA;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockRotatableHorizontal;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockMachinePressurizer extends BlockRotatableHorizontal {
    private boolean isActive;
    public BlockMachinePressurizer(String key, int id, Boolean isActive) {
        super(key, id, Material.metal);
        this.isActive = isActive;
    }
    @Override
    public int tickRate() {
        return 5;
    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k) {
        world.scheduleBlockUpdate(i, j, k, this.id, this.tickRate());
    }
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        checkActiveState(world, x, y, z);
        if (isActive){
            pumpGas(world, x, y, z);
        }
        world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
    }
    private void checkActiveState(World world, int x, int y, int z){
        boolean isPoweredByBlock;
        boolean bl = isPoweredByBlock = world.isBlockGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y, z);
        int meta = world.getBlockMetadata(x,y,z);
        if (this.isActive) {
            if (!isPoweredByBlock) {
                world.setBlockAndMetadataWithNotify(x, y, z, ModBlocks.machinePressurizerIdle.id, meta);
            }
        } else if (isPoweredByBlock) {
            world.setBlockAndMetadataWithNotify(x, y, z, ModBlocks.machinePressurizerActive.id, meta);
        }
    }
    private void pumpGas(World world, int x, int y, int z){
        int meta = world.getBlockMetadata(x, y, z);
        int direction = meta & 0b111;

        int xAdj = x;
        int zAdj = z;

        if (direction == 2){
            zAdj--;
        } else if (direction == 3) {
            zAdj++;
        } else if (direction == 4) {
            xAdj--;
        } else if (direction == 5) {
            xAdj++;
        }

        int blockId = world.getBlockId(xAdj, y, zAdj);
        if (blockId == 0 || (Block.blocksList[blockId].hasTag(ModBlockTags.GAS_DESTROYS) || blockId == ModBlocks.gasAirFlowing.id || blockId == ModBlocks.gasAirStill.id)){
            world.setBlockAndMetadataWithNotify(xAdj,y,zAdj, ModBlocks.gasAirFlowing.id, 31);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        checkActiveState(world, x, y, z);
    }
}
