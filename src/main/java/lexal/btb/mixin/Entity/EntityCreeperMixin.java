package lexal.btb.mixin.Entity;

import lexal.btb.item.ModItems;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.monster.EntityCreeper;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemRecord;
import net.minecraft.core.world.World;
import net.minecraft.core.world.weather.Weather;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EntityCreeper.class, remap = false)
public class EntityCreeperMixin extends EntityMonster {
    @Unique
    private static int[] normalTable = new int[]{Item.record13.id, Item.recordCat.id, Item.recordBlocks.id, Item.recordChirp.id, Item.recordFar.id, Item.recordMall.id, Item.recordMellohi.id, Item.recordStal.id, Item.recordStrad.id, Item.recordWard.id, Item.recordWait.id};
    @Unique
    private static int[] rainingTable = new int[]{Item.record13.id, Item.recordCat.id, Item.recordBlocks.id, Item.recordChirp.id, Item.recordFar.id, Item.recordMall.id, Item.recordMellohi.id, Item.recordStal.id, Item.recordStrad.id, Item.recordWard.id, Item.recordWait.id, ModItems.recordRain.id, ModItems.recordRain.id, ModItems.recordRain.id, ModItems.recordRain.id};

    public EntityCreeperMixin(World world) {
        super(world);
    }

    @Redirect(method = "onDeath(Lnet/minecraft/core/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/monster/EntityCreeper;spawnAtLocation(II)Lnet/minecraft/core/entity/EntityItem;"))
    private EntityItem creeperLootTable(EntityCreeper instance, int itemid, int stacksize){
        int id = Item.record13.id;
        if (instance.world.currentWeather == Weather.overworldRain || instance.world.currentWeather == Weather.overworldStorm || instance.world.currentWeather == Weather.overworldSnow || instance.world.currentWeather == Weather.overworldWinterSnow){
            id = rainingTable[random.nextInt(rainingTable.length)];
        } else {
            id = normalTable[random.nextInt(normalTable.length)];
        }
        return instance.spawnAtLocation(id, stacksize);
    }
}
