package lexal.btb.entity.renderer;

import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.util.helper.MathHelper;
import lexal.btb.entity.EntityPenguin;

public class PenguinRenderer extends LivingRenderer<EntityPenguin> {
    public PenguinRenderer(ModelBase modelbase, float f) {
        super(modelbase, f);
    }
    protected float getWingRotation(EntityPenguin entity, float ticksPassed) {
        float flappingProgress = entity.oldFlapAngle + (entity.flapAngle - entity.oldFlapAngle) * ticksPassed;
        float rotationMagnitude = entity.oldDestPos + (entity.destPos - entity.oldDestPos) * ticksPassed;
        return (MathHelper.sin(flappingProgress) + 1.0F) * rotationMagnitude;
    }
    protected float ticksExisted(EntityPenguin entity, float renderPartialTicks) {
        return this.getWingRotation(entity, renderPartialTicks);
    }
}
