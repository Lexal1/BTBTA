package lexal.btb.block;

import lexal.btb.BTBTA;
import lexal.btb.UtilIdRegistrar;
import net.minecraft.client.sound.block.BlockSounds;
import net.minecraft.core.block.*;
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

    public static final Block paperwall = new BlockBuilder(BTBTA.MOD_ID)
            .setHardness(1.0f)
            .setResistance(0.0f)
            .setSideTextures("paperwall.png")
            .setTopBottomTexture("paperwall.png")
            //.setBlockModel(new BlockModelRenderBlocks(31))
            .setTags(BlockTags.MINEABLE_BY_AXE)
            .build(new BlockPaperWall("paperwall",UtilIdRegistrar.nextIdBlock(),Material.snow));

    static {
        Item.itemsList[layerPancake.id] = new ItemBlockLayer(layerPancake);
    }

    public static void register() {}

}
