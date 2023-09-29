package lexal.btb.mixin;

import lexal.btb.block.TileEntityInscriber;
import lexal.btb.entity.IPlayerDisplay;
import net.minecraft.core.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin implements IPlayerDisplay {
    @Override
    public void displayGUIInscriber(TileEntityInscriber tileEntityInscriber) {

    }
}
