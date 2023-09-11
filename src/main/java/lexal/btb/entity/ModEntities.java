package lexal.btb.entity;

import lexal.btb.entity.renderer.ModelPenguin;
import lexal.btb.entity.renderer.PenguinRenderer;
import turniplabs.halplibe.helper.EntityHelper;

public class ModEntities {

    public static void register() {
        EntityHelper.createEntity(EntityPenguin.class, new PenguinRenderer(new ModelPenguin(), .25F), 902, "Penguin");
    }
}
