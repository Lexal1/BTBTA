package lexal.btb.crafting;

import com.mojang.nbt.CompoundTag;
import lexal.btb.item.ItemArmorColored;
import net.minecraft.client.util.helper.Colors;
import net.minecraft.core.data.registry.recipe.SearchQuery;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingDynamic;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryCrafting;
import net.minecraft.core.util.helper.Color;

import java.util.ArrayList;
import java.util.List;

public class RecipeEntryDyedArmor extends RecipeEntryCraftingDynamic {
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventorycrafting) {
        ItemStack armorStack = null;
        List<ItemStack> dyeStacks = new ArrayList<>();
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                ItemStack stack = inventorycrafting.getItemStackAt(x, y);
                if (stack == null) continue;
                if (stack.getItem() instanceof ItemArmorColored) {
                    armorStack = stack;
                } else if (stack.itemID == Item.dye.id) {
                    dyeStacks.add(stack);
                }
            }
        }
        if (armorStack != null && !dyeStacks.isEmpty()) {
            ItemStack outStack = armorStack.copy();
            int r = 0;
            int g = 0;
            int b = 0;
            if (outStack.getData().containsKey("dyed_color")){
                CompoundTag armorColorTag = outStack.getData().getCompound("dyed_color");
                r += armorColorTag.getShort("red");
                g += armorColorTag.getShort("green");
                b += armorColorTag.getShort("blue");
            }

            for (ItemStack dyeStack : dyeStacks){
                Color color = Colors.allChatColors[15 - dyeStack.getMetadata()];
                r += color.getRed();
                g += color.getGreen();
                b += color.getBlue();
            }

            r /= dyeStacks.size() + 1;
            g /= dyeStacks.size() + 1;
            b /= dyeStacks.size() + 1;
            CompoundTag colorTag = new CompoundTag();
            colorTag.putShort("red", (short) r);
            colorTag.putShort("green", (short) g);
            colorTag.putShort("blue", (short) b);
            outStack.getData().putCompound("dyed_color", colorTag);
            outStack.stackSize = 1;
            return outStack;
        }
        return null;
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public boolean matches(InventoryCrafting crafting) {
        ItemStack armorStack = null;
        ItemStack dyeStack = null;
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                ItemStack stack = crafting.getItemStackAt(x, y);
                if (stack == null) continue;
                if (stack.getItem() instanceof ItemArmorColored) {
                    if (armorStack != null) {
                        return false;
                    }
                    armorStack = stack;
                    continue;
                }
                if (stack.itemID == Item.dye.id) {
                    dyeStack = stack;
                    continue;
                }
                return false;
            }
        }
        return armorStack != null && dyeStack != null;
    }

    @Override
    public boolean matchesQuery(SearchQuery query) {
        return false;
    }

    @Override
    public ItemStack[] onCraftResult(InventoryCrafting crafting) {
        ItemStack[] returnStack = new ItemStack[9];
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                ItemStack stack = crafting.getItemStackAt(x, y);
                if (stack == null) continue;
                --stack.stackSize;
                if (stack.stackSize > 0) continue;
                crafting.setSlotContentsAt(x, y, null);
            }
        }
        return returnStack;
    }
}
