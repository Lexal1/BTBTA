package lexal.btb;

import turniplabs.halplibe.helper.ItemHelper;
import useless.spawneggs.ItemSpawnEgg;

public class SpawnEggsModule {
    public static void onInitialize(){
        BTBTA.penguinSpawnEgg = ItemHelper.createItem(BTBTA.MODID, new ItemSpawnEgg("spawn.egg.penguin", BTBTA.itemIdTacker++, "Penguin", 0x29255b, 0xDEDEDE), "spawn.egg.penguin", "spawnEggDefault.png");
        BTBTA.spaceZombieSpawnEgg = ItemHelper.createItem(BTBTA.MODID, new ItemSpawnEgg("spawn.egg.spacezombie", BTBTA.itemIdTacker++, "spaceZombie", 0xffffff, 0xff9100), "spawn.egg.spacezombie", "spawnEggDefault.png");
    }
}
