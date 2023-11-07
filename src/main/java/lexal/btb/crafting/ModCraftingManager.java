package lexal.btb.crafting;

import lexal.btb.BTBTA;
import lexal.btb.block.ModBlocks;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.CraftingManager;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import turniplabs.halplibe.helper.RecipeHelper;

public class ModCraftingManager {


    public static void register() {

        //item recipes

        RecipeHelper.Crafting.createShapelessRecipe(ModItems.pancake, 3, new Object[]{Item.eggChicken, Item.wheat, Item.dustSugar});

        RecipeHelper.Crafting.createShapelessRecipe(new ItemStack(Item.dye, 2, 6), new Object[]{ModBlocks.blueRose});

        RecipeHelper.Crafting.createRecipe(ModItems.unPopcorn, 1, new Object[]{
                " C ",
                "CBC",
                " C ",
                'C', ModItems.corn,
                'B', Item.bucket});

        RecipeHelper.Crafting.createRecipe(ModItems.recordBlank, 1, new Object[]{
                " C ",
                "CBC",
                " C ",
                'C', Item.sulphur,
                'B', Item.paper});

        RecipeHelper.Crafting.createRecipe(ModItems.cornBread, 1, new Object[]{
                "CCC",
                'C', ModItems.crackedCorn});

        RecipeHelper.Crafting.createRecipe(ModItems.cherryPie, 1, new Object[]{
                "CCC",
                "SSS",
                "WEW",
                'C', Item.cherry,
                'S', Item.dustSugar,
                'W', Item.wheat,
                'E', Item.eggChicken});

        RecipeHelper.Crafting.createShapelessRecipe(ModItems.pumpkinPie, 1, new Object[]{Item.eggChicken, Item.wheat, Item.dustSugar, Block.pumpkin});
        //block recipes

        RecipeHelper.craftingManager.addRecipe(new ItemStack(Block.planksOakPainted, 4, DyeColor.DYE_WHITE.blockMeta), false, new Object[]{"#", Character.valueOf('#'), ModBlocks.birchSyrupLog});

        RecipeHelper.craftingManager.addRecipe(new ItemStack(ModBlocks.inscriber, 1), true, false, new Object[]{
                "PIP",
                "PDP",
                "PPP",
                'P', Block.planksOak,
                'I', Item.ingotIron,
                'D', Item.diamond});

        RecipeHelper.Crafting.createRecipe(ModBlocks.frameGlass, 8, new Object[]{
                "GCG",
                "CGC",
                'C', Item.ingotSteelCrude,
                'G', Block.glass});

        RecipeHelper.Crafting.createRecipe(ModBlocks.trapdoorFrameGlass, 6, new Object[]{
                "CCC",
                "CCC",
                'C', ModBlocks.frameGlass});

        RecipeHelper.Crafting.createRecipe(ModBlocks.flintTile, 2, new Object[]{
                "CC",
                "CC",
                'C', Item.flint});

        RecipeHelper.Crafting.createRecipe(ModBlocks.flintTileSlab, 6, new Object[]{
                "CCC",
                'C', ModBlocks.flintTile});

        RecipeHelper.Crafting.createRecipe(ModBlocks.olivineBricks, 4, new Object[]{
                "CC",
                "CC",
                'C', Item.olivine});

        //armor recipes

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

        RecipeHelper.Blasting.createRecipe(Block.netherrackIgneous,Block.netherrack);
        RecipeHelper.Blasting.createRecipe(ModItems.cornBread,ModItems.crackedCorn);
        RecipeHelper.Smelting.createRecipe(ModItems.cornBread,ModItems.crackedCorn);
        RecipeHelper.Blasting.createRecipe(ModItems.popcornBucket,ModItems.unPopcorn);
        RecipeHelper.Smelting.createRecipe(ModItems.popcornBucket,ModItems.unPopcorn);

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
