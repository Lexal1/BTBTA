package lexal.btb.world;

import lexal.btb.entity.EntityPenguin;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.world.biome.Biome;

public class BiomeGlacier extends Biome {
    public BiomeGlacier() {
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPenguin.class, 102));
    }
}
