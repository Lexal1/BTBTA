package lexal.btb.block;

import lexal.btb.BTBTA;
import lexal.btb.item.ModItems;
import lexal.btb.UtilIdRegistrar;

import net.minecraft.client.render.block.model.BlockModelCrossedSquares;
import net.minecraft.client.render.block.model.BlockModelTransparent;
import net.minecraft.client.render.block.model.BlockModelTrapDoor;
import net.minecraft.client.render.block.model.BlockModelStandard;

import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.world.World;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlockLayer;
import net.minecraft.core.sound.BlockSoundDispatcher;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

public class ModBlocks {
    public static final Block layerPancake = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.5f)
            .setResistance(0.0f)
            .setBlockSound(BlockSounds.CLOTH)
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("btb:block/pancake_top", "btb:block/pancake_side")
            )
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockLayerPancake("layer_pancake",UtilIdRegistrar.nextIdBlock(), Material.cake));

    public static final Block blueRose = new BlockBuilder(BTBTA.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(b -> new BlockModelCrossedSquares(b)
                .withTextures("btb:block/flower_rose_blue")
            )
            .setTags(BlockTags.PLANTABLE_IN_JAR)
            .build(new BlockFlower("bluerose",UtilIdRegistrar.nextIdBlock()));

    public static final Block flintTile = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.5f)
            .setResistance(5.0f)
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("btb:block/tile_top")
            )
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new Block("flint_tile",UtilIdRegistrar.nextIdBlock(), Material.stone));
    public static final Block birchSyrupLog = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.0f)
            .setResistance(1.0f)
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("minecraft:block/log_birch_top", "btb:block/birch_syrup")
            )
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .setBlockDrop(Block.logBirch)
            .setBlockSound(BlockSounds.WOOD)
            .build(new BlockBirchLog("syruplog",UtilIdRegistrar.nextIdBlock(), true));
    public static final Block layerPancakeSyrup = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.5f)
            .setResistance(0.0f)
            .setBlockSound(BlockSounds.CLOTH)
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("btb:block/pancake_top_syrup", "btb:block/pancake_side_syrup")
            )
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockLayerPancakeSyrup("layer_pancake_syrup",UtilIdRegistrar.nextIdBlock(), Material.cake));

    public static final Block cornCropTop = new BlockBuilder(BTBTA.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(b -> new BlockModelCropCorn(b, false))
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .setTickOnLoad()
            .build(new BlockCropCornTop("corncroptop",UtilIdRegistrar.nextIdBlock()));

    public static final Block cornCropBottom = new BlockBuilder(BTBTA.MOD_ID)
            .setBlockSound(BlockSounds.GRASS)
            .setBlockModel(b -> new BlockModelCropCorn(b, true))
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .setTickOnLoad()
            .build(new BlockCropCornBottom("corncropbottom",UtilIdRegistrar.nextIdBlock()));

    public static final Block frameGlass = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.75f)
            .setResistance(0.0f)
            .setBlockModel(b -> new BlockModelTransparent(b, true)
                .withTextures("btb:block/frame_glass")
            )
            .build(new BlockFramedGlass("glass_framed",UtilIdRegistrar.nextIdBlock(),Material.glass));

    public static final Block trapdoorFrameGlass = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.75f)
            .setResistance(0.0f)
            .setBlockModel(b -> new BlockModelTrapDoor(b)
                .withTextures("btb:block/frame_glass")
            )
            .build(new BlockFramedGlassTrapdoor(
                    "glass_framed_trapdoor",
                    UtilIdRegistrar.nextIdBlock(),
                    Material.glass,
                    false)
            ).withDisabledNeighborNotifyOnMetadataChange();

    public static final Block flintTileSlab = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.5f)
            .setResistance(5.0f)
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("btb:block/tile_top")
            )
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            //.setItemModel(b -> new ItemModelStandard(b, "btb:block/tile_flint")
            //)
            .build(new BlockSlab(flintTile,UtilIdRegistrar.nextIdBlock()));

    public static final Block inscriber = new BlockBuilder(BTBTA.MOD_ID)
            .setBlockSound(BlockSounds.WOOD)
            .setHardness(1.0f)
            .setResistance(1.0f)
            .setImmovable()
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("btb:block/inscriber_top", "btb:block/inscriber_side")
            )
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .build(new BlockInscriber("inscriber",UtilIdRegistrar.nextIdBlock(),Material.wood));

    public static final Block olivineBricks = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.5f)
            .setResistance(5.0f)
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("btb:block/olivine_bricks")
            )
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new Block("olivine_bricks",UtilIdRegistrar.nextIdBlock(), Material.stone));

    /*public static final Block pumpkinPie = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.5f)
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("btb:block/pumpkin_pie_top", "btb:block/pie_bottom", "btb:block/pie_side")
            )
            .setBlockSound(BlockSounds.CLOTH)
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockPumpkinPie("pie_pumpkin",UtilIdRegistrar.nextIdBlock()));*/
    // For id compat
    public static final int pumpkinPie_ID = UtilIdRegistrar.nextIdBlock();

    public static final Block cherryPie = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.5f)
            .setBlockModel(b -> new BlockModelPie(b)
                .withTextures("btb:block/cherry_pie_top", "btb:block/pie_bottom", "btb:block/pie_side")
            )
            .setBlockSound(BlockSounds.CLOTH)
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockCherryPie("pie_cherry",UtilIdRegistrar.nextIdBlock()));

    public static final Block stool = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.5f)
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("btb:block/stool_top", "btb:block/stool_side")
            )
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockSeat("stool",UtilIdRegistrar.nextIdBlock()) {
                @Override
                public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
                    return new ItemStack[] { new ItemStack(ModItems.stool) };
                }
            });

    static {
        Item.itemsList[layerPancake.id] = new ItemBlockLayer(layerPancake);
    }

    public static void register() {
        // Replace vanilla birch log with custom version
        Block.blocksList[Block.logBirch.id] = null;
        Block.blocksList[Block.logBirch.id] = new BlockBuilder("minecraft")
            .setBlockModel(b -> new BlockModelStandard(b)
                .withTextures("minecraft:block/log_birch_top", "minecraft:block/log_birch_side")
            )
            .setHardness(2.0f)
            .setVisualUpdateOnMetadata()
            .setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
            .build(new BlockBirchLog("log.birch", 282, false));
        BlockSoundDispatcher.getInstance().addDispatch(Block.blocksList[Block.logBirch.id], BlockSounds.WOOD);
        Block.shouldTick[Block.logBirch.id] = true;
    }

}
