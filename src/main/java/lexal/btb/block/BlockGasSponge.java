package lexal.btb.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockGasSponge extends Block {
    public BlockGasSponge(String key, int id) {
        super(key, id, Material.sponge);
    }
    @Override
    public int tickRate() {
        return 30;
    }
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        suck(world,x,y,z);
    }
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world,x,y,z,rand);
        suck(world,x,y,z);
    }
    public void suck(World world, int x, int y, int z){
        int l;
        int byte0 = 2;
        if (this.inGas(world, x, y, z)) {
            for (l = x - byte0; l <= x + byte0; ++l) {
                for (int i1 = y - byte0; i1 <= y + byte0; ++i1) {
                    for (int j1 = z - byte0; j1 <= z + byte0; ++j1) {
                        if (!Block.hasTag(world.getBlockId(l, i1, j1), ModBlockTags.IS_GAS)) continue;
                        world.setBlockWithNotify(l, i1, j1, 0);
                        world.scheduleBlockUpdate(x,y,z,this.id, this.tickRate());
                    }
                }
            }
        }
    }

    public boolean inGas(World world, int x, int y, int z) {
        if (Block.hasTag(world.getBlockId(x + 1, y, z), ModBlockTags.IS_GAS)) {
            return true;
        }
        if (Block.hasTag(world.getBlockId(x - 1, y, z), ModBlockTags.IS_GAS)) {
            return true;
        }
        if (Block.hasTag(world.getBlockId(x, y + 1, z), ModBlockTags.IS_GAS)) {
            return true;
        }
        if (Block.hasTag(world.getBlockId(x, y - 1, z), ModBlockTags.IS_GAS)) {
            return true;
        }
        if (Block.hasTag(world.getBlockId(x, y, z + 1), ModBlockTags.IS_GAS)) {
            return true;
        }
        return Block.hasTag(world.getBlockId(x, y, z - 1), ModBlockTags.IS_GAS);
    }

    @Override
    public void onBlockRemoval(World world, int x, int y, int z) {
        int byte0 = 2;
        for (int l = x - byte0; l <= x + byte0; ++l) {
            for (int i1 = y - byte0; i1 <= y + byte0; ++i1) {
                for (int j1 = z - byte0; j1 <= z + byte0; ++j1) {
                    world.notifyBlocksOfNeighborChange(l, i1, j1, world.getBlockId(l, i1, j1));
                }
            }
        }
    }
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        super.onNeighborBlockChange(world, x, y, z, blockId);
        world.scheduleBlockUpdate(x,y,z,this.id,tickRate());
    }
}
