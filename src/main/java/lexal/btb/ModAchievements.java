package lexal.btb;

import lexal.btb.block.ModBlocks;
import net.minecraft.client.render.TextureFX;
import net.minecraft.core.Global;
import net.minecraft.core.achievement.Achievement;
import net.minecraft.core.achievement.AchievementList;
import net.minecraft.core.achievement.stat.StatBase;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import org.lwjgl.opengl.GL11;
import turniplabs.halplibe.util.achievements.AchievementPage;
import turniplabs.halplibe.util.achievements.GuiAchievements;

import java.util.Random;

public class ModAchievements extends AchievementPage {

    public static final Achievement ROOT = new Achievement(AchievementList.achievementList.size()+1,"btb.root",0,0, Block.netherrack/*change this to a modded block, i dare you*/,null);

    public ModAchievements() {
        super("BTBTA", "achievements.page.btb");
        ((StatBase)ROOT).registerStat();
        achievementList.add(ROOT);
    }

    @Override
    public void getBackground(GuiAchievements guiAchievements, Random random, int iOffset, int jOffset, int blockX1, int blockY1, int blockX2, int blockY2) {
        int l7 = 0;
        while (l7 * 16 - blockY2 < 155) {
            float f5 = 0.6f - (float)(blockY1 + l7) / 25.0f * 0.3f;
            GL11.glColor4f(f5, f5, f5, 1.0f);
            int i8 = 0;
            while (i8 * 16 - blockX2 < 224) {
                int k8 = Block.bedrock.getBlockTextureFromSideAndMetadata(Side.BOTTOM,0);
                guiAchievements.drawTexturedModalRect(iOffset + i8 * 16 - blockX2, jOffset + l7 * 16 - blockY2, k8 % Global.TEXTURE_ATLAS_WIDTH_TILES * TextureFX.tileWidthTerrain, k8 / Global.TEXTURE_ATLAS_WIDTH_TILES * TextureFX.tileWidthTerrain, 16, 16, TextureFX.tileWidthTerrain, 1.0f / (float)(Global.TEXTURE_ATLAS_WIDTH_TILES * TextureFX.tileWidthTerrain));
                ++i8;
            }
            ++l7;
        }
    }
}