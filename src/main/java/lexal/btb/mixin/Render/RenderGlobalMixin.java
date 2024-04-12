package lexal.btb.mixin.Render;

import lexal.btb.item.ItemRecordAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.render.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderGlobal.class, remap = false)
public class RenderGlobalMixin {

    @Redirect(method = "playStreamingMusic(Ljava/lang/String;III)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;setRecordPlayingMessage(Ljava/lang/String;)V"))
    public void playStreamingMusic(GuiIngame instance, String title) {
        instance.setRecordPlayingMessage(ItemRecordAccessor.getAuthor(title) + " - " + title);
    }

    @ModifyConstant(method = "playStreamingMusic(Ljava/lang/String;III)V", constant = @Constant(stringValue = "C418 - "))
    public String playStreamingMusic(String constant) {
        return "";
    }

}