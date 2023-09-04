package lexal.btb.mixin.core.item;

import lexal.btb.BTBTA;
import lexal.btb.block.ModBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFirestriker;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemFirestriker.class, remap = false)
public class ItemFirestrikerMixin extends Item {
    public ItemFirestrikerMixin(int id) {
        super(id);
    }

    @Inject(method = "onItemUse(Lnet/minecraft/core/item/ItemStack;Lnet/minecraft/core/entity/player/EntityPlayer;Lnet/minecraft/core/world/World;IIILnet/minecraft/core/util/helper/Side;DD)Z",
            at = @At("HEAD"), cancellable = true)
    private void lightTorches(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir){
        int idUsedOn = world.getBlockId(blockX,blockY,blockZ);
        if (idUsedOn == ModBlocks.torchUnlit.id){
            if (world.setBlockWithNotify(blockX, blockY, blockZ, Block.torchCoal.id)) {
                world.playSoundEffect(SoundType.WORLD_SOUNDS, (double)blockX + 0.5, (double)blockY + 0.5, (double)blockZ + 0.5, "fire.ignite", 1.0f, itemRand.nextFloat() * 0.4f + 0.8f);
                itemstack.damageItem(1, entityplayer);
                cir.setReturnValue(true);
            }
        }
    }
}
