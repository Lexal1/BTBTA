package lexal.btb;

import lexal.btb.block.*;
import lexal.btb.entity.ModEntities;
import lexal.btb.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.AchievementHelper;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.achievements.AchievementPage;

import java.util.Properties;

public class BTBTA implements ModInitializer {
    
    public static final String MOD_ID = "btb";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static int GUI_ID_INSCRIBER = 9;

    private void handleConfig() {
        Properties prop = new Properties();
        prop.setProperty("starting_block_id","3000");
        prop.setProperty("starting_item_id","20000");
        prop.setProperty("starting_spawneggs_item_id","20200");
        prop.setProperty("gui_inscriber_id", "9");
        ConfigHandler config = new ConfigHandler(MOD_ID,prop);
        UtilIdRegistrar.initIds(
                config.getInt("starting_block_id"),
                config.getInt("starting_item_id"),
                config.getInt("starting_spawneggs_item_id"));
        config.updateConfig();
        GUI_ID_INSCRIBER = config.getInt("gui_inscriber_id");
    }
    public static final boolean noNameDyesModPresent = FabricLoader.getInstance().isModLoaded("nonamedyes");
    public static AchievementPage BTBACHIEVEMENTS;

    @Override
    public void onInitialize() {
        LOGGER.info("btbta loading! watch out for bugs");
        handleConfig();

        ModBlocks.register();
        ModItems.register();
        ModEntities.register();

        SoundHelper.addStreaming(MOD_ID, "rain.wav");
        SoundHelper.addStreaming(MOD_ID, "blank.wav");
        SoundHelper.addStreaming(MOD_ID, "so long.wav");
        SoundHelper.addStreaming(MOD_ID, "sahara.ogg");

//        if (noNameDyesModPresent){
//            new NoNameDyes().onInitialize();
//        }
        
        BTBACHIEVEMENTS = new ModAchievements();
        AchievementHelper.addPage(BTBACHIEVEMENTS);
        LOGGER.info("btbta loaded successfully!");
    }
}
