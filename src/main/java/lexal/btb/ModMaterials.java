package lexal.btb;

import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.MaterialColor;
import net.minecraft.core.block.material.MaterialLogic;

public class ModMaterials {
    public static final Material gas = new MaterialAccess(MaterialColor.none).replaceable().notSolidBlocking().notSolid().notBlocksLight();
}
