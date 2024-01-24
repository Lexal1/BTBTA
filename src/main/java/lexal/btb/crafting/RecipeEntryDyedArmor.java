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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeEntryDyedArmor extends RecipeEntryCraftingDynamic {
    public static HashMap<Item, Map<Integer, Color>> dyeMap = new HashMap<>();
    private static final Map<Integer, Color> vanillaDye;
    static {
        vanillaDye = new HashMap<>();
        for (int color = 0; color < Colors.allSignColors.length; color++) {
            vanillaDye.put(color, Colors.allSignColors[15 - color]);
        }
        dyeMap.put(Item.dye, vanillaDye);
    }
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
                } else if (dyeMap.containsKey(stack.getItem())) {
                    dyeStacks.add(stack);
                }
            }
        }
        if (armorStack != null && !dyeStacks.isEmpty()) {
            ItemStack outStack = armorStack.copy();
            int r = -1;
            int g = -1;
            int b = -1;
            if (outStack.getData().containsKey("dyed_color")){
                CompoundTag armorColorTag = outStack.getData().getCompound("dyed_color");
                r = armorColorTag.getShort("red");
                g = armorColorTag.getShort("green");
                b = armorColorTag.getShort("blue");
            }

            for (ItemStack dyeStack : dyeStacks){
                Color color = dyeMap.getOrDefault(dyeStack.getItem(), vanillaDye).getOrDefault(dyeStack.getMetadata(), vanillaDye.get(0));
                if (r == -1 || g == -1 || b == -1){
                    r = (int) (color.getRed() * 0.85f);
                    g = (int) (color.getGreen() * 0.85f);
                    b = (int) (color.getBlue() * 0.85f);
                } else {
                    r += color.getRed();
                    g += color.getGreen();
                    b += color.getBlue();
                }
            }

            r /= dyeStacks.size();
            g /= dyeStacks.size();
            b /= dyeStacks.size();
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
                if (dyeMap.containsKey(stack.getItem())) {
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
