package lexal.btb.world;

import lexal.btb.entity.EntitySpaceSkeleton;
import lexal.btb.entity.EntitySpaceZombie;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.entity.monster.EntitySkeleton;
import net.minecraft.core.world.biome.Biome;

public class BiomeMoon extends Biome {
    public BiomeMoon() {
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpaceSkeleton.class, 10));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpaceZombie.class, 10));
    }
    @Override
    public int getSkyColor(float temperature) {
        return 0;
    }
}
