package lexal.btb.entity;

import lexal.btb.entity.renderer.ModelPenguin;
import lexal.btb.entity.renderer.PenguinRenderer;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.core.Global;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.EntityHelper;

public class ModEntities {
    public static void register() {
        EntityHelper.createEntity(EntityPenguin.class, 102, "Penguin", () -> new PenguinRenderer(new ModelPenguin(), .25F));
    }

    public static void registerGui() {
        MobInfoRegistry.register(EntityPenguin.class, "btb.penguin.name", "btb.penguin.desc", 10, 10, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(Item.featherChicken.getDefaultStack(), 0.66f, 1, 2)});
    }
}
