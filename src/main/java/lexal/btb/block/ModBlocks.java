package lexal.btb.block;

import lexal.btb.BTBTA;
import lexal.btb.UtilIdRegistrar;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.sound.block.BlockSounds;
import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.block.ItemBlockLayer;
import turniplabs.halplibe.helper.BlockBuilder;

public class ModBlocks {
    public static final Block layerPancake = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.5f)
            .setResistance(0.0f)
            .setBlockSound(BlockSounds.CLOTH)
            .setTopBottomTexture("pancake_top.png")
            .setSideTextures("pancake_side.png")
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockLayerPancake("layer.pancake",UtilIdRegistrar.nextIdBlock(), Material.snow));

    public static final Block blueRose = new BlockBuilder(BTBTA.MOD_ID)
            .setTextures("flower_rose_blue.png")
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(new BlockModelRenderBlocks(1))
            .build(new BlockFlower("bluerose",UtilIdRegistrar.nextIdBlock()));

    public static final Block flintTile = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.0f)
            .setResistance(1.0f)
            .setTextures("tile_top.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new Block("flint_tile",UtilIdRegistrar.nextIdBlock(),Material.stone));
    public static final Block birchSyrupLog = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.0f)
            .setResistance(1.0f)
            .setSideTextures("birch_syrup.png")
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .setBlockDrop(Block.logBirch)
            .setTopBottomTexture(1,24)
            .build(new BlockLog("syruplog",UtilIdRegistrar.nextIdBlock()));
    public static final Block layerPancakeSyrup = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.5f)
            .setResistance(0.0f)
            .setBlockSound(BlockSounds.CLOTH)
            .setTopBottomTexture("pancake_top_syrup.png")
            .setSideTextures("pancake_side_syrup.png")
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockLayerPancakeSyrup("layer.pancake.syrup",UtilIdRegistrar.nextIdBlock(), Material.snow));

    public static final Block cornCropTop = new BlockBuilder(BTBTA.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(new BlockModelRenderBlocks(1))
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockCropCornTop("corncroptop",UtilIdRegistrar.nextIdBlock()));

    public static final Block cornCropBottom = new BlockBuilder(BTBTA.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(new BlockModelRenderBlocks(1))
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockCropCornBottom("corncropbottom",UtilIdRegistrar.nextIdBlock()));

    public static final Block frameGlass = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.5f)
            .setResistance(3.0f)
            .setTextures("frame_glass.png")
            .build(new BlockFramedGlass("glass.framed",UtilIdRegistrar.nextIdBlock(),Material.glass,false));

    public static final Block trapdoorFrameGlass = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.5f)
            .setResistance(3.0f)
            .setTextures("frame_glass.png")
            .build(new BlockFramedGlassTrapdoor(
                    "glass.framed.trapdoor",
                    UtilIdRegistrar.nextIdBlock(),
                    Material.glass,
                    false)
            ).withDisabledNeighborNotifyOnMetadataChange();

    public static final Block flintTileSlab = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.0f)
            .setResistance(1.0f)
            .setTextures("tile_top.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new BlockSlab(flintTile,UtilIdRegistrar.nextIdBlock()));

    public static final Block inscriber = new BlockBuilder(BTBTA.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(1.0f)
            .setResistance(1.0f)
            .setImmovable()
            .setTopBottomTexture("inscriber_top.png")
            .setSideTextures("inscriber_side.png")
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .build(new BlockInscriber("inscriber",UtilIdRegistrar.nextIdBlock(),Material.wood));

    static {
        Item.itemsList[layerPancake.id] = new ItemBlockLayer(layerPancake);
    }

    public static void register() {
        Block.tickOnLoad[cornCropBottom.id] = true;
        Block.tickOnLoad[cornCropTop.id] = true;
        Block.blocksList[Block.logBirch.id] = null; // :)
        Block.blocksList[Block.logBirch.id] = new BlockBirchLog().withTexCoords(1, 24, 0, 24).withHardness(2.0f).withDisabledNeighborNotifyOnMetadataChange().withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE);
        Block.tickOnLoad[Block.logBirch.id] = true;
    }

}
