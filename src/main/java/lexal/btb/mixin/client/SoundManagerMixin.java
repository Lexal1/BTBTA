package lexal.btb.mixin.client;

import lexal.btb.sound.SoundContainer;
import lexal.btb.world.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.SoundPool;
import net.minecraft.client.sound.SoundPoolEntry;
import net.minecraft.client.sound.SoundTypeHelper;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulscode.sound.SoundSystem;

import java.io.File;
import java.util.Random;

@Mixin(value = SoundManager.class, remap = false)
public class SoundManagerMixin {
    @Shadow
    private static SoundSystem sndSystem;
    @Shadow
    private static boolean loaded;
    @Shadow
    private final SoundPool cave = new SoundPool();
    @Shadow
    private final Random rand = new Random();
    @Shadow
    private GameSettings options;
    @Shadow
    private int ticksBeforeMusic = this.rand.nextInt(6000);

    @Inject(method = "playRandomMusicIfReady()V", at = @At("HEAD"), cancellable = true)
    private void dimensionMusic(CallbackInfo ci){
        if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){ // Skip Song
            sndSystem.stop("BgMusic");
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_N) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){ // Skip Song
            ticksBeforeMusic = 0;
        }
        Minecraft mc = Minecraft.getMinecraft(this);
        if (mc.theWorld.dimension == ModDimensions.dimensionMoon){
            if (!loaded || sndSystem == null || SoundTypeHelper.getEffectiveVolume(SoundType.MUSIC, options) == 0.0f) {
                return;
            }
            if (!sndSystem.playing("BgMusic") && !sndSystem.playing("streaming")) {
                if (this.ticksBeforeMusic > 0) {
                    --this.ticksBeforeMusic;
                    return;
                }
                SoundPoolEntry soundpoolentry = mc != null && mc.thePlayer != null && !mc.thePlayer.world.canBlockSeeTheSky(MathHelper.floor_double(mc.thePlayer.x), MathHelper.floor_double(mc.thePlayer.y), MathHelper.floor_double(mc.thePlayer.z)) ? this.cave.getRandomSound() : SoundContainer.soundPoolMoonMusic.getRandomSound();
                if (soundpoolentry != null) {
                    this.ticksBeforeMusic = this.rand.nextInt(6000) + 6000;
                    sndSystem.backgroundMusic("BgMusic", soundpoolentry.soundUrl, soundpoolentry.soundName, false);
                    sndSystem.setVolume("BgMusic", SoundTypeHelper.getEffectiveVolume(SoundType.MUSIC, options));
                    sndSystem.play("BgMusic");
                }
            }
            ci.cancel();
        }
    }

    @Redirect(method = "walkFolder(Ljava/io/File;Ljava/io/File;Lnet/minecraft/client/sound/SoundPool;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/SoundPool;addSound(Ljava/lang/String;Ljava/io/File;)Lnet/minecraft/client/sound/SoundPoolEntry;"))
    private static SoundPoolEntry dimensionMusic(SoundPool instance, String soundpoolentry, File e){
        if (e.getPath().contains("moon")){
            return SoundContainer.soundPoolMoonMusic.addSound(soundpoolentry, e);
        }
        return  instance.addSound(soundpoolentry, e);
    }

}
