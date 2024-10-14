package lexal.btb.crafting;

import lexal.btb.block.BTBBlocks;
import lexal.btb.item.BTBItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ModRecipes implements RecipeEntrypoint {
    public static final String MOD_ID = "btbta";
    public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));

    @Override
    public void initNamespaces() {
        RecipeNamespace BTB = new RecipeNamespace();
		BTB.register("workbench", WORKBENCH);
		Registries.RECIPES.register(MOD_ID, BTB);
    }

    @Override
    public void onRecipesReady() {
        RecipeBuilder.Shapeless(MOD_ID)
                .addInput(Item.eggChicken)
                .addInput(Item.wheat)
                .addInput(Item.dustSugar)
                .create("pancakeCraft", new ItemStack(BTBItems.pancake, 3));

        RecipeBuilder.Shapeless(MOD_ID)
                .addInput(BTBBlocks.blueRose)
                .create("bRose2Dye", new ItemStack(Item.dye, 2, 6));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        " C ",
                        "CBC",
                        " C ")
                .addInput('B', Item.bucket)
                .addInput('C', BTBItems.corn)
                .create("sadcorn", new ItemStack(BTBItems.unPopcorn));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        " C ",
                        "CBC",
                        " C ")
                .addInput('C', Item.sulphur)
                .addInput('B', Item.paper)
                .create("emptyDisc", new ItemStack(BTBItems.recordBlank));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape("CCC")
                .addInput('C', BTBItems.crackedCorn)
                .create("cornbread", new ItemStack(BTBItems.cornBread));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CCC",
                        "SSS",
                        "WEW")
                .addInput('C', Item.foodCherry)
                .addInput('E', Item.eggChicken)
                .addInput('S', Item.dustSugar)
                .addInput('W', Item.wheat)
                .create("cherry_pie", new ItemStack(BTBItems.foodCherryPie));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "PIP",
                        "PDP",
                        "PPP")
                .addInput('P', Block.planksOak)
                .addInput('I', Item.ingotIron)
                .addInput('D', Item.diamond)
                .create("inscribe", new ItemStack(BTBBlocks.inscriber));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "SGS",
                        "GSG")
                .addInput('G', Block.glass)
                .addInput('S', Item.ingotSteelCrude)
                .create("framedglass", new ItemStack(BTBBlocks.glassFramed));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "GGG",
                        "GGG")
                .addInput('G', BTBBlocks.glassFramed)
                .create("frameglasstrapremix", new ItemStack(BTBBlocks.trapdoorGlassFramed, 6));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CC",
                        "CC")
                .addInput('C', Item.flint)
                .create("tiled", new ItemStack(BTBBlocks.flintTile, 4));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CCC")
                .addInput('C', BTBBlocks.flintTile)
                .create("tiledD", new ItemStack(BTBBlocks.slabFlintTile, 6));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "C",
                        "C")
                .addInput('C', BTBBlocks.slabFlintTile)
                .create("tiledR", new ItemStack(BTBBlocks.flintTile));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CC",
                        "CC")
                .addInput('C', Item.olivine)
                .create("bricked", new ItemStack(BTBBlocks.brickOlivine, 4));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CCC",
                        "C C")
                .addInput('C', Item.cloth)
                .create("cloth1", new ItemStack(BTBItems.armorHelmetCloth));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "C C",
                        "CCC",
                        "CCC")
                .addInput('C', Item.cloth)
                .create("cloth2", new ItemStack(BTBItems.armorChestplateCloth));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "CCC",
                        "C C",
                        "C C")
                .addInput('C', Item.cloth)
                .create("cloth3", new ItemStack(BTBItems.armorLeggingsCloth));

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "C C",
                        "C C")
                .addInput('C', Item.cloth)
                .create("cloth3", new ItemStack(BTBItems.armorBootsCloth));

        RecipeBuilder.Furnace(MOD_ID)
                .setInput(BTBItems.unPopcorn)
                .create("poppingoff", BTBItems.popcornBucket.getDefaultStack());
        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(BTBItems.unPopcorn)
                .create("poppingoff2", BTBItems.popcornBucket.getDefaultStack());

        RecipeBuilder.Furnace(MOD_ID)
                .setInput(BTBItems.corn)
                .create("crackingoil", BTBItems.crackedCorn.getDefaultStack());
        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(BTBItems.corn)
                .create("cracking2", BTBItems.crackedCorn.getDefaultStack());

        RecipeBuilder.BlastFurnace(MOD_ID)
                .setInput(Block.netherrack)
                .create("cracking", Block.netherrackIgneous.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                        "TT",
                        "CC")
                .addInput('C', Item.stick)
                .addInput('T', Block.trapdoorPlanksOak)
                .create("stooled", new ItemStack(BTBItems.stool));
    }

    public static void postInit() {
        Registries.RECIPE_TYPES.register("dyedArmor", RecipeEntryDyedArmor.class);
		WORKBENCH.register("dyingArmor", new RecipeEntryDyedArmor());
    }
}
