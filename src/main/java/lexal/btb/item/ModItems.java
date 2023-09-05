package lexal.btb.item;

import lexal.btb.BTBTA;
import lexal.btb.UtilIdRegistrar;
import lexal.btb.block.ModBlocks;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.material.ArmorMaterial;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.ItemHelper;

public class ModItems {

    //armor
    public static final ArmorMaterial armorspacesuit = ArmorHelper.createArmorMaterial("space",256,25.0f,25.0f,30.0f,50.0f);
    public static final Item armorHelmetSpace = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Space Helmet", UtilIdRegistrar.nextIdItem(), armorspacesuit, 0), "armor.helmet.space", "space_helmet.png");
    public static final Item armorChestplateSpace = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Space Suit", UtilIdRegistrar.nextIdItem(), armorspacesuit, 1), "armor.chestplate.space", "space_suit.png");
    public static final Item armorLeggingsSpace = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Space Leggings", UtilIdRegistrar.nextIdItem(), armorspacesuit, 2), "armor.leggings.space", "space_pants.png");
    public static final Item armorBootsSpace = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Space Boots", UtilIdRegistrar.nextIdItem(), armorspacesuit, 3), "armor.boots.space", "space_boots.png");

    public static final ArmorMaterial armorclothing = ArmorHelper.createArmorMaterial("clothes",128,10.0f,0.0f,0.0f,10.0f);
    public static final Item armorHelmetCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Cloth Hood", UtilIdRegistrar.nextIdItem(), armorclothing, 0), "armor.helmet.clothes", "cloth_hood.png");
    public static final Item armorChestplateCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Cloth Shirt", UtilIdRegistrar.nextIdItem(), armorclothing, 1), "armor.chestplate.clothes", "cloth_shirt.png");
    public static final Item armorLeggingsCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Cloth Pants", UtilIdRegistrar.nextIdItem(), armorclothing, 2), "armor.leggings.clothes", "cloth_pants.png");
    public static final Item armorBootsCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Shoes", UtilIdRegistrar.nextIdItem(), armorclothing, 3), "armor.boots.clothes", "cloth_boots.png");
    //items
    public static final Item cheese = ItemHelper.createItem(BTBTA.MOD_ID, new ItemFood("cheese",UtilIdRegistrar.nextIdItem(),5,false),"cheese","cheese.png");
    public static final Item burger = ItemHelper.createItem(BTBTA.MOD_ID, new ItemFood("burger",UtilIdRegistrar.nextIdItem(),20,true),"burger","hamburger.png");
    public static final Item moondust = ItemHelper.createItem(BTBTA.MOD_ID, new Item("dust.moon", UtilIdRegistrar.nextIdItem()),"moondust","moondust.png");
    public static final Item pancake = ItemHelper.createItem(BTBTA.MOD_ID, new ItemPlacableLayer(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(), ModBlocks.layerPancake, false, false),"pancake","pancake.png").setMaxStackSize(16);

    public static final ArmorMaterial crashlandingsuit = ArmorHelper.createArmorMaterial("space",8,0.0f,0.0f,0.0f,500.0f);
    public static final Item armorBootsCrash = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Crash Boots", UtilIdRegistrar.nextIdItem(), crashlandingsuit, 3), "armor.boots.crash", "crash_landing_boots.png");

    //spawn eggs
    public static Item penguinSpawnEgg = ItemHelper.createItem(BTBTA.MOD_ID, new Item("spawn.egg.penguin", UtilIdRegistrar.nextIdSpawnEggItem()), "spawn.egg.penguin", "unknown.png");
    public static Item spaceZombieSpawnEgg = ItemHelper.createItem(BTBTA.MOD_ID, new Item("spawn.egg.spacezombie", UtilIdRegistrar.nextIdSpawnEggItem()), "spawn.egg.spacezombie", "unknown.png");
    public static Item spaceSkeletonSpawnEgg = ItemHelper.createItem(BTBTA.MOD_ID, new Item("spawn.egg.spaceskeleton", UtilIdRegistrar.nextIdSpawnEggItem()), "spawn.egg.spaceskeleton", "unknown.png");
    public static void register() {}
}
