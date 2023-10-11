package lexal.btb.modmodule.BTWaila;

import lexal.btb.BTBTA;
import lexal.btb.block.tile_entity.TileEntityInscriber;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.item.ItemStack;
import toufoumaster.btwaila.BTWaila;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;
import toufoumaster.btwaila.util.ProgressBarOptions;

public class InscriberTooltip implements IBTWailaCustomBlockTooltip {
    public void addTooltip() {
        BTWaila.LOGGER.info("Adding tooltips for: " + this.getClass().getSimpleName());
        TooltipGroup tooltipGroup = new TooltipGroup(BTBTA.MOD_ID, TileEntityInscriber.class, this);
        tooltipGroup.addTooltip(TileEntityInscriber.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityInscriber inscriber = (TileEntityInscriber) tileEntity;
        ItemStack input = inscriber.getStackInSlot(0);
        ItemStack fuel = inscriber.getStackInSlot(1);
        ItemStack output = inscriber.getStackInSlot(2);
        ProgressBarOptions options = (new ProgressBarOptions()).setText("Progress: ");
        guiBlockOverlay.drawProgressBarWithText(inscriber.getInscribeProgressScaled(100), 100, options, 32);
        ItemStack[] stacks = new ItemStack[]{input, fuel, output};
        guiBlockOverlay.drawItemList(stacks, 0);
    }
}
