package lexal.btb;

import lexal.btb.block.ModBlocks;
import lexal.btb.entity.EntityPenguin;
import lexal.btb.entity.ModEntities;
import lexal.btb.item.ModItems;
import lexal.btb.crafting.ModRecipes;
import lexal.btb.world.biomes.BiomeGlacier;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.enums.EnumCreatureType;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.AchievementHelper;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.achievements.AchievementPage;

import java.util.List;
import java.util.Properties;

public class BTBTA implements GameStartEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "btb";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static int GUI_ID_INSCRIBER;
    public static ConfigHandler config;
    static {
        Properties prop = new Properties();
        prop.setProperty("starting_block_id","3000");
        prop.setProperty("starting_item_id","20000");
        prop.setProperty("gui_inscriber_id", "9");
        config = new ConfigHandler(MOD_ID,prop);
        config.updateConfig();
        GUI_ID_INSCRIBER = config.getInt("gui_inscriber_id");
    }

    public static final boolean noNameDyesModPresent = FabricLoader.getInstance().isModLoaded("nonamedyes");
    public static AchievementPage BTBACHIEVEMENTS;

    @Override
    public void beforeGameStart() {
        LOGGER.info("btbta loading! watch out for bugs");

        ModBlocks.register();
        ModItems.register();
        ModEntities.register();

        List<SpawnListEntry> creatures = Biomes.OVERWORLD_GLACIER.getSpawnableList(EnumCreatureType.creature);
        for (SpawnListEntry creature : creatures){
            creature.spawnFrequency /= 2;
        }
        creatures.add(new SpawnListEntry(EntityPenguin.class, 102));

//        if (noNameDyesModPresent){
//            new NoNameDyes().onInitialize();
//        }

        BTBACHIEVEMENTS = new ModAchievements();
        AchievementHelper.addPage(BTBACHIEVEMENTS);
        LOGGER.info("btbta loaded successfully!");
    }

    @Override
    public void afterGameStart() {
    }

    @Override
    public void beforeClientStart() {
        SoundHelper.addStreaming(MOD_ID, "rain.wav");
        SoundHelper.addStreaming(MOD_ID, "blank.wav");
        SoundHelper.addStreaming(MOD_ID, "so long.wav");
        SoundHelper.addStreaming(MOD_ID, "sahara.ogg");
    }

    @Override
    public void afterClientStart() {
        LOGGER.info("btbta loaded successfully! :3");
        ModEntities.registerGui();
        ModRecipes.postInit();
    }
}
