package lexal.btb.mixin;

import lexal.btb.item.ModItemTags;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
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
}
