package lexal.btb.modmodule.BTWaila;

import org.slf4j.Logger;
import toufoumaster.btwaila.BTWailaCustomTootltipPlugin;

public class BTWailaPlugin implements BTWailaCustomTootltipPlugin {
    @Override
    public void initializePlugin(Logger logger) {
        logger.info("Loading BTBTA Tooltips");
        new InscriberTooltip().addTooltip();
    }
}
