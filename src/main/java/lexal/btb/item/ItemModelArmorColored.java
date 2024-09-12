package lexal.btb.item;

import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import com.mojang.nbt.CompoundTag;

import java.awt.*;

public class ItemModelArmorColored extends ItemModelStandard {
    private final IconCoordinate[] texturesOut = new IconCoordinate[]{
        TextureRegistry.getTexture("btb:item/armor/cloth_hood_outline"),
        TextureRegistry.getTexture("btb:item/armor/cloth_shirt_outline"),
        TextureRegistry.getTexture("btb:item/armor/cloth_pants_outline"),
        TextureRegistry.getTexture("btb:item/armor/cloth_boots_outline")
    };
    private final IconCoordinate[] texturesGrey = new IconCoordinate[]{
        TextureRegistry.getTexture("btb:item/armor/cloth_hood_colored"),
        TextureRegistry.getTexture("btb:item/armor/cloth_shirt_colored"),
        TextureRegistry.getTexture("btb:item/armor/cloth_pants_colored"),
        TextureRegistry.getTexture("btb:item/armor/cloth_boots_colored")
    };

    public int armorPiece = 0;
    public boolean overlay = false;
    public ItemModelArmorColored(Item item, int armorPiece) {
        super(item, "btbta");
        this.armorPiece = armorPiece;
    }

    @Override
    public IconCoordinate getIcon(Entity entity, ItemStack itemStack) {
        if (overlay) {
            return texturesOut[armorPiece];
        }
        return texturesGrey[armorPiece];
    }

    @Override
	public int getColor(ItemStack itemStack) {
        if (!overlay && itemStack != null && itemStack.getItem() instanceof ItemArmorColored) {
            return ((ItemArmorColored) itemStack.getItem()).getColor(itemStack);
		}
		return 0xFFFFFFFF;
	}

    public void renderItemInWorld(Tessellator tessellator, Entity entity, ItemStack itemStack, float brightness, float alpha, boolean worldTransform) {
        if (itemStack.getData().containsKey("dyed_color")) {
            renderItemInWorld(tessellator, entity, itemStack, brightness, alpha, worldTransform);
            overlay = true;
        }
        renderItemInWorld(tessellator, entity, itemStack, brightness, alpha, worldTransform);
        overlay = false;
    }
}
