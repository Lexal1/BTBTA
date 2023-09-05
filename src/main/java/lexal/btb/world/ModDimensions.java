package lexal.btb.world;

import lexal.btb.block.ModBlocks;
import lexal.btb.world.worldType.ModWorldType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.PortalHandler;
import net.minecraft.core.world.World;

public class ModDimensions {
    public static final Dimension dimensionMoon = new Dimension("moon", Dimension.overworld, 3f, ModBlocks.portalmoon.id).setDefaultWorldType(ModWorldType.worldTypeMoon);
    static
    {
        Dimension.registerDimension(3, dimensionMoon);
    }
    public static void register() {}

    public static void dimensionShift(int targetDimension){
        Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
        EntityPlayer player = mc.thePlayer;
        World world = mc.theWorld;

        Dimension lastDim = Dimension.getDimensionList().get(player.dimension);
        Dimension newDim = Dimension.getDimensionList().get(targetDimension);
        System.out.println("Switching to dimension \"" + newDim.getTranslatedName() + "\"!!");
        player.dimension = targetDimension;
        world.setEntityDead(player);
        player.removed = false;
        double d = player.x;
        double d1 = player.z;
        double newY = world.getHeightBlocks()+1;
        player.moveTo(d *= (double)net.minecraft.core.world.Dimension.getCoordScale(lastDim, newDim), newY, d1 *= (double)net.minecraft.core.world.Dimension.getCoordScale(lastDim, newDim), player.yRot, player.xRot);
        if (player.isAlive()) {
            world.updateEntityWithOptionalForce(player, false);
        }
        if (player.isAlive()) {
            world.updateEntityWithOptionalForce(player, false);
        }
        world = new World(world, newDim);
        if (newDim == lastDim.homeDim) {
            mc.changeWorld(world, "Leaving " + lastDim.getTranslatedName(), player);
        } else {
            mc.changeWorld(world, "Entering " + newDim.getTranslatedName(), player);
        }
        player.world = world;
        if (player.isAlive()) {
            player.moveTo(d, newY, d1, player.yRot, player.xRot);
            world.updateEntityWithOptionalForce(player, false);
            int newX = (int)((player.x / lastDim.worldScale) * newDim.worldScale);
            int newZ = (int)((player.z / lastDim.worldScale) * newDim.worldScale);
            player.moveTo(newX, world.worldType.getMaxY()+1, newZ, player.yRot, 0.0f);
        }

    }
}
