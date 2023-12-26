package lexal.btb.mixin.Entity;

import lexal.btb.item.ModItems;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.monster.EntityCreeper;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemRecord;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.weather.Weather;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = EntityCreeper.class, remap = false)
public class EntityCreeperMixin extends EntityMonster {
    @Unique
    private static final List<Integer> baseDrops = new ArrayList<>();
    @Unique
    private static final List<Integer> rainDrops = new ArrayList<>();
    @Unique
    private static final List<Integer> desertDrops = new ArrayList<>();
    @Unique
    private static final List<Biome> desertBiomes = new ArrayList<>();

    static {
        baseDrops.add(Item.record13.id);
        baseDrops.add(Item.recordCat.id);
        baseDrops.add(Item.recordBlocks.id);
        baseDrops.add(Item.recordChirp.id);
        baseDrops.add(Item.recordFar.id);
        baseDrops.add(Item.recordMall.id);
        baseDrops.add(Item.recordMellohi.id);
        baseDrops.add(Item.recordStal.id);
        baseDrops.add(Item.recordStrad.id);
        baseDrops.add(Item.recordWard.id);
        baseDrops.add(Item.recordWait.id);

        rainDrops.add(ModItems.recordRain.id);
        rainDrops.add(ModItems.recordRain.id);
        rainDrops.add(ModItems.recordRain.id);
        rainDrops.add(ModItems.recordRain.id);
        rainDrops.add(ModItems.recordRain.id);
        rainDrops.add(ModItems.recordRain.id);

        desertDrops.add(ModItems.recordSahara.id);
        desertDrops.add(ModItems.recordSahara.id);
        desertDrops.add(ModItems.recordSahara.id);
        desertDrops.add(ModItems.recordSahara.id);
        desertDrops.add(ModItems.recordSahara.id);
        desertDrops.add(ModItems.recordSahara.id);

        desertBiomes.add(Biomes.OVERWORLD_DESERT);
        desertBiomes.add(Biomes.OVERWORLD_OUTBACK);
        desertBiomes.add(Biomes.OVERWORLD_OUTBACK_GRASSY);
    }

    public EntityCreeperMixin(World world) {
        super(world);
    }

    @Redirect(method = "onDeath(Lnet/minecraft/core/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/monster/EntityCreeper;spawnAtLocation(II)Lnet/minecraft/core/entity/EntityItem;"))
    private EntityItem creeperLootTable(EntityCreeper entityCreeper, int itemid, int stacksize){
        List<Integer> currentDropList = new ArrayList<>(baseDrops);
        Biome currentBiome = entityCreeper.world.getBlockBiome((int) entityCreeper.x, (int) entityCreeper.y, (int) entityCreeper.z);
        if (desertBiomes.contains(currentBiome)) {
            currentDropList.addAll(desertDrops);
        } else if (entityCreeper.world.getCurrentWeather() == Weather.overworldRain || entityCreeper.world.getCurrentWeather() == Weather.overworldStorm || entityCreeper.world.getCurrentWeather() == Weather.overworldSnow || entityCreeper.world.getCurrentWeather() == Weather.overworldWinterSnow){
            currentDropList.addAll(rainDrops);
        }
        return entityCreeper.spawnAtLocation(currentDropList.get(random.nextInt(currentDropList.size())), stacksize);
    }
}
