package lexal.btb.mixin;

import lexal.btb.item.ModItemTags;
import lexal.btb.item.ItemArmorColored;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.Color;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.lwjgl.opengl.GL11;

@Mixin(value = PlayerRenderer.class, remap = false)
public class PlayerRendererMixin {
    @Redirect(method = "renderName(Lnet/minecraft/core/entity/player/EntityPlayer;DDD)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/player/EntityPlayer;isSneaking()Z", ordinal = 1))
    private boolean armorNameHiding(EntityPlayer instance){
        boolean nameCloak = false;
        if (instance.inventory.armorInventory[3] != null){
            nameCloak =  instance.inventory.armorInventory[3].getItem().hasTag(ModItemTags.hideName);
        }
        return instance.isSneaking() || nameCloak;
    }

    // Credit to https://github.com/UselessSolutions/BTA_Babric_PrismaticLibe/blob/7.2/src/main/java/useless/prismaticlibe/mixin/PlayerRendererMixin.java
    @Inject(method = "setArmorModel", at = @At("HEAD"))
	private void setArmorModel_HEAD(EntityPlayer entity, int renderPass, float partialTick, CallbackInfoReturnable<Boolean> cir){
		float br = Minecraft.getMinecraft(this).fullbright ? 1f : entity.getBrightness(0);
		GL11.glColor4f(br, br, br, 1f);
		ItemStack itemstack = entity.inventory.armorItemInSlot(3 - renderPass);
		if (itemstack != null && itemstack.getItem() instanceof ItemArmorColored) {
			Color color = new Color().setARGB(((ItemArmorColored) itemstack.getItem()).getColor(itemstack));
			GL11.glColor4f((color.getRed() / 255.0f) * br, (color.getGreen() / 255.0f) * br, (color.getBlue() / 255.0f) * br, color.getAlpha() / 255.0f);
		}
	}

	@Inject(method = "setArmorModel", at = @At("TAIL"))
	private void setArmorModel_TAIL(EntityPlayer entity, int renderPass, float partialTick, CallbackInfoReturnable<Boolean> cir){
		float br = Minecraft.getMinecraft(this).fullbright ? 1f : entity.getBrightness(0);
		GL11.glColor4f(br, br, br, 1f);
	}
}
