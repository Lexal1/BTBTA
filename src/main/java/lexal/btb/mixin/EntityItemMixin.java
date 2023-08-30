package lexal.btb.mixin;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import lexal.btb.world.IGravity;

@Mixin(value = EntityItem.class, remap = false)
public class EntityItemMixin extends Entity {
    public EntityItemMixin(World world) {
        super(world);
    }

    @Redirect(method = "tick()V", at = @At(value = "FIELD", target = "Lnet/minecraft/core/entity/EntityItem;yd:D", opcode = Opcodes.PUTFIELD, ordinal = 0))
    private void itemGravity(EntityItem item, double yd){
        double offset = -(yd - this.yd);
        double gravity = 1d;
        if (item.world.getWorldType() instanceof IGravity){
            gravity = ((IGravity)item.world.getWorldType()).getGravityScalar();
        }
        item.yd -= offset * gravity;
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
