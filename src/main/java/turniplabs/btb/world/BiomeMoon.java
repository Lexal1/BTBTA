package turniplabs.btb.world;

import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.entity.monster.EntityGhast;
import net.minecraft.core.entity.monster.EntityPigZombie;
import net.minecraft.core.entity.monster.EntitySkeleton;
import net.minecraft.core.world.biome.Biome;
import turniplabs.btb.entity.EntitySpaceZombie;

public class BiomeMoon extends Biome {
    public BiomeMoon() {
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpaceZombie.class, 10));
    }
    @Override
    public int getSkyColor(float temperature) {
        return 0;
    }
}
