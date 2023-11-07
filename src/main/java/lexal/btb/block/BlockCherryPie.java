package lexal.btb.block;

import lexal.btb.BTBTA;
import lexal.btb.item.ModItems;
import net.minecraft.core.block.BlockCake;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.TextureHelper;

public class BlockCherryPie extends BlockCake {
    public BlockCherryPie(String key, int id) {
        super(key, id);
    }
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        return dropCause != EnumDropCause.PICK_BLOCK && meta != 0 ? null : new ItemStack[]{new ItemStack(ModItems.cherryPie)};
    }
    public int getBlockTextureFromSideAndMetadata(Side side, int j) {
        int[] texture = TextureHelper.getOrCreateBlockTexture(BTBTA.MOD_ID, "cherry_pie_side.png");
        return j > 0 && side == Side.WEST ? texCoordToIndex(texture[0], texture[1]) : this.atlasIndices[side.getId()];
    }
}
