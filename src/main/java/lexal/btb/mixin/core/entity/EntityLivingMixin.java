package lexal.btb.mixin.core.entity;

import com.mojang.nbt.CompoundTag;
import lexal.btb.block.ModMaterials;
import lexal.btb.world.IGravity;
import lexal.btb.world.worldType.WorldTypeMoon;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityLiving.class, remap = false)
public class EntityLivingMixin extends Entity {
    @Unique
    private double gravityScale;
    public EntityLivingMixin(World world) {
        super(world);
    }

    @Redirect(method = "baseTick()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/EntityLiving;isUnderLiquid(Lnet/minecraft/core/block/material/Material;)Z"))
    public boolean moonSuffocation(EntityLiving living, Material material){
        if (living.isUnderLiquid(ModMaterials.gas)){
            return false;
        }
        return (living.isUnderLiquid(material) || (living.world.getWorldType() instanceof WorldTypeMoon)); // Suffocate underwater or on moon
    }


    @Inject(method = "moveEntityWithHeading(FF)V", at = @At("HEAD"))
    private void getGravity(float moveStrafing, float moveForward, CallbackInfo cbi){
        gravityScale = 1f;
        if (world.getWorldType() instanceof IGravity){
            gravityScale = ((IGravity)world.worldType).getGravityScalar();
        }
    }

    @Redirect(method = "moveEntityWithHeading(FF)V", at = @At(value = "FIELD", target = "Lnet/minecraft/core/entity/EntityLiving;yd:D", opcode = Opcodes.PUTFIELD))
    private void entityGravity(EntityLiving entity, double yd){ //Probably terrible way of modifying gravity by a scalar
        double offset = -(yd - this.yd);
        if ((0.021 > offset && offset > 0.019) || (0.081 > offset && offset > 0.079)){ // If falling in water or in air
            entity.yd -= offset * gravityScale;
        } else if ((-0.251 < yd && yd < -0.249)) { // Terminal velocity
            entity.yd = yd * gravityScale;
        } else { // Else regular behavior
            entity.yd = yd;
        }
    }

    @ModifyVariable(method = "causeFallDamage(F)V", at = @At(value = "STORE"), ordinal = 0)
    private int changeFallDamage(int i){
        return (int)((i * gravityScale) - (3/gravityScale) + 3);
    }
    @Shadow
    protected void init() {
    }
    @Shadow
    public void readAdditionalSaveData(CompoundTag compoundTag) {
    }
    @Shadow
    public void addAdditionalSaveData(CompoundTag compoundTag) {
    }
}
