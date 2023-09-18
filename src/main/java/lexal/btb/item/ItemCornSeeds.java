package lexal.btb.item;

import lexal.btb.block.ModBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemCornSeeds extends Item {

    public ItemCornSeeds(String name, int id) {
        super(name, id);
        this.maxStackSize = 64;
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
        int zOffset;
        Block cropBlockTop;
        Block doorBlockBottom;
        if (!world.canPlaceInsideBlock(blockX, blockY, blockZ)) {
            blockX += side.getOffsetX();
            blockY += side.getOffsetY();
            blockZ += side.getOffsetZ();
        }
            doorBlockBottom = ModBlocks.cornCropBottom;
            cropBlockTop = ModBlocks.cornCropTop;
        if (!doorBlockBottom.canPlaceBlockAt(world, blockX, blockY, blockZ)) {
            return false;
        }
        Direction dir = entityplayer.getHorizontalPlacementDirection(side).rotate(3);
        int meta = dir.getHorizontalIndex();
        int xOffset = -dir.getOffsetX();
        int isSolidBlockLeft = (world.isBlockNormalCube(blockX - xOffset, blockY, blockZ - (zOffset = -dir.getOffsetZ())) ? 1 : 0) + (world.isBlockNormalCube(blockX - xOffset, blockY + 1, blockZ - zOffset) ? 1 : 0);
        int isSolidBlockRight = (world.isBlockNormalCube(blockX + xOffset, blockY, blockZ + zOffset) ? 1 : 0) + (world.isBlockNormalCube(blockX + xOffset, blockY + 1, blockZ + zOffset) ? 1 : 0);
        boolean isDoorLeft = world.getBlockId(blockX - xOffset, blockY, blockZ - zOffset) == doorBlockBottom.id || world.getBlockId(blockX - xOffset, blockY + 1, blockZ - zOffset) == cropBlockTop.id;
        boolean isDoorRight = world.getBlockId(blockX + xOffset, blockY, blockZ + zOffset) == doorBlockBottom.id || world.getBlockId(blockX + xOffset, blockY + 1, blockZ + zOffset) == cropBlockTop.id;
        boolean isMirrored = false;
        if (isDoorLeft && !isDoorRight) {
            isMirrored = true;
        } else if (isSolidBlockRight > isSolidBlockLeft) {
            isMirrored = true;
        }
        if (isMirrored) {
            meta = meta - 1 & 3;
            meta += 4;
            meta |= 8;
        }
        world.editingBlocks = true;
        world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, doorBlockBottom.id, meta);
        world.setBlockAndMetadataWithNotify(blockX, blockY + 1, blockZ, cropBlockTop.id, meta);
        world.editingBlocks = false;
        world.notifyBlocksOfNeighborChange(blockX, blockY, blockZ, doorBlockBottom.id);
        world.notifyBlocksOfNeighborChange(blockX, blockY + 1, blockZ, cropBlockTop.id);
        world.playBlockSoundEffect((float)blockX + 0.5f, (float)blockY + 0.5f, (float)blockZ + 0.5f, doorBlockBottom, EnumBlockSoundEffectType.PLACE);
        itemstack.consumeItem(entityplayer);
        return true;
    }

}
