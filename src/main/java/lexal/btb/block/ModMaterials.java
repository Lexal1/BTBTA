package lexal.btb.block;

import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.MaterialColor;

public class ModMaterials {
    public static final Material gas = new MaterialAccess(MaterialColor.none).replaceable().notSolidBlocking().notSolid().notBlocksLight().destroyOnPush();
}
