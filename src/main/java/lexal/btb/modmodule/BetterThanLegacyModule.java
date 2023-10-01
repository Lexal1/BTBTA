package lexal.btb.modmodule;

import lexal.btb.BTBTA;
import useless.legacyui.Settings.ModSettings;

public class BetterThanLegacyModule {
    public static void onInitialize(){
        BTBTA.GUI_LABEL_COLOR = ModSettings.legacyOptions.getGuiLabelColor().value.value;
    }
}
