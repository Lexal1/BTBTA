package turniplabs.btb.entity.renderer;

import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.core.util.helper.MathHelper;
import turniplabs.btb.entity.EntityPenguin;

public class PenguinRenderer extends LivingRenderer<EntityPenguin> {
    public PenguinRenderer(ModelBase modelbase, float f) {
        super(modelbase, f);
    }

    public void renderPenguin(EntityPenguin entity, double d, double d1, double d2, float f, float f1) {
        super.doRenderLiving(entity, d, d1, d2, f, f1);
    }

    public void doRenderLiving(EntityPenguin entity, double x, double y, double z, float yaw, float renderPartialTicks) {
        this.renderPenguin(entity, x, y, z, yaw, renderPartialTicks);
    }

    public void doRender(EntityPenguin entity, double x, double y, double z, float yaw, float renderPartialTicks) {
        this.renderPenguin(entity, x, y, z, yaw, renderPartialTicks);
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
