package lexal.btb.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockFenceChainlink;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockPaperWall extends BlockFenceChainlink {
    public BlockPaperWall(String key, int id, Material material) {
        super(key, id, material);
    }
    public boolean canConnectTo(WorldSource iblockaccess, int x, int y, int z) {
        int l = iblockaccess.getBlockId(x, y, z);
        return Block.hasTag(l, BlockTags.MINEABLE_BY_AXE) || Block.blocksList[l] != null && (Block.blocksList[l].blockMaterial == Material.wood || Block.blocksList[l].blockMaterial == Material.cloth);
    }
    public boolean isClimbable(World world, int x, int y, int z) {
        return false;
    }
}
