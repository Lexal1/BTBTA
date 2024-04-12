package lexal.btb.crafting;

import lexal.btb.block.ModBlocks;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ModRecipes implements RecipeEntrypoint {
    public static final RecipeNamespace BTBTA = new RecipeNamespace();
    public static final String MOD_ID = "btbta";
    public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
    @Override
    public void onRecipesReady() {
        RecipeBuilder.Shapeless(MOD_ID)
                .addInput(Item.eggChicken)
                .addInput(Item.wheat)
                .addInput(Item.dustSugar)
                .create("pancakeCraft", new ItemStack(ModItems.pancake, 3));

        RecipeBuilder.Shapeless(MOD_ID)
                .addInput(ModBlocks.blueRose)
                .create("bRose2Dye", new ItemStack(Item.dye, 2, 6));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        " C ",
                        "CBC",
                        " C ")
                .addInput('B', Item.bucket)
                .addInput('C', ModItems.corn)
                .create("sadcorn", new ItemStack(ModItems.unPopcorn));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        " C ",
                        "CBC",
                        " C ")
                .addInput('C', Item.sulphur)
                .addInput('B', Item.paper)
                .create("emptyDisc", new ItemStack(ModItems.recordBlank));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape("CCC")
                .addInput('C', ModItems.crackedCorn)
                .create("cornrbead", new ItemStack(ModItems.cornBread));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CCC",
                        "SSS",
                        "WEW")
                .addInput('C', Item.cherry)
                .addInput('E', Item.eggChicken)
                .addInput('S', Item.dustSugar)
                .addInput('W', Item.wheat)
                .create("cherycake", new ItemStack(ModItems.cherryPie));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "PP",
                        "SE",
                        "WW")
                .addInput('P', Block.pumpkin)
                .addInput('E', Item.eggChicken)
                .addInput('S', Item.dustSugar)
                .addInput('W', Item.wheat)
                .create("pumpincake", new ItemStack(ModItems.pumpkinPie));

        // blocks

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "PIP",
                        "PDP",
                        "PPP")
                .addInput('P', Block.planksOak)
                .addInput('I', Item.ingotIron)
                .addInput('D', Item.diamond)
                .create("inscribe", new ItemStack(ModBlocks.inscriber));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "SGS",
                        "GSG")
                .addInput('G', Block.glass)
                .addInput('S', Item.ingotSteelCrude)
                .create("frameglass", new ItemStack(ModBlocks.frameGlass));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "GGG",
                        "GGG")
                .addInput('G', ModBlocks.frameGlass)
                .create("frameglasstrapremix", new ItemStack(ModBlocks.trapdoorFrameGlass, 6));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CC",
                        "CC")
                .addInput('C', Item.flint)
                .create("tiled", new ItemStack(ModBlocks.flintTile, 4));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CCC")
                .addInput('C', ModBlocks.flintTile)
                .create("tiledD", new ItemStack(ModBlocks.flintTileSlab, 6));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "C",
                        "C")
                .addInput('C', ModBlocks.flintTileSlab)
                .create("tiledR", new ItemStack(ModBlocks.flintTile));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CC",
                        "CC")
                .addInput('C', Item.olivine)
                .create("bricked", new ItemStack(ModBlocks.olivineBricks, 4));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CCC",
                        "C C")
                .addInput('C', Item.cloth)
                .create("cloth1", new ItemStack(ModItems.armorHelmetCloth));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "C C",
                        "CCC",
                        "CCC")
                .addInput('C', Item.cloth)
                .create("cloth2", new ItemStack(ModItems.armorChestplateCloth));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CCC",
                        "C C",
                        "C C")
                .addInput('C', Item.cloth)
                .create("cloth3", new ItemStack(ModItems.armorLeggingsCloth));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "C C",
                        "C C")
                .addInput('C', Item.cloth)
                .create("cloth3", new ItemStack(ModItems.armorBootsCloth));

        RecipeBuilder.Furnace(MOD_ID)
                .setInput(ModItems.unPopcorn)
                .create("poppingoff", ModItems.popcornBucket.getDefaultStack());
        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(ModItems.unPopcorn)
                .create("poppingoff2", ModItems.popcornBucket.getDefaultStack());

        RecipeBuilder.Furnace(MOD_ID)
                .setInput(ModItems.corn)
                .create("crackingoil", ModItems.crackedCorn.getDefaultStack());
        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(ModItems.corn)
                .create("cracking2", ModItems.crackedCorn.getDefaultStack());

        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(Block.netherrack)
                .create("cracking", Block.netherrackIgneous.getDefaultStack());
    }

}
