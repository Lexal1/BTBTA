package lexal.btb.mixin.Entity;

import lexal.btb.item.ItemArmorColored;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.lwjgl.opengl.GL11;

@Mixin(value = LivingRenderer.class, remap = false)
public abstract class LivingRendererMixin<T extends EntityLiving> {
	@Shadow protected abstract boolean shouldRenderPass(T entity, int renderPass, float partialTick);

	@Shadow protected ModelBase renderPassModel;
	@Unique
	float limbSwing;
	@Unique
	float limbYaw;
	@Unique
	float ticksExisted;
	@Unique
	float headYaw;
	@Unique
	float headYawOffset;
	@Unique
	float headPitch;
	@Unique
	float scale;
	@Inject(method = "render(Lnet/minecraft/core/entity/EntityLiving;DDDFF)V",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingRenderer;shouldRenderPass(Lnet/minecraft/core/entity/EntityLiving;IF)Z"))
	private void captureLocals(
		T entity, double x, double y, double z, float yaw, float partialTick, CallbackInfo ci,
							   @Local(name = "limbSwing") float limbSwing,
							   @Local(name = "limbYaw") float limbYaw,
							   @Local(name = "ticksExisted") float ticksExisted,
							   @Local(name = "headYaw") float headYaw,
							   @Local(name = "headYawOffset") float headYawOffset,
							   @Local(name = "headPitch") float headPitch,
							   @Local(name = "scale") float scale
	){
		this.limbSwing = limbSwing;
		this.limbYaw = limbYaw;
		this.ticksExisted = ticksExisted;
		this.headYaw = headYaw;
		this.headYawOffset = headYawOffset;
		this.headPitch = headPitch;
		this.scale = scale;
	}
	@Redirect(method = "render(Lnet/minecraft/core/entity/EntityLiving;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingRenderer;shouldRenderPass(Lnet/minecraft/core/entity/EntityLiving;IF)Z"))
	private boolean hijackRenderPass(LivingRenderer instance, T entity, int renderPass, float partialTick){
		if (entity instanceof EntityPlayer){
			ItemStack itemstack = ((EntityPlayer) entity).inventory.armorItemInSlot(3 - renderPass);
			if (itemstack != null && itemstack.getItem() instanceof ItemArmorColored) {
				// do stuff
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(770, 771);
				shouldRenderPass(entity, renderPass, partialTick);
				this.renderPassModel.render(limbSwing, limbYaw, ticksExisted, headYaw - headYawOffset, headPitch, scale);
				GL11.glDisable(3042);
				GL11.glEnable(3008);
				return false;
			}
		}
		return this.shouldRenderPass(entity, renderPass, partialTick);
	}
}