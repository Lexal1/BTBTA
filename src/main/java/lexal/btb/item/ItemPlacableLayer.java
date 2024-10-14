package lexal.btb.item;

import lexal.btb.ModAchievements;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLayerBase;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

public class ItemPlacableLayer extends ItemPlaceable {
    public int blockID;
    public boolean placeOnUnfinished;
    public boolean onlyStackOnTop;
    public ItemPlacableLayer(String name, int id, Block blockToPlace) {
        super(name, id, blockToPlace);
        this.blockID = blockToPlace.id;
        placeOnUnfinished = true;
        onlyStackOnTop = true;
    }
    public ItemPlacableLayer(String name, int id, Block blockToPlace, boolean canPlaceOnUnfinishedStack, boolean onlyStackOnTop) {
        super(name, id, blockToPlace);
        this.blockID = blockToPlace.id;
        this.placeOnUnfinished = canPlaceOnUnfinishedStack;
        this.onlyStackOnTop = onlyStackOnTop;
    }
    @Override
    public boolean onUseItemOnBlock(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
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
        if (id == this.blockID && (side == Side.TOP || (!onlyStackOnTop && !entityplayer.isSneaking()))) {
            BlockLayerBase blockLayer = (BlockLayerBase)Block.blocksList[this.blockID];
            int newMeta = meta + 1;
            AABB bbBox = AABB.getBoundingBoxFromPool(blockX, blockY, blockZ, (float)blockX + 1.0f, (float)blockY + (float)(2 * (newMeta + 1)) / 16.0f, (float)blockZ + 1.0f);
            if (!world.checkIfAABBIsClear(bbBox)) {
                return false;
            }
            if (newMeta < 7) {
                if (newMeta == 6 && this.id == BTBItems.pancake.id){
                    entityplayer.triggerAchievement(ModAchievements.PANCAKES);
                }
                world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.blockID, newMeta);
            } else if (blockLayer.fullBlockID != -1) {
                world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, blockLayer.fullBlockID, 0);
            } else {
                if (placeOnUnfinished) {
                    world.setBlockAndMetadataWithNotify(blockX, blockY + 1, blockZ, this.blockID, 0);
                }
                else {
                    return false;
                }
            }
            world.playBlockSoundEffect(entityplayer, (float)blockX + 0.5f, (float)blockY + 0.5f, (float)blockZ + 0.5f, blockLayer, EnumBlockSoundEffectType.PLACE);
            itemstack.consumeItem(entityplayer);
            return true;
        }
        if (id != 0) {
            id = world.getBlockId(blockX += side.getOffsetX(), blockY += side.getOffsetY(), blockZ += side.getOffsetZ());
            meta = world.getBlockMetadata(blockX, blockY, blockZ);
        }
        if (id == this.blockID) {
            BlockLayerBase blockLayer = (BlockLayerBase)Block.blocksList[this.blockID];
            int newMeta = meta + 1;
            AABB bbBox = AABB.getBoundingBoxFromPool(blockX, blockY, blockZ, (float)blockX + 1.0f, (float)blockY + (float)(2 * (newMeta + 1)) / 16.0f, (float)blockZ + 1.0f);
            if (!world.checkIfAABBIsClear(bbBox)) {
                return false;
            }
            if (newMeta < 7) {
                world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.blockID, newMeta);
            } else if (blockLayer.fullBlockID != -1) {
                world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, blockLayer.fullBlockID, 0);
            } else {
                if (placeOnUnfinished) {
                    world.setBlockAndMetadataWithNotify(blockX, blockY + 1, blockZ, this.blockID, 0);
                }
                else {
                    return false;
                }

            }
            world.playBlockSoundEffect(entityplayer,(float)blockX + 0.5f, (float)blockY + 0.5f, (float)blockZ + 0.5f, blockLayer, EnumBlockSoundEffectType.PLACE);
            itemstack.consumeItem(entityplayer);
            return true;
        }
        if (world.canBlockBePlacedAt(this.blockID, blockX, blockY, blockZ, false, side)) {
            Block block = Block.blocksList[this.blockID];
            if (world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, this.blockID, this.getPlacedBlockMetadata(itemstack.getMetadata()))) {
                Block.blocksList[this.blockID].onBlockPlaced(world, blockX, blockY, blockZ, null, entityplayer, yPlaced);
                world.playBlockSoundEffect(entityplayer,(float)blockX + 0.5f, (float)blockY + 0.5f, (float)blockZ + 0.5f, block, EnumBlockSoundEffectType.PLACE);
                itemstack.consumeItem(entityplayer);
            }
            return true;
        }
        return false;
    }
}
