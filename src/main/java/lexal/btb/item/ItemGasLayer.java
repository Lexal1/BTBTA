package lexal.btb.item;

import lexal.btb.block.BlockGas;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

public class ItemGasLayer extends ItemBlock{
    public ItemGasLayer(Block block) {
        super(block);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
        int id = world.getBlockId(blockX, blockY, blockZ);
        int meta = world.getBlockMetadata(blockX, blockY, blockZ);
        if (id != this.blockID && Block.blocksList[id] != null && Block.blocksList[id].hasTag(BlockTags.PLACE_OVERWRITES)) {
            id = 0;
            meta = 0;
        }
        if (itemstack.stackSize <= 0) {
            return false;
        }
        if (blockY == world.getHeightBlocks() - 1 && Block.blocksList[this.blockID].blockMaterial.isSolid()) {
            return false;
        }
        if (id == this.blockID && side == Side.TOP) {
            BlockGas blockLayer = (BlockGas)Block.blocksList[this.blockID];
            int newMeta = meta + 1;
            AABB bbBox = AABB.getBoundingBoxFromPool(blockX, blockY, blockZ, (float)blockX + 1.0f, (float)blockY + (float)(2 * (newMeta + 1)) / 16.0f, (float)blockZ + 1.0f);
            if (!world.checkIfAABBIsClear(bbBox)) {
                return false;
            }
            if (newMeta <= 0b11111) {
                world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.blockID, newMeta);
            } else {
                world.setBlockAndMetadataWithNotify(blockX, blockY + 1, blockZ, this.blockID, 0);
            }
            world.markBlockNeedsUpdate(blockX, blockY, blockZ);
            world.playBlockSoundEffect((float)blockX + 0.5f, (float)blockY + 0.5f, (float)blockZ + 0.5f, blockLayer, EnumBlockSoundEffectType.PLACE);
            itemstack.consumeItem(entityplayer);
            return true;
        }
        if (id != 0) {
            id = world.getBlockId(blockX += side.getOffsetX(), blockY += side.getOffsetY(), blockZ += side.getOffsetZ());
            meta = world.getBlockMetadata(blockX, blockY, blockZ);
        }
        if (id == this.blockID) {
            BlockGas blockLayer = (BlockGas)Block.blocksList[this.blockID];
            int newMeta = meta + 1;
            AABB bbBox = AABB.getBoundingBoxFromPool(blockX, blockY, blockZ, (float)blockX + 1.0f, (float)blockY + (float)(2 * (newMeta + 1)) / 16.0f, (float)blockZ + 1.0f);
            if (!world.checkIfAABBIsClear(bbBox)) {
                return false;
            }
            if (newMeta < 0b11111) {
                world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.blockID, newMeta);
            } else {
                world.setBlockAndMetadataWithNotify(blockX, blockY + 1, blockZ, this.blockID, 0);
            }
            world.markBlockNeedsUpdate(blockX, blockY, blockZ);
            world.playBlockSoundEffect((float)blockX + 0.5f, (float)blockY + 0.5f, (float)blockZ + 0.5f, blockLayer, EnumBlockSoundEffectType.PLACE);
            itemstack.consumeItem(entityplayer);
            return true;
        }
        if (world.canBlockBePlacedAt(this.blockID, blockX, blockY, blockZ, false, side)) {
            Block block = Block.blocksList[this.blockID];
            if (world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.blockID, this.getPlacedBlockMetadata(itemstack.getMetadata()))) {
                Block.blocksList[this.blockID].onBlockPlaced(world, blockX, blockY, blockZ, null, entityplayer, yPlaced);
                world.markBlockNeedsUpdate(blockX, blockY, blockZ);
                world.playBlockSoundEffect((float)blockX + 0.5f, (float)blockY + 0.5f, (float)blockZ + 0.5f, block, EnumBlockSoundEffectType.PLACE);
                itemstack.consumeItem(entityplayer);
            }
            return true;
        }
        return false;
    }
}
