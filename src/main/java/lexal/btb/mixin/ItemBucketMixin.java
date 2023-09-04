package lexal.btb.mixin;

import lexal.btb.BTBTA;
import net.minecraft.core.HitResult;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemBucket;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemBucket.class, remap = false)
public class ItemBucketMixin {
    @Shadow
    private int idToPlace;
    @Unique
    private HitResult result;

    @Redirect(method = "onItemRightClick(Lnet/minecraft/core/item/ItemStack;Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/EntityPlayer;)Lnet/minecraft/core/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;checkBlockCollisionBetweenPoints(Lnet/minecraft/core/util/phys/Vec3d;Lnet/minecraft/core/util/phys/Vec3d;Z)Lnet/minecraft/core/HitResult;"))
    private HitResult stealHitResult(World world, Vec3d start, Vec3d end, boolean shouldCollideWithFluids){
        result = world.checkBlockCollisionBetweenPoints(start, end, this.idToPlace == 0);
        return result;
    }
    @Inject(method = "onItemRightClick(Lnet/minecraft/core/item/ItemStack;Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/EntityPlayer;)Lnet/minecraft/core/item/ItemStack;", at = @At(value = "RETURN"), cancellable = true)
    private void evaporateWater(ItemStack stack, World world, EntityPlayer player, CallbackInfoReturnable<ItemStack> cir) {
        int x = result.x;
        int y = result.y;
        int z = result.z;
        if (world.dimension == BTBTA.dimensionMoon && idToPlace == Block.fluidWaterFlowing.id){
            world.playSoundEffect(SoundType.WORLD_SOUNDS, (double)z + 0.5, (double)y + 0.5, (double)x + 0.5, "random.fizz", 0.5f, 2.6f + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8f);
            for (int l = 0; l < 8; ++l) {
                world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 0.0, 0.0, 0.0);
            }
        }

    }
}
