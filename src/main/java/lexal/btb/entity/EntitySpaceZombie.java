package lexal.btb.entity;

import net.minecraft.core.entity.monster.EntityArmoredZombie;
import net.minecraft.core.world.World;

import java.util.Random;

public class EntitySpaceZombie extends EntityArmoredZombie {
    public EntitySpaceZombie(World world) {
        super(world);
        this.skinName = "zombie";
    }

    @Override
    public boolean canBreatheUnderwater(){
        return true;
    }
    @Override
    public String getEntityTexture() {
        return getDefaultEntityTexture();
    }

    public String getDefaultEntityTexture() {
        return "/mob/" + this.skinName + "/2.png";
    }
}
