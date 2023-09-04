package lexal.btb.mixin.core.entity;

import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.monster.EntitySkeleton;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = EntitySkeleton.class, remap = false)
public class EntitySkeletonMixin extends EntityMonster {
    public EntitySkeletonMixin(World world) {
        super(world);
    }

    @Override()
    public boolean canBreatheUnderwater(){
        return true;
    }

}
