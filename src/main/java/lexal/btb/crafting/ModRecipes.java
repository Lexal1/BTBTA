package lexal.btb.crafting;

import net.minecraft.core.block.Block;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ModRecipes implements RecipeEntrypoint {
    public static final RecipeNamespace BTBTA = new RecipeNamespace();
    public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
    @Override
    public void onRecipesReady() {
        BTBTA.register("workbench", WORKBENCH);
        Registries.RECIPES.register("btb", BTBTA);
        Registries.RECIPE_TYPES.register("btb:crafting/armor", RecipeEntryDyedArmor.class);
        WORKBENCH.register("armor", new RecipeEntryDyedArmor());
        DataLoader.loadRecipes("/assets/btb/recipes/workbench.json");
        DataLoader.loadRecipes("/assets/btb/recipes/furnace.json");
        DataLoader.loadRecipes("/assets/btb/recipes/blast_furnace.json");

    }

}
