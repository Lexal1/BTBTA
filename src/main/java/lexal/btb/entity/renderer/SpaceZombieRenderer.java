package lexal.btb.entity.renderer;

import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.model.ModelZombie;
import net.minecraft.core.entity.monster.EntityArmoredZombie;

public class SpaceZombieRenderer
        extends MobRenderer<EntityArmoredZombie> {
    private final ModelZombie modelArmorChestplate;
    private final ModelZombie modelArmor;

    public SpaceZombieRenderer(ModelZombie model, float shadowSize) {
        super(model, shadowSize);
        this.modelBipedMain = model;
        this.modelArmorChestplate = new ModelZombie(1.0f);
        this.modelArmor = new ModelZombie(0.5f);
    }

    protected boolean setArmorModel(EntityArmoredZombie zombie, int renderPass, float renderPartialTicks) {
        this.loadTexture("/armor/space_" + (renderPass != 2 ? 1 : 2) + ".png");
        ModelZombie model = renderPass != 2 ? this.modelArmorChestplate : this.modelArmor;
        model.bipedHead.showModel = renderPass == 0;
        model.bipedHeadOverlay.showModel = renderPass == 0;
        model.bipedBody.showModel = renderPass == 1 || renderPass == 2;
        model.bipedRightArm.showModel = renderPass == 1;
        model.bipedLeftArm.showModel = renderPass == 1;
        model.bipedRightLeg.showModel = renderPass == 2 || renderPass == 3;
        model.bipedLeftLeg.showModel = renderPass == 2 || renderPass == 3;
        for (int i = 0; i < 4; ++i) {
            if (zombie.health > zombie.armorBreakPoints[i]) continue;
            this.hideArmorPiece(zombie.armorBreakOrder[i]);
        }
        this.setRenderPassModel(model);
        return true;
    }

    @Override
    protected boolean shouldRenderPass(EntityArmoredZombie zombie, int renderPass, float renderPartialTicks) {
        return this.setArmorModel(zombie, renderPass, renderPartialTicks);
    }

    private void hideArmorPiece(int piece) {
        if (piece == 0) {
            this.modelArmorChestplate.bipedHead.showModel = false;
            this.modelArmorChestplate.bipedHeadOverlay.showModel = false;
        } else if (piece == 1) {
            this.modelArmorChestplate.bipedBody.showModel = false;
            this.modelArmorChestplate.bipedLeftArm.showModel = false;
            this.modelArmorChestplate.bipedRightArm.showModel = false;
        } else if (piece == 2) {
            this.modelArmor.bipedBody.showModel = false;
            this.modelArmor.bipedLeftLeg.showModel = false;
            this.modelArmor.bipedRightLeg.showModel = false;
        } else if (piece == 3) {
            this.modelArmorChestplate.bipedLeftLeg.showModel = false;
            this.modelArmorChestplate.bipedRightLeg.showModel = false;
        }
    }
}

