package lexal.btb.world;

import lexal.btb.block.ModBlocks;
import lexal.btb.world.worldType.ModWorldType;
import net.minecraft.core.world.Dimension;

public class ModDimensions {
    public static final Dimension dimensionMoon = new Dimension("moon", Dimension.overworld, 3f, ModBlocks.portalmoon.id).setDefaultWorldType(ModWorldType.worldTypeMoon);
    static
    {
        Dimension.registerDimension(3, dimensionMoon);
    }
    public static void register() {}
}
