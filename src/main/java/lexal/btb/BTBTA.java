package lexal.btb;

import lexal.btb.block.*;
import lexal.btb.crafting.ModCraftingManager;
import lexal.btb.entity.ModEntities;
import lexal.btb.item.ModItems;
import lexal.btb.modmodule.SpawnEggsModule;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.AchievementHelper;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.achievements.AchievementPage;
import useless.prismaticlibe.helper.ModCheckHelper;
import useless.prismaticlibe.helper.SoundHelper;

import java.util.Properties;

public class BTBTA implements ModInitializer {
    public static final String MOD_ID = "btb";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final AchievementPage BTBACHIEVEMENTS = new ModAchievements();

    private void handleConfig() {
        Properties prop = new Properties();
        prop.setProperty("starting_block_id","3000");
        prop.setProperty("starting_item_id","20000");
        prop.setProperty("starting_spawneggs_item_id","20200");
        ConfigHandler config = new ConfigHandler(MOD_ID,prop);
        UtilIdRegistrar.initIds(
                config.getInt("starting_block_id"),
                config.getInt("starting_item_id"),
                config.getInt("starting_spawneggs_item_id"));
        config.updateConfig();
    }
    public static final boolean spawnEggsModPresent = ModCheckHelper.checkForMod("spawneggs", ">=1.1.0");

    @Override
    public void onInitialize() {
        LOGGER.info("btbta loading! watch out for bugs");
        AchievementHelper.addPage(BTBACHIEVEMENTS);
        handleConfig();

        ModBlockTags.register();

        ModBlocks.register();
        ModItems.register();
        ModCraftingManager.register();


        ModEntities.register();

        SoundHelper.addMusic(MOD_ID, "moon0.wav");
        SoundHelper.addMusic(MOD_ID, "moon1.wav");
        SoundHelper.addMusic(MOD_ID, "moon2.wav");
        SoundHelper.addMusic(MOD_ID, "moon3.wav");
        SoundHelper.addMusic(MOD_ID, "moon4.wav");

        if (spawnEggsModPresent){
            SpawnEggsModule.onInitialize();;
        }
        LOGGER.info("btbta loaded successfully!");
    }
}
