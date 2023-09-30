package lexal.btb;

import lexal.btb.block.ModBlocks;
import lexal.btb.item.ModItems;
import net.minecraft.client.render.TextureFX;
import net.minecraft.core.Global;
import net.minecraft.core.achievement.Achievement;
import net.minecraft.core.achievement.AchievementList;
import net.minecraft.core.achievement.stat.StatBase;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.helper.Side;
import org.lwjgl.opengl.GL11;
import turniplabs.halplibe.util.achievements.AchievementPage;
import turniplabs.halplibe.util.achievements.GuiAchievements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModAchievements extends AchievementPage {
    public static final List<Integer> flowersList = new ArrayList<>();
    public static final Achievement ROOT = new Achievement(AchievementList.achievementList.size()+1,"btb.root",0,0, Item.basket,null);
    public static final Achievement SYRUP = new Achievement(AchievementList.achievementList.size()+1,"btb.syrup",2,1, ModItems.syrupJar,ROOT);
    public static final Achievement PANCAKES = new Achievement(AchievementList.achievementList.size()+1,"btb.pancakes",4,1, ModItems.pancake,SYRUP);
    public static final Achievement OVERKILL = new Achievement(AchievementList.achievementList.size()+1,"btb.damage",0,3, Item.toolSwordSteel,ROOT).setSpecial();
    public static final Achievement FLOWERS = new Achievement(AchievementList.achievementList.size()+1,"btb.flowers",-2,0, ModBlocks.blueRose,ROOT);
    public static final Achievement DAYS = new Achievement(AchievementList.achievementList.size()+1,"btb.days",-2,2, Item.toolClock,ROOT).setSpecial();
    public static final Achievement PENGUIN = new Achievement(AchievementList.achievementList.size()+1,"btb.penguintame",3,-2, Item.featherChicken,ROOT);
    public static final Achievement POPCORN = new Achievement(AchievementList.achievementList.size()+1,"btb.popcorn",2,-1, ModItems.popcornBucket,ROOT);
    public static final Achievement NETHERDIST = new Achievement(AchievementList.achievementList.size()+1,"btb.netherdist",-1,-3, Block.netherrack,ROOT).setSpecial();
    static {
        flowersList.add(Block.flowerRed.id);
        flowersList.add(Block.flowerYellow.id);
        flowersList.add(ModBlocks.blueRose.id);
    }
    public ModAchievements() {
        super("BTBTA", "achievements.page.btb");
        ((StatBase)ROOT).registerStat();
        achievementList.add(ROOT);
        achievementList.add(SYRUP);
        achievementList.add(PANCAKES);
        achievementList.add(OVERKILL);
        achievementList.add(FLOWERS);
        achievementList.add(DAYS);
        achievementList.add(PENGUIN);
        achievementList.add(POPCORN);
        achievementList.add(NETHERDIST);
    }

    @Override
    public void getBackground(GuiAchievements guiAchievements, Random random, int iOffset, int jOffset, int blockX1, int blockY1, int blockX2, int blockY2) {
        int l7 = 0;
        while (l7 * 16 - blockY2 < 155) {
            float f5 = 0.6f - (float)(blockY1 + l7) / 25.0f * 0.3f;
            GL11.glColor4f(f5, f5, f5, 1.0f);
            int i8 = 0;
            while (i8 * 16 - blockX2 < 224) {
                int k8 = ModBlocks.flintTile.getBlockTextureFromSideAndMetadata(Side.BOTTOM,0);
                guiAchievements.drawTexturedModalRect(iOffset + i8 * 16 - blockX2, jOffset + l7 * 16 - blockY2, k8 % Global.TEXTURE_ATLAS_WIDTH_TILES * TextureFX.tileWidthTerrain, k8 / Global.TEXTURE_ATLAS_WIDTH_TILES * TextureFX.tileWidthTerrain, 16, 16, TextureFX.tileWidthTerrain, 1.0f / (float)(Global.TEXTURE_ATLAS_WIDTH_TILES * TextureFX.tileWidthTerrain));
                ++i8;
            }
            ++l7;
        }
    }
}