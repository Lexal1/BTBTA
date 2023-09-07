package lexal.btb.block;

import lexal.btb.BTBTA;
import lexal.btb.UtilIdRegistrar;
import lexal.btb.item.ItemGasLayer;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.block.ItemBlockLayer;
import turniplabs.halplibe.helper.BlockBuilder;

public class ModBlocks {

    public static final Block ironplating = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(3.0f)
            .setResistance(2.5f)
            .setTextures("iron_plating.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new Block("ironplating", UtilIdRegistrar.nextIdBlock(), Material.metal));
    public static final Block goldplating = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(2.0f)
            .setResistance(1.5f)
            .setTextures("gold_plating.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new Block("goldplating", UtilIdRegistrar.nextIdBlock(),Material.metal));
    public static final Block moonturf = new BlockBuilder(BTBTA.MOD_ID)
            .setBlockSound(new BlockSound("step.sand", "step.stone", 1.0f, 1.0f))
            .setHardness(2.0f)
            .setResistance(1.5f)
            .setTextures("moon_turf.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new Block("moonturf", UtilIdRegistrar.nextIdBlock(),Material.stone));
    public static final Block moonstone = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(2.5f)
            .setResistance(2.5f)
            .setTextures("moon_stone.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new BlockMoonstone("moonstone", UtilIdRegistrar.nextIdBlock(),Material.stone));
    public static final Block catwalk = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.5f)
            .setResistance(1.5f)
            .setTopBottomTexture(7,19)
            .setSideTextures("catwalk_side.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new BlockCatwalk("catwalk", UtilIdRegistrar.nextIdBlock(),Material.metal));
    public static final Block mooncobblestone = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(3.0f)
            .setResistance(3.0f)
            .setTextures("moon_cobblestone.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new BlockMoonstone("mooncobblestone", UtilIdRegistrar.nextIdBlock(),Material.stone));
    public static final Block cheesewheel = new BlockBuilder(BTBTA.MOD_ID)
            .setTextures("cheese_block.png")
            .build(new BlockCheeseWheel("cheesewheel", UtilIdRegistrar.nextIdBlock()));

    public static final Block cheeseore = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(2.5f)
            .setResistance(2.5f)
            .setTextures("cheese_ore.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new BlockCheeseOre("cheeseore", UtilIdRegistrar.nextIdBlock()));
    public static final Block mooniron = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(2.5f)
            .setResistance(2.5f)
            .setTextures("moon_stone_iron.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new BlockOreIron("mooniron", UtilIdRegistrar.nextIdBlock(),Material.stone));
    public static final Block moongold = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(2.5f)
            .setResistance(2.5f)
            .setTextures("moon_stone_gold.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new BlockOreGold("moongold", UtilIdRegistrar.nextIdBlock(), Material.stone));
    public static final Block moonsnow = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.5f)
            .setResistance(0.0f)
            .setTextures("moon_turf.png")
            .setTags(BlockTags.MINEABLE_BY_SHOVEL, BlockTags.CAVES_CUT_THROUGH, BlockTags.PLACE_OVERWRITES, BlockTags.BROKEN_BY_FLUIDS, ModBlockTags.GAS_DESTROYS)
            .build(new BlockLayerSnow("moonsnow", UtilIdRegistrar.nextIdBlock(), Material.topSnow));

    public static final Block portalmoon = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(-1.0f)
            .setResistance(-1.0f)
            .setTextures("moon_cobblestone.png")
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockPortal("portal.moon",UtilIdRegistrar.nextIdBlock(),3, ironplating.id,moonstone.id));
    public static final Block layerPancake = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0.5f)
            .setResistance(0.0f)
            .setTopBottomTexture("pancake_top.png")
            .setSideTextures("pancake_side.png")
            .setTags(BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockLayerPancake("layer.pancake",UtilIdRegistrar.nextIdBlock(), Material.cloth));

    public static final Block torchUnlit = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0)
            .setTags(BlockTags.BROKEN_BY_FLUIDS)
            .setTextures("torch_unlit.png")
            .setBlockModel(new BlockModelRenderBlocks(2)) // Torch model
            .build(new BlockTorchUnlit("torch.unlit", UtilIdRegistrar.nextIdBlock())).withDisabledNeighborNotifyOnMetadataChange();
    public static final Block glowstoneTorch = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0)
            .setTextures("glowstone_torch.png")
            .setLuminance(8)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PREVENT_MOB_SPAWNS)
            .setBlockModel(new BlockModelRenderBlocks(2))
            .build(new BlockTorch("torch.glowstone",UtilIdRegistrar.nextIdBlock()));
    public static final Block whitetile = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(2.0f)
            .setResistance(2.0f)
            .setTextures("white_tile.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new Block("whitetile",UtilIdRegistrar.nextIdBlock(),Material.cloth));
    public static final Block blacktile = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(2.5f)
            .setResistance(2.5f)
            .setTextures("black_tile.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new Block("blacktile",UtilIdRegistrar.nextIdBlock(),Material.metal));
    public static final Block gasAirFlowing = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0)
            .setTextures(6/*-2*/,12/*-1*/)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLACE_OVERWRITES)
            .build(new BlockGasFlowing("gas.air.flowing", UtilIdRegistrar.nextIdBlock()));
    public static final Block gasAirStill = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(0)
            .setTextures(1/*+3*/,3/*+8*/)
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLACE_OVERWRITES)
            .build(new BlockGasStill("gas.air.still", UtilIdRegistrar.nextIdBlock()));

    public static final Block machinePressurizerIdle = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(3.5f)
            .setNorthTexture("pressurizer_idle.png")
            .setSouthTexture("iron_plating.png")
            .setEastTexture("iron_plating.png")
            .setWestTexture("iron_plating.png")
            .setTopTexture("iron_plating.png")
            .setBottomTexture("iron_plating.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new BlockMachinePressurizer("machine.pressurizer.idle", UtilIdRegistrar.nextIdBlock(), false) {});
    public static final Block machinePressurizerActive = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(3.5f)
            .setNorthTexture("pressurizer_active.png")
            .setSouthTexture("iron_plating.png")
            .setEastTexture("iron_plating.png")
            .setWestTexture("iron_plating.png")
            .setTopTexture("iron_plating.png")
            .setBottomTexture("iron_plating.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new BlockMachinePressurizer("machine.pressurizer.active", UtilIdRegistrar.nextIdBlock(), true) {});


    static {
        Item.itemsList[layerPancake.id] = new ItemBlockLayer(layerPancake);
        ((BlockLayerBase)moonsnow).setFullBlockID(moonturf.id);
        Item.itemsList[moonsnow.id] = new ItemBlockLayer(moonsnow);
        Item.itemsList[gasAirStill.id] = new ItemGasLayer(gasAirStill);
        Item.itemsList[gasAirFlowing.id] = new ItemGasLayer(gasAirFlowing);
    }

    public static void register() {}

}
