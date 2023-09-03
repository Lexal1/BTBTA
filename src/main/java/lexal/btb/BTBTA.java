package lexal.btb;

import lexal.btb.block.BlockCatwalk;
import lexal.btb.block.BlockCheeseOre;
import lexal.btb.block.BlockCheeseWheel;
import lexal.btb.block.BlockMoonstone;
import lexal.btb.entity.EntityPenguin;
import lexal.btb.entity.EntitySpaceSkeleton;
import lexal.btb.entity.EntitySpaceZombie;
import lexal.btb.entity.renderer.ModelPenguin;
import lexal.btb.entity.renderer.PenguinRenderer;
import lexal.btb.entity.renderer.SpaceZombieRenderer;
import lexal.btb.world.BiomeMoon;
import lexal.btb.world.WorldTypeMoonDefault;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.model.ModelSkeleton;
import net.minecraft.client.render.model.ModelZombie;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.entity.monster.EntitySkeleton;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.type.WorldType;
import net.minecraft.core.world.type.WorldTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.*;
import turniplabs.halplibe.util.achievements.AchievementPage;
import useless.prismaticlibe.helper.ModCheckHelper;
import useless.prismaticlibe.helper.SoundHelper;

public class BTBTA implements ModInitializer {

    public static int blockIdTacker = 3000;
    public static int itemIdTacker = 20000;
    public static final String MODID = "btb";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static final Block ironplating = new BlockBuilder(MODID)
            .setHardness(3.0f)
            .setResistance(2.5f)
            .setTextures("iron_plating.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new Block("ironplating", blockIdTacker++,Material.metal));
    public static final Block goldplating = new BlockBuilder(MODID)
            .setHardness(2.0f)
            .setResistance(1.5f)
            .setTextures("gold_plating.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new Block("goldplating", blockIdTacker++,Material.metal));
    public static final Block moonturf = new BlockBuilder(MODID)
            .setBlockSound(new BlockSound("step.sand", "step.stone", 1.0f, 1.0f))
            .setHardness(2.0f)
            .setResistance(1.5f)
            .setTextures("moon_turf.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new Block("moonturf", blockIdTacker++,Material.stone));
    public static final Block moonstone = new BlockBuilder(MODID)
            .setHardness(2.5f)
            .setResistance(2.5f)
            .setTextures("moon_stone.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new BlockMoonstone("moonstone", blockIdTacker++,Material.stone));
    public static final Block catwalk = new BlockBuilder(MODID)
            .setHardness(1.5f)
            .setResistance(1.5f)
            .setTopBottomTexture(7,19)
            .setSideTextures("catwalk_side.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new BlockCatwalk("catwalk", blockIdTacker++,Material.metal));
    public static final Block mooncobblestone = new BlockBuilder(MODID)
            .setHardness(3.0f)
            .setResistance(3.0f)
            .setTextures("moon_cobblestone.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE)
            .build(new BlockMoonstone("mooncobblestone", blockIdTacker++,Material.stone));
    public static final Block cheesewheel = new BlockBuilder(MODID)
            .setTextures("cheese_block.png")
            .build(new BlockCheeseWheel("cheesewheel", blockIdTacker++));
    public static final Block cheeseore = new BlockBuilder(MODID)
            .setHardness(2.5f)
            .setResistance(2.5f)
            .setTextures("cheese_ore.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new BlockCheeseOre("cheeseore", blockIdTacker++));
    public static final Block mooniron = new BlockBuilder(MODID)
            .setHardness(2.5f)
            .setResistance(2.5f)
            .setTextures("moon_stone_iron.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new BlockOreIron("mooniron", blockIdTacker++,Material.stone));
    public static final Block moongold = new BlockBuilder(MODID)
            .setHardness(2.5f)
            .setResistance(2.5f)
            .setTextures("moon_stone_gold.png")
            .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAVES_CUT_THROUGH)
            .build(new BlockOreGold("moongold", blockIdTacker++,Material.stone));




    //armor
    public static final ArmorMaterial armorspacesuit = ArmorHelper.createArmorMaterial("space",256,25.0f,25.0f,30.0f,50.0f);
    public static final Item armorHelmetSpace = ItemHelper.createItem(MODID, new ItemArmor("Space Helmet", itemIdTacker++, armorspacesuit, 0), "armor.helmet.space", "space_helmet.png");
    public static final Item armorChestplateSpace = ItemHelper.createItem(MODID, new ItemArmor("Space Suit", itemIdTacker++, armorspacesuit, 1), "armor.chestplate.space", "space_suit.png");
    public static final Item armorLeggingsSpace = ItemHelper.createItem(MODID, new ItemArmor("Space Leggings", itemIdTacker++, armorspacesuit, 2), "armor.leggings.space", "space_pants.png");
    public static final Item armorBootsSpace = ItemHelper.createItem(MODID, new ItemArmor("Space Boots", itemIdTacker++, armorspacesuit, 3), "armor.boots.space", "space_boots.png");

    public static final ArmorMaterial armorclothing = ArmorHelper.createArmorMaterial("clothes",128,10.0f,0.0f,0.0f,10.0f);
    public static final Item armorHelmetCloth = ItemHelper.createItem(MODID, new ItemArmor("Cloth Hood", itemIdTacker++, armorclothing, 0), "armor.helmet.clothes", "cloth_hood.png");
    public static final Item armorChestplateCloth = ItemHelper.createItem(MODID, new ItemArmor("Cloth Shirt", itemIdTacker++, armorclothing, 1), "armor.chestplate.clothes", "cloth_shirt.png");
    public static final Item armorLeggingsCloth = ItemHelper.createItem(MODID, new ItemArmor("Cloth Pants", itemIdTacker++, armorclothing, 2), "armor.leggings.clothes", "cloth_pants.png");
    public static final Item armorBootsCloth = ItemHelper.createItem(MODID, new ItemArmor("Shoes", itemIdTacker++, armorclothing, 3), "armor.boots.clothes", "cloth_boots.png");
    //items
    public static final Item cheese = ItemHelper.createItem(MODID,new ItemFood("cheese",itemIdTacker++,5,false),"cheese","cheese.png");
    public static final Item burger = ItemHelper.createItem(MODID,new ItemFood("burger",itemIdTacker++,20,true),"burger","hamburger.png");

    public static final boolean spawnEggsModPresent = ModCheckHelper.checkForMod("spawneggs", ">=1.1.0");

    public static Item penguinSpawnEgg;
    public static Item spaceZombieSpawnEgg;
    public static Item spaceSkeletonSpawnEgg;






    public static final Block portalmoon = new BlockBuilder(MODID)
            .setHardness(-1.0f)
            .setResistance(-1.0f)
            .setTextures("moon_cobblestone.png")
            .setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.NOT_IN_CREATIVE_MENU)
            .build(new BlockPortal("portal.moon",blockIdTacker++,3, ironplating.id,moonstone.id));
    public static final WorldType worldTypeMoon = WorldTypes.register("btb:moon.default", new WorldTypeMoonDefault("worldType.moon.default"));
    public static final Biome biomeMoon = Biomes.register("btb:moon.moon", new BiomeMoon());
    static
    {
        biomeMoon.topBlock = (short) moonturf.id;
        biomeMoon.fillerBlock = (short) moonturf.id;
    }
    public static final Dimension dimensionMoon = new Dimension("moon", Dimension.overworld, 3f, BTBTA.portalmoon.id).setDefaultWorldType(worldTypeMoon);
    static
    {
        Dimension.registerDimension(3, dimensionMoon);
    }




    @Override
    public void onInitialize() {
        LOGGER.info("btbta loading! watch out for bugs");

        //item recipes
        RecipeHelper.Crafting.createRecipe(BTBTA.burger, 1, new Object[]{
                " B ",
                "CSC",
                " B ",
                'B', Item.foodBread,
                'S', Item.foodPorkchopCooked,
                'C', BTBTA.cheese});

        //block recipes
        RecipeHelper.Crafting.createRecipe(BTBTA.catwalk, 8, new Object[]{
                "CCC",
                "I I",
                'I', Item.ingotIron,
                'C', Item.chainlink});
        RecipeHelper.Crafting.createRecipe(BTBTA.cheesewheel, 1, new Object[]{
                "CC",
                "CC",
                'C', BTBTA.cheese});
        RecipeHelper.Crafting.createRecipe(BTBTA.ironplating, 12, new Object[]{
                "I I",
                " B ",
                "I I",
                'B', Block.blockIron,
                'I', Item.ingotIron});
        RecipeHelper.Crafting.createRecipe(BTBTA.goldplating, 12, new Object[]{
                "I I",
                " B ",
                "I I",
                'B', Block.blockGold,
                'I', Item.ingotGold});

        //armor recipes
        RecipeHelper.Crafting.createRecipe(BTBTA.armorHelmetSpace, 1, new Object[]{
                "CIC",
                "WLW",
                "CGC",
                'I', Item.armorHelmetIron,
                'L', Item.armorHelmetLeather,
                'G', Block.glassTinted,
                'W', Block.wool,
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(BTBTA.armorChestplateSpace, 1, new Object[]{
                "WIW",
                "WLW",
                "CCC",
                'W', Block.wool,
                'I', Item.armorChestplateIron,
                'L', Item.armorChestplateLeather,
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(BTBTA.armorLeggingsSpace, 1, new Object[]{
                "WWW",
                "CIC",
                "CLC",
                'I', Item.armorLeggingsIron,
                'L', Item.armorLeggingsLeather,
                'W', Block.wool,
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(BTBTA.armorBootsSpace, 1, new Object[]{
                "CIC",
                "WLW",
                'I', Item.armorBootsIron,
                'L', Item.armorBootsLeather,
                'W', Block.wool,
                'C', Item.cloth});

        RecipeHelper.Crafting.createRecipe(BTBTA.armorHelmetCloth, 1, new Object[]{
                "CCC",
                "C C",
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(BTBTA.armorChestplateCloth, 1, new Object[]{
                "C C",
                "CCC",
                "CCC",
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(BTBTA.armorLeggingsCloth, 1, new Object[]{
                "CCC",
                "C C",
                "C C",
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(BTBTA.armorBootsCloth, 1, new Object[]{
                "C C",
                "L L",
                'L', Item.leather,
                'C', Item.cloth});
        RecipeHelper.Crafting.createRecipe(BTBTA.moonstone, 1, new Object[]{
                "CCC",
                "CBC",
                "CCC",
                'B', Block.cobbleBasalt,
                'C', Block.cobbleStone});


        LOGGER.info("btbta loaded all recipes successfully!"); //put recipes before this point

        EntityHelper.createEntity(EntitySpaceZombie.class, new SpaceZombieRenderer(new ModelZombie(), 1), 900, "spaceZombie");
        EntityHelper.createEntity(EntitySpaceSkeleton.class, new MobRenderer<EntitySkeleton>(new ModelSkeleton(), 1), 901, "spaceSkeleton");
        EntityHelper.createEntity(EntityPenguin.class, new PenguinRenderer(new ModelPenguin(), .25F), 902, "Penguin");

        if (spawnEggsModPresent){
            SpawnEggsModule.onInitialize();;
        }
        LOGGER.info("btbta loaded all entities successfully!"); //put entities before this point
    }
}
