package lexal.btb.mixin.client;

import lexal.btb.ModMaterials;
import lexal.btb.world.worldType.WorldTypeMoon;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.core.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GuiIngame.class, remap = false)
public class GuiIngameMixin extends Gui {
    @Redirect(method = "renderGameOverlay(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/player/EntityPlayerSP;isUnderLiquid(Lnet/minecraft/core/block/material/Material;)Z"))
    private boolean renderOnMoon(EntityPlayerSP player, Material material){
        return (player.isUnderLiquid(material) || (player.world.getWorldType() instanceof WorldTypeMoon)) && !player.isUnderLiquid(ModMaterials.gas); // If underwater or on moon without helmet render oxygen overlay
    }
}
