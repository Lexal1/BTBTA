package lexal.btb.block;

import lexal.btb.UtilIdRegistrar;
import lexal.btb.item.BTBItems;
import net.minecraft.client.render.block.model.*;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockFlower;
import net.minecraft.core.block.BlockSeat;
import net.minecraft.core.block.BlockSlab;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlockLayer;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.BlockBuilder;

import static lexal.btb.BTBTA.MOD_ID;

public class BTBBlocks {

    public static Block layerPancake;
    public static Block layerPancakeSyrup;

    public static Block blueRose;

    public static Block flintTile;
    public static Block slabFlintTile;

    public static Block birchSyrupLog;

    public static Block cropsCornTop;
    public static Block cropsCornBottom;

    public static Block glassFramed;
    public static Block trapdoorGlassFramed;


    public static Block inscriber;

    public static Block brickOlivine;

    public static Block pieCherry;

    public static Block stool;


    public void initializeBlockDetails() {

    }


    public void initializeBlocks() {

        BlockBuilder pancakeLayer = new BlockBuilder(MOD_ID)
                .setHardness(0.5f)
                .setResistance(0.0f)
                .setBlockSound(BlockSounds.CLOTH)
                .setBlockModel(BlockModelLayer::new)
                .setItemBlock(ItemBlockLayer::new)
                .setTags(BlockTags.NOT_IN_CREATIVE_MENU);





        layerPancake = pancakeLayer
                .setTopBottomTextures("btb:block/pancake_top")
                .setSideTextures("btb:block/pancake_side")
                .build(new BlockLayerPancake("layer.pancake", UtilIdRegistrar.nextIdBlock(), Material.cake));
        layerPancakeSyrup = pancakeLayer
                .setTopBottomTextures("btb:block/pancake_top_syrup")
                .setSideTextures("btb:block/pancake_side_syrup")
                .build(new BlockLayerPancakeSyrup("layer.pancake.syrup", UtilIdRegistrar.nextIdBlock(), Material.cake));


        blueRose = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.GRASS)
                .setBlockModel(BlockModelCrossedSquares::new)
                .setTextures("btb:block/flower_rose_blue")
                .setTags(BlockTags.PLANTABLE_IN_JAR)
                .build(new BlockFlower("flower.blue", UtilIdRegistrar.nextIdBlock()));


        flintTile = new BlockBuilder(MOD_ID)
                .setHardness(1.5f)
                .setResistance(5.0f)
                .setTextures("btb:block/tile_top")
                .setTags(BlockTags.MINEABLE_BY_PICKAXE)
                .build(new Block("tile.flint", UtilIdRegistrar.nextIdBlock(), Material.stone));

        slabFlintTile = new BlockBuilder(MOD_ID)
                .setHardness(1.5f)
                .setResistance(5.0f)
                .setTags(BlockTags.MINEABLE_BY_PICKAXE)
                .setBlockModel(BlockModelSlab::new)
                .setItemBlock(ItemBlockSlab::new)
                .build(new BlockSlab(flintTile, UtilIdRegistrar.nextIdBlock()));


        birchSyrupLog = new BlockBuilder(MOD_ID)
                .setHardness(1.0f)
                .setResistance(1.0f)
                .setBlockModel(BlockModelAxisAligned::new)
                .setTopBottomTextures("minecraft:block/log_birch_top")
                .setSideTextures("btb:block/birch_syrup")
                .setTags(BlockTags.MINEABLE_BY_AXE)
                .setBlockDrop(Block.logBirch)
                .setBlockSound(BlockSounds.WOOD)
                .build(new BlockBirchLog("log.birch.syrup", UtilIdRegistrar.nextIdBlock(), true));


        cropsCornTop = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.GRASS)
                .setBlockModel(b -> new BlockModelCropCorn<>(b, false))
                .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
                .setTickOnLoad()
                .setTicking(true)
                .build(new BlockCropCornTop("crops.corn.top", UtilIdRegistrar.nextIdBlock()));

        cropsCornBottom = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.GRASS)
                .setBlockModel(b -> new BlockModelCropCorn<>(b, true))
                .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
                .setTickOnLoad()
                .setTicking(true)
                .build(new BlockCropCornBottom("crops.corn.bottom", UtilIdRegistrar.nextIdBlock()));


        glassFramed = new BlockBuilder(MOD_ID)
                .setHardness(0.75f)
                .setResistance(0.0f)
                .setTextures("btb:block/frame_glass")
                .build(new BlockGlassFramed("glass.framed", UtilIdRegistrar.nextIdBlock(), Material.glass));

        trapdoorGlassFramed = new BlockBuilder(MOD_ID)
                .setHardness(0.75f)
                .setResistance(0.0f)
                .setBlockModel(BlockModelTrapDoor::new)
                .setTextures("btb:block/frame_glass")
                .setVisualUpdateOnMetadata()
                .build(new BlockTrapDoorFramed("trapdoor.glass.framed", UtilIdRegistrar.nextIdBlock(), Material.glass, false));


        inscriber = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(1.0f)
                .setResistance(1.0f)
                .setImmovable()
                .setSideTextures("btb:block/inscriber_top")
                .setTopBottomTextures("btb:block/inscriber_side")
                .setTags(BlockTags.MINEABLE_BY_AXE)
                .build(new BlockInscriber("inscriber", UtilIdRegistrar.nextIdBlock(), Material.wood));


        brickOlivine = new BlockBuilder(MOD_ID)
                .setHardness(1.5f)
                .setResistance(5.0f)
                .setTextures("btb:block/olivine_bricks")
                .setTags(BlockTags.MINEABLE_BY_PICKAXE)
                .build(new Block("brick.olivine", UtilIdRegistrar.nextIdBlock(), Material.stone));


        pieCherry = new BlockBuilder(MOD_ID)
                .setHardness(0.5f)
                .setBlockModel(block -> new BlockModelCherryPie<>(block).withTextures("btb:block/cherry_pie_top", "btb:block/cherry_pie_bottom", "btb:block/cherry_pie_side"))
                .setBlockSound(BlockSounds.CLOTH)
                .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
                .build(new BlockCherryPie("pie.cherry", UtilIdRegistrar.nextIdBlock()));


        stool = new BlockBuilder(MOD_ID)
                .setHardness(0.5f)
                .setBlockModel(block -> new BlockModelSeat<>(block).withTextures("btb:block/stool_top", "minecraft:block/planks_oak", "btb:block/stool_side"))
                .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
                .build(new BlockSeat("stool", UtilIdRegistrar.nextIdBlock()) {
                    @Override
                    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
                        return new ItemStack[]{new ItemStack(BTBItems.stool)};
                    }
                });

    }

    public static void register() {
        // Replace vanilla birch log with custom version
        Block.blocksList[Block.logBirch.id] = null;
        Block.blocksList[Block.logBirch.id] = new BlockBuilder("minecraft")
                .setBlockModel(BlockModelAxisAligned::new)
                .setTopBottomTextures("minecraft:block/log_birch_top")
                .setSideTextures("minecraft:block/log_birch_side")
                .setHardness(2.0f)
                .setBlockSound(BlockSounds.WOOD)
                .setVisualUpdateOnMetadata()
                .setTicking(true)
                .setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
                .build(new BlockBirchLog("log.birch", 282, false));
    }

}
