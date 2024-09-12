package lexal.btb.item;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;

import java.awt.*;

import static lexal.btb.BTBTA.MOD_ID;


public class ItemArmorColored extends ItemArmor {
    public ItemArmorColored(String name, int id, ArmorMaterial material, int armorPiece) {
        super(name, id, material, armorPiece);
    }

    public int getColor(ItemStack itemStack) {
        if (itemStack != null && itemStack.getData().containsKey("dyed_color")) {
            CompoundTag colorTag = itemStack.getData().getCompound("dyed_color");
            int red = colorTag.getShort("red");
            int green = colorTag.getShort("green");
            int blue = colorTag.getShort("blue");
            return (0xFF << 24 | red << 16 | green << 8 | blue);
        }
        return 0xFFFFFFFF;
    }
}
