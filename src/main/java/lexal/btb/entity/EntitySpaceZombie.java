package lexal.btb.entity;

import net.minecraft.core.entity.monster.EntityArmoredZombie;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.Random;

public class EntitySpaceZombie extends EntityArmoredZombie {
    private static final ItemStack defaultHeldItem;
    private final boolean isHoldingSword;
    public EntitySpaceZombie(World world) {
        super(world);
        Random rand = new Random();
        this.isHoldingSword = rand.nextInt(3) == 0;
        this.skinName = "zombie";
        this.health = 50;
        this.attackStrength = this.isHoldingSword ? 5 : 2;
    }
    public EntitySpaceZombie(World world, boolean isHoldingSword) {
        super(world);
        this.isHoldingSword = isHoldingSword;
        this.skinName = "zombie";
        this.health = 50;
        this.attackStrength = this.isHoldingSword ? 5 : 2;
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
    public ItemStack getHeldItem() {
        return this.isHoldingSword ? defaultHeldItem : null;
    }

    static {
        defaultHeldItem = new ItemStack(Item.toolSwordDiamond, 1);
    }
}

