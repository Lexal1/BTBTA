package lexal.btb.modmodule;

import lexal.btb.BTBTA;
import lexal.btb.item.ModItems;
import turniplabs.halplibe.helper.ItemHelper;
import useless.spawneggs.ItemSpawnEgg;

public class SpawnEggsModule {
    public static void onInitialize(){
        ModItems.penguinSpawnEgg = ItemHelper.createItem(BTBTA.MOD_ID, new ItemSpawnEgg("spawn.egg.penguin", ModItems.penguinSpawnEgg.id,
                "Penguin", 0x29255b, 0xDEDEDE), "spawn.egg.penguin", "spawnEggDefault.png");
        ModItems.spaceZombieSpawnEgg = ItemHelper.createItem(BTBTA.MOD_ID, new ItemSpawnEgg("spawn.egg.spacezombie", ModItems.spaceZombieSpawnEgg.id,
                "spaceZombie", 0xffffff, 0xff9100), "spawn.egg.spacezombie", "spawnEggDefault.png");
        ModItems.spaceSkeletonSpawnEgg = ItemHelper.createItem(BTBTA.MOD_ID, new ItemSpawnEgg("spawn.egg.spaceskeleton", ModItems.spaceSkeletonSpawnEgg.id,
                "spaceSkeleton", 0xC1C1C1, 0xff9100), "spawn.egg.spaceskeleton", "spawnEggDefault.png");
    }
}
