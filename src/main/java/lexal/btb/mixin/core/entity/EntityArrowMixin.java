package lexal.btb.mixin.core.entity;

import com.mojang.nbt.CompoundTag;
import lexal.btb.world.IGravity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.projectile.EntityArrow;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityArrow.class, remap = false)
public class EntityArrowMixin extends Entity {
    @Shadow protected float arrowGravity;

    public EntityArrowMixin(World world) {
        super(world);
    }

    @Inject(method = "init()V", at = @At("TAIL"))
    private void changeGravity(CallbackInfo ci){
        if (this.world.getWorldType() instanceof IGravity){
            this.arrowGravity *= ((IGravity)this.world.getWorldType()).getGravityScalar();
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
