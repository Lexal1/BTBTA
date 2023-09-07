package lexal.btb.crafting;

import lexal.btb.BTBTA;
import lexal.btb.block.ModBlocks;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.CraftingManager;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.RecipeHelper;

public class ModCraftingManager {


    public static void register() {
        //item recipes

        RecipeHelper.Crafting.createShapelessRecipe(ModItems.burger, 1, new Object[]{Item.foodPorkchopCooked, Item.foodBread, Item.foodBread, ModItems.cheese});

        RecipeHelper.Crafting.createShapelessRecipe(ModItems.pancake, 3, new Object[]{Item.eggChicken, Item.wheat, Item.dustSugar});

        //block recipes
        RecipeHelper.Crafting.createRecipe(ModBlocks.catwalk, 8, new Object[]{
                "CCC",
                "I I",
                'I', Item.ingotIron,
                'C', Item.chainlink});
        RecipeHelper.Crafting.createRecipe(ModBlocks.cheesewheel, 1, new Object[]{
                "CC",
                "CC",
                'C', ModItems.cheese});
        RecipeHelper.Crafting.createRecipe(ModBlocks.ironplating, 12, new Object[]{
                "I I",
                " B ",
                "I I",
                'B', Block.blockIron,
                'I', Item.ingotIron});
        RecipeHelper.Crafting.createRecipe(ModBlocks.goldplating, 12, new Object[]{
                "I I",
                " B ",
                "I I",
                'B', Block.blockGold,
                'I', Item.ingotGold});
        RecipeHelper.Crafting.createRecipe(ModBlocks.glowstoneTorch, 3, new Object[]{
                "G",
                "S",
                'S', Item.stick,
                'G', Item.dustGlowstone});

        RecipeHelper.Crafting.createShapelessRecipe(ModBlocks.whitetile, 2, new Object[]{Block.stonePolished,ModItems.moondust,ModItems.moondust,ModItems.moondust,ModItems.moondust});
        RecipeHelper.Crafting.createShapelessRecipe(ModBlocks.blacktile, 8, new Object[]{ModBlocks.whitetile,Item.ingotSteel,Item.ingotSteel,Item.ingotSteel,Item.ingotSteel});


        //armor recipes
        RecipeHelper.Crafting.createRecipe(ModItems.armorHelmetSpace, 1, new Object[]{
                "CIC",
                "WLW",
                "CGC",
                'I', Item.armorHelmetIron,
                'L', Item.armorHelmetLeather,
                'G', Block.glassTinted,
                'W', Block.wool,
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(ModItems.armorChestplateSpace, 1, new Object[]{
                "WIW",
                "WLW",
                "CCC",
                'W', Block.wool,
                'I', Item.armorChestplateIron,
                'L', Item.armorChestplateLeather,
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(ModItems.armorLeggingsSpace, 1, new Object[]{
                "WWW",
                "CIC",
                "CLC",
                'I', Item.armorLeggingsIron,
                'L', Item.armorLeggingsLeather,
                'W', Block.wool,
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(ModItems.armorBootsSpace, 1, new Object[]{
                "CIC",
                "WLW",
                'I', Item.armorBootsIron,
                'L', Item.armorBootsLeather,
                'W', Block.wool,
                'C', Item.cloth});

        RecipeHelper.Crafting.createRecipe(ModItems.armorHelmetCloth, 1, new Object[]{
                "CCC",
                "C C",
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(ModItems.armorChestplateCloth, 1, new Object[]{
                "C C",
                "CCC",
                "CCC",
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(ModItems.armorLeggingsCloth, 1, new Object[]{
                "CCC",
                "C C",
                "C C",
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(ModItems.armorBootsCloth, 1, new Object[]{
                "C C",
                "L L",
                'L', Item.leather,
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(ModItems.armorBootsCrash, 1, new Object[]{
                "B",
                "S",
                "S",
                'B', ModItems.armorBootsSpace,
                'S', Block.spongeDry});

        BTBTA.LOGGER.info("btbta loaded all recipes successfully!"); //put recipes before this point
    }

    /*
    0 = stone
    1 = cobblestone
    2 = planksOak
    3 = grass
    4 = dirt
    5 = mossStone
    6 = logOak
    7 = leavesOak
    8 = oreCoalStone
    9 = oreIronStone
    10 = oreGoldStone
    11 = oreLapisStone
    12 = oreRedstoneOre
    13 = chestPlanksOak
    */
    private static void addAlternatives(int index, Block[] alternatives) {
        Block[] current = CraftingManager.blockAlternatives[index];
        Block[] newAlts = new Block[current.length+alternatives.length];
        System.arraycopy(current, 0, newAlts, 0, current.length);
        System.arraycopy(alternatives, 0, newAlts, current.length, alternatives.length);
        CraftingManager.blockAlternatives[index] = newAlts;
    }

    private static void appendAlternatives(Block[] alternatives) {
        Block[][] current = CraftingManager.blockAlternatives;
        Block[][] newAlts = new Block[current.length+1][];
        System.arraycopy(current, 0, newAlts, 0, current.length);
        newAlts[newAlts.length-1] = alternatives;
        CraftingManager.blockAlternatives = newAlts;
    }
}
