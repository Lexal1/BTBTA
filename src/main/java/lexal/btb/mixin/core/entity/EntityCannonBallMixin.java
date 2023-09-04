package lexal.btb.mixin.core.entity;

import com.mojang.nbt.CompoundTag;
import lexal.btb.world.IGravity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.projectile.EntityCannonball;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityCannonball.class, remap = false)
public class EntityCannonBallMixin extends Entity {
    public EntityCannonBallMixin(World world) {
        super(world);
    }

    @Inject(method = "tick()V", at = @At("TAIL"))
    private void scaleGravity(CallbackInfo ci){
        if (world.getWorldType() instanceof IGravity){
            float gravityScale = ((IGravity)world.getWorldType()).getGravityScalar();
            this.yd +=  0.09f - (0.09f * gravityScale);
        }

    }

    @Shadow
    protected void init() {

    }

    @Shadow
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Shadow
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }
}
