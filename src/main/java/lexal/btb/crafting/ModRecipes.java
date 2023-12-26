package lexal.btb.crafting;

import lexal.btb.block.ModBlocks;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ModRecipes implements RecipeEntrypoint {
    public static final RecipeNamespace BTBTA = new RecipeNamespace();
    public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
    @Override
    public void onRecipesReady() {
        BTBTA.register("workbench", WORKBENCH);
        Registries.RECIPES.register("btb", BTBTA);
        DataLoader.loadRecipes("/assets/btb/recipes/workbench.json");
    }


    public static void register() {
        //block recipes
        //armor recipes

//        RecipeHelper.Blasting.createRecipe(Block.netherrackIgneous,Block.netherrack);
//        RecipeHelper.Blasting.createRecipe(ModItems.crackedCorn,ModItems.corn);
//        RecipeHelper.Smelting.createRecipe(ModItems.crackedCorn,ModItems.corn);
//        RecipeHelper.Blasting.createRecipe(ModItems.popcornBucket,ModItems.unPopcorn);
//        RecipeHelper.Smelting.createRecipe(ModItems.popcornBucket,ModItems.unPopcorn);

    }
}
