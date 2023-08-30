package lexal.btb.mixin;

import lexal.btb.world.WorldTypeMoon;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.RenderGlobal;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.world.LevelListener;
import net.minecraft.core.world.World;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderGlobal.class, remap = false)
public class RenderGlobalMixin implements LevelListener {
    @Shadow
    private World worldObj;
    @Shadow
    private RenderEngine renderEngine;

    @Inject(method = "drawSky(F)V",
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBindTexture(II)V",
                    shift = At.Shift.AFTER,
                    ordinal = 1))
    private void renderEarthImage(float renderPartialTicks,CallbackInfo cbi){
        if (this.worldObj.getWorldType() instanceof WorldTypeMoon){ // Replace moon texture with earth when on the moon
            GL11.glBindTexture(3553, this.renderEngine.getTexture("/assets/btb/terrain/earth.png"));
        }
    }

    @Shadow
    public void blockChanged(int i, int j, int k) {

    }

    @Shadow
    public void setBlocksDirty(int i, int j, int k, int l, int m, int n) {

    }

    @Shadow
    public void playSound(String string, SoundType soundType, double d, double e, double f, float g, float h) {

    }

    @Shadow
    public void playBlockSoundEffect(Block block, EnumBlockSoundEffectType enumBlockSoundEffectType, double d, double e, double f) {

    }

    @Shadow
    public void addParticle(String string, double d, double e, double f, double g, double h, double i, double j) {

    }

    @Shadow
    public void addParticle(String string, double d, double e, double f, double g, double h, double i) {

    }

    @Shadow
    public void entityAdded(Entity entity) {

    }

    @Shadow
    public void entityRemoved(Entity entity) {

    }

    @Shadow
    public void allChanged() {

    }

    @Shadow
    public void playStreamingMusic(String string, int i, int j, int k) {

    }

    @Shadow
    public void tileEntityChanged(int i, int j, int k, TileEntity tileEntity) {

    }

    @Shadow
    public void levelEvent(EntityPlayer entityPlayer, int i, int j, int k, int l, int m) {

    }
}
