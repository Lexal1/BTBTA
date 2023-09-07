package lexal.btb.block;

import lexal.btb.BTBTA;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.Arrays;
import java.util.Random;

public class BlockGasFlowing extends BlockGas {
    private final int[] adjacentAir = new int[6];
    private final int[] horizontalAdjacentAir = new int[4];
    public BlockGasFlowing(String key, int id) {
        super(key, id);
    }
    private void setStill(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        world.setBlockAndMetadata(x, y, z, this.id + 1, meta);
        world.markBlocksDirty(x, y, z, x, y, z);
    }
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        if (world.getBlockId(x, y, z) == this.id) {
            world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
        }
    }
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        super.onNeighborBlockChange(world, x, y, z, blockId);
        if (blockId == Side.TOP.getId()) {
            return;
        }
        if (world.getBlockId(x, y, z) == this.id) {
            world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
        }
    }
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        gasProcessing(world, x, y, z);
    }

    private void gasProcessing(World world, int x, int y, int z){
        calculateAdjacentBlocks(world, x, y, z);
        int currentMeta = world.getBlockMetadata(x, y, z);
        int airAmount = currentMeta + 1;

        int index;

        if (combineDownwards(world, x, y, z)){
            return;
        }
        if (airAmount <= 1){
            setStill(world, x, y, z);
            return;
        }
        if (spreadHorizontally(world, x, y, z)){
            world.scheduleBlockUpdate(x, y, z, this.id, this.tickRate());
            return;
        }
        setStill(world, x, y, z);

    }
    private boolean combineDownwards(World world, int x, int y, int z){
        int currentMeta = world.getBlockMetadata(x, y, z);
        if(adjacentAir[5] <= 16 && adjacentAir[5] != -1){
            if (adjacentAir[5] == 0){
                world.setBlockAndMetadata(x, y-1, z, ModBlocks.gasAirFlowing.id, currentMeta);
            } else {
                world.setBlockMetadata(x, y-1, z, currentMeta + world.getBlockMetadata(x,y-1,z) + 1);
            }
            world.setBlockWithNotify(x, y, z, 0);
            calculateAdjacentBlocks(world, x, y, z);
            return true;
        }
        return false;
    }

    private boolean spreadHorizontally(World world, int x, int y, int z){
        int currentMeta = world.getBlockMetadata(x, y, z);
        int airAmount = currentMeta + 1;
        int lowestIndex = getIndexOfMin(horizontalAdjacentAir);
        if (lowestIndex == -1) {return false;}
        if (horizontalAdjacentAir[lowestIndex] + 1 < airAmount){
            int x2 = x;
            int z2 = z;
            if (lowestIndex == 0){
                x2 += 1;
            } else if (lowestIndex == 1) {
                x2 -= 1;
            } else if (lowestIndex == 2) {
                z2 += 1;
            } else if (lowestIndex == 3) {
                z2 -= 1;
            }
            if (horizontalAdjacentAir[lowestIndex] == 0){ // air block
                world.setBlockWithNotify(x2, y, z2, ModBlocks.gasAirFlowing.id);
                world.setBlockMetadata(x,y,z, currentMeta-1);
            } else {
                world.setBlockMetadata(x2, y, z2, world.getBlockMetadata(x2, y, z2) + 1);
                world.setBlockMetadata(x,y,z, currentMeta-1);
            }
            world.notifyBlockChange(x,y,z, this.id);
            return true;
        }
        return false;
    }
    private void calculateAdjacentBlocks(World world, int x, int y, int z){
        int xPosId = world.getBlockId(x+1,y,z);
        int xNegId = world.getBlockId(x-1,y,z);
        int zPosId = world.getBlockId(x,y,z+1);
        int zNegId = world.getBlockId(x,y,z-1);
        int yPosId = world.getBlockId(x,y+1,z);
        int yNegId = world.getBlockId(x,y-1,z);
        int[] adjacentBlocksIds = new int[]{xPosId, xNegId, zPosId, zNegId, yPosId, yNegId};

        int xPosMeta = world.getBlockMetadata(x+1,y,z);
        int xNegMeta = world.getBlockMetadata(x-1,y,z);
        int zPosMeta = world.getBlockMetadata(x,y,z+1);
        int zNegMeta = world.getBlockMetadata(x,y,z-1);
        int yPosMeta = world.getBlockMetadata(x,y+1,z);
        int yNegMeta = world.getBlockMetadata(x,y-1,z);
        int[] adjacentBlocksMeta = new int[]{xPosMeta, xNegMeta, zPosMeta, zNegMeta, yPosMeta, yNegMeta};

        for (int i = 0; i < adjacentBlocksIds.length; i++){
            if (adjacentBlocksIds[i] == ModBlocks.gasAirFlowing.id || adjacentBlocksIds[i] == ModBlocks.gasAirStill.id){
                adjacentAir[i] = adjacentBlocksMeta[i] + 1;
                continue;
            }
            if (adjacentBlocksIds[i] == 0 || Block.blocksList[adjacentBlocksIds[i]].hasTag(ModBlockTags.GAS_DESTROYS)){
                adjacentAir[i] = 0;
                continue;
            }
            adjacentAir[i] = -1;
        }
        System.arraycopy(adjacentAir, 0, horizontalAdjacentAir, 0, 4);
    }
    private int getIndexOfMin(int[] arr){
        int minVal = -1;// = arr[0]; // take first as minVal
        int indexOfMin = -1;// = 0; //returns -1 if all elements are equal
        for(int i = 0; i < arr.length; i++){
            if (arr[i] != -1){
                minVal = arr[i];
                indexOfMin = i;
                break;
            }
        }
        if (minVal == -1) {return -1;}

        for (int i = 0; i < arr.length; i++) {
            //if current is less then minVal
            if(arr[i] < minVal && arr[i] != -1){
                minVal = arr[i]; // put it in minVal
                indexOfMin = i; // put index of current min
            }
        }
        return indexOfMin;
    }
}
