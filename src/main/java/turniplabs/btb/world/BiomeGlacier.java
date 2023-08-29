package turniplabs.btb.world;

import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.world.biome.Biome;
import turniplabs.btb.entity.EntityPenguin;

public class BiomeGlacier extends Biome {
    public BiomeGlacier() {
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPenguin.class, 102));
    }
}
