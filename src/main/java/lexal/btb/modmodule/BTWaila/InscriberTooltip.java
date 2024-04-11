package lexal.btb.modmodule.BTWaila;

import lexal.btb.block.tile_entity.TileEntityInscriber;
import net.minecraft.core.item.ItemStack;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;
import toufoumaster.btwaila.util.ProgressBarOptions;

public class InscriberTooltip extends TileTooltip<TileEntityInscriber> {
    @Override
    public void initTooltip() {
        addClass(TileEntityInscriber.class);
    }
    @Override
    public void drawAdvancedTooltip(TileEntityInscriber inscriber, AdvancedInfoComponent advancedInfoComponent) {
        ItemStack input = inscriber.getStackInSlot(0);
        ItemStack fuel = inscriber.getStackInSlot(1);
        ItemStack output = inscriber.getStackInSlot(2);
        ProgressBarOptions options = (new ProgressBarOptions()).setText("Progress: ");
        advancedInfoComponent.drawProgressBarWithText(inscriber.getInscribeProgressScaled(100), 100, options, 0);
        ItemStack[] stacks = new ItemStack[]{input, fuel, output};
        advancedInfoComponent.drawItemList(stacks, 0);
    }
}
