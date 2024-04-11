package lexal.btb.world.biomes;

import lexal.btb.entity.EntityPenguin;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.world.biome.Biome;

public class BiomeGlacier extends Biome {
    public BiomeGlacier(String key) {
        super(key);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPenguin.class, 102));
    }
}
