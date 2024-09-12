package lexal.btb.entity;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;

public class EntityPenguin extends EntityPet {
    public float flapAngle = 0.0F;
    public float destPos = 0.0F;
    public float oldDestPos;
    public float oldFlapAngle = 0.0F;
    public float flapRate = 1.0F;

    private boolean sliding = false;

    public EntityPenguin(World world) {
        super(world);
        this.tameItemID = Item.foodFishRaw.id;
        //this.skinName = "penguin";
        this.setSize(8f/16f, 14f/16f);
    }
    public String getEntityTexture() {return "/assets/btb/textures/entity/penguin/penguin2.png";}
    public String getDefaultEntityTexture() {
        return "/assets/btb/textures/entity/penguin/penguin2.png";
    }
    public String getLivingSound() {
        return "mob.chicken";
    }

    protected String getHurtSound() {
        return "mob.chickenhurt";
    }

    protected String getDeathSound() {
        return "mob.chickenhurt";
    }

    @Override
    protected int getDropItemId() {
        return Item.featherChicken.id;
    }
    @Override
    public boolean getCanSpawnHere() {
        int z;
        int y;
        int x = MathHelper.floor_double(this.x);
        int id = this.world.getBlockId(x, (y = MathHelper.floor_double(this.bb.minY)) - 1, z = MathHelper.floor_double(this.z));
        if (Block.blocksList[id] != null) {
            return (Block.blocksList[id].hasTag(BlockTags.PASSIVE_MOBS_SPAWN) || Block.blocksList[id] == Block.blockSnow || Block.blocksList[id] == Block.layerSnow) && this.world.getFullBlockLightValue(x, y, z) > 8 && baseCanSpawn();
        }
        return false;
    }
    private boolean baseCanSpawn(){
        int i = MathHelper.floor_double(this.x);
        int j = MathHelper.floor_double(this.bb.minY);
        int k = MathHelper.floor_double(this.z);
        return baseierCanSpawn() && this.getBlockPathWeight(i, j, k) >= 0.0f;
    }
    private boolean baseierCanSpawn(){
        int blockZ;
        int blockY;
        int blockX = MathHelper.floor_double(this.x);
        if (Block.hasTag(this.world.getBlockId(blockX, blockY = MathHelper.floor_double(this.bb.minY), blockZ = MathHelper.floor_double(this.z)), BlockTags.PREVENT_MOB_SPAWNS)) {
            return false;
        }
        return this.world.checkIfAABBIsClear(this.bb) && this.world.getCubes(this, this.bb).size() == 0 && !this.world.getIsAnyLiquid(this.bb);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.oldFlapAngle = this.flapAngle;
        this.oldDestPos = this.destPos;
        this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3);
        if (this.destPos < 0.0F) {
            this.destPos = 0.0F;
        }

        if (this.destPos > 1.0F) {
            this.destPos = 1.0F;
        }

        if (!this.onGround && this.flapRate < 1.0F) {
            this.flapRate = 1.0F;
        }

        this.flapRate = (float) ((double) this.flapRate * 0.9);

        // Slow falling
        if (!this.onGround && this.yd < 0.0) {
            this.yd *= 0.6;
        }

        this.flapAngle += this.flapRate * 2.0F;
    }

    public boolean isSliding(){
        return sliding;
    }

    public void setSliding(boolean flag){
        sliding = flag;
        if (isSliding()){
            setSitting(true);
        }

    }
    @Override
    public boolean interact(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (this.isTamed()) {
            if (itemstack != null && itemstack.itemID == Item.stick.id && !this.isAngry()) {
                if (!this.world.isClientSide) {
                    setSliding(!isSliding());
                    //this.world.sendTrackedEntityStatusUpdatePacket(this, (byte) 7);
                }

                return true;
            }
        }
        return super.interact(entityplayer);


    }
}

