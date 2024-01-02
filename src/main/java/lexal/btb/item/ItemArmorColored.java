package lexal.btb.item;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ArmorMaterial;
import turniplabs.halplibe.helper.TextureHelper;
import useless.prismaticlibe.ColoredArmorTexture;
import useless.prismaticlibe.ColoredTexture;
import useless.prismaticlibe.IColored;
import useless.prismaticlibe.IColoredArmor;

import java.awt.*;

import static lexal.btb.BTBTA.MOD_ID;


public class ItemArmorColored extends ItemArmor implements IColoredArmor, IColored {
    private final int[][] texturesOut = new int[][]{
            TextureHelper.getOrCreateItemTexture(MOD_ID, "armor/cloth_hood_outline.png"),
            TextureHelper.getOrCreateItemTexture(MOD_ID, "armor/cloth_shirt_outline.png"),
            TextureHelper.getOrCreateItemTexture(MOD_ID, "armor/cloth_pants_outline.png"),
            TextureHelper.getOrCreateItemTexture(MOD_ID, "armor/cloth_boots_outline.png")};
    private final int[][] texturesGrey = new int[][]{
            TextureHelper.getOrCreateItemTexture(MOD_ID, "armor/cloth_hood_colored.png"),
            TextureHelper.getOrCreateItemTexture(MOD_ID, "armor/cloth_shirt_colored.png"),
            TextureHelper.getOrCreateItemTexture(MOD_ID, "armor/cloth_pants_colored.png"),
            TextureHelper.getOrCreateItemTexture(MOD_ID, "armor/cloth_boots_colored.png")};

    public ItemArmorColored(String name, int id, ArmorMaterial material, int armorPiece) {
        super(name, id, material, armorPiece);
    }

    @Override
    public ColoredArmorTexture[] getArmorTextures(ItemStack itemStack) {
        return new ColoredArmorTexture[]{new ColoredArmorTexture("clothes", getColor(itemStack)), new ColoredArmorTexture("clothes_out", Color.WHITE)};
    }
    public Color getColor(ItemStack itemStack){
        if (itemStack.getData().containsKey("dyed_color")){
            CompoundTag colorTag = itemStack.getData().getCompound("dyed_color");
            int red = colorTag.getShort("red");
            int green = colorTag.getShort("green");
            int blue = colorTag.getShort("blue");
            return new Color(red, green, blue);
        }
        return new Color(255, 255,255);
    }

    @Override
    public ColoredTexture[] getTextures(ItemStack itemStack) {
        return new ColoredTexture[]{new ColoredTexture(texturesGrey[armorPiece], getColor(itemStack)), new ColoredTexture(texturesOut[armorPiece], Color.WHITE)};
    }
}
