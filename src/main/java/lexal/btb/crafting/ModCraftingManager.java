package lexal.btb.crafting;

import lexal.btb.BTBTA;
import lexal.btb.block.ModBlocks;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.CraftingManager;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeHelper;

public class ModCraftingManager {


    public static void register() {
        //item recipes
        RecipeHelper.Crafting.createShapelessRecipe(ModItems.pancake, 3, new Object[]{Item.eggChicken, Item.wheat, Item.dustSugar});
        RecipeHelper.Crafting.createShapelessRecipe(new ItemStack(Item.dye, 2, 6), new Object[]{ModBlocks.blueRose});

        //block recipes


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
        RecipeHelper.Smelting.createRecipe(ModBlocks.netherBrick,Block.netherrack);

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
