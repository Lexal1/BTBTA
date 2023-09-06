package lexal.btb;

import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.MaterialColor;

public class MaterialAccess extends Material {
    private boolean flammable;
    private boolean replaceable;
    private boolean notSolidBlocking;
    private boolean alwaysDestroyable = true;
    private boolean solid = true;
    private boolean litByBlocks = true;
    private boolean motionBlocks = true;
    private boolean liquid = false;
    private int pushReaction;
    public MaterialAccess(MaterialColor color) {
        super(color);
    }
    public boolean isLiquid() {
        return liquid;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean blocksLight() {
        return litByBlocks;
    }

    public boolean blocksMotion() {
        return motionBlocks;
    }

    public MaterialAccess notSolidBlocking() {
        this.notSolidBlocking = true;
        return this;
    }

    public MaterialAccess notAlwaysDestroyable() {
        this.alwaysDestroyable = false;
        return this;
    }

    public MaterialAccess flammable() {
        this.flammable = true;
        return this;
    }

    public boolean isFlammable() {
        return this.flammable;
    }

    public MaterialAccess replaceable() {
        this.replaceable = true;
        return this;
    }

    public boolean isReplaceable() {
        return this.replaceable;
    }

    public boolean isSolidBlocking() {
        if (this.notSolidBlocking) {
            return false;
        }
        return this.blocksMotion();
    }

    public boolean isAlwaysDestroyable() {
        return this.alwaysDestroyable;
    }

    public int getPushReaction() {
        return this.pushReaction;
    }

    protected MaterialAccess destroyOnPush() {
        this.pushReaction = 1;
        return this;
    }

    protected MaterialAccess notPushable() {
        this.pushReaction = 2;
        return this;
    }
    public MaterialAccess notSolid(){
        solid = false;
        return this;
    }
    public MaterialAccess notBlocksLight(){
        litByBlocks = false;
        return this;
    }
    public MaterialAccess notBlocksMotions(){
        motionBlocks = false;
        return this;
    }
    public MaterialAccess liquid(){
        liquid = true;
        return this;
    }
}
