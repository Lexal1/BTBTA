package lexal.btb.item;

import lexal.btb.BTBTA;
import lexal.btb.UtilIdRegistrar;
import lexal.btb.block.ModBlocks;
import net.minecraft.core.item.*;
import net.minecraft.core.item.material.ArmorMaterial;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.ItemHelper;

public class ModItems {
    //items
    public static final Item pancake = ItemHelper.createItem(BTBTA.MOD_ID, new ItemPlacableLayer(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(), ModBlocks.layerPancake, false, false), "pancake", "pancake.png").setMaxStackSize(16);
    public static final Item syrupJar = ItemHelper.createItem(BTBTA.MOD_ID, new Item(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem()), "syrup", "syrup_jar.png").setMaxStackSize(1);
    public static final Item corn = ItemHelper.createItem(BTBTA.MOD_ID, new ItemFoodStackable(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(),1,false,32), "corn", "corn.png");
    public static final Item popcornBucket = ItemHelper.createItem(BTBTA.MOD_ID, new ItemBucketIceCream(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(),5), "popcorn", "popcorn.png");
    public static final Item unPopcorn = ItemHelper.createItem(BTBTA.MOD_ID, new Item(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem()), "unpopcorn", "unpopped_corn.png").setMaxStackSize(1);
    public static final Item cornBread = ItemHelper.createItem(BTBTA.MOD_ID, new ItemFood(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(),10,false), "cornbread", "cornbread.png");
    public static final Item cornSeeds = ItemHelper.createItem(BTBTA.MOD_ID, new ItemSeeds(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(),ModBlocks.cornCropBottom), "cornseeds", "corn_seeds.png");
    public static final Item recordRain = ItemHelper.createItem(BTBTA.MOD_ID, new ItemRecordAccessor(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(), "discrain"), "record.rain", "record_5.png"); // ItemRecord is protected i am sobbing rn
    public static final Item recordBlank = ItemHelper.createItem(BTBTA.MOD_ID, new Item(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem()), "record.blank", "blank_disc.png");



    //armor
    public static final ArmorMaterial armorclothing = ArmorHelper.createArmorMaterial("clothes", 128, 10.0f, 0.0f, 0.0f, 10.0f);
    public static final Item armorHelmetCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Cloth Hood", UtilIdRegistrar.nextIdItem(), armorclothing, 0), "armor.helmet.clothes", "cloth_hood.png").withTags(ModItemTags.hideName);
    public static final Item armorChestplateCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Cloth Shirt", UtilIdRegistrar.nextIdItem(), armorclothing, 1), "armor.chestplate.clothes", "cloth_shirt.png");
    public static final Item armorLeggingsCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Cloth Pants", UtilIdRegistrar.nextIdItem(), armorclothing, 2), "armor.leggings.clothes", "cloth_pants.png");
    public static final Item armorBootsCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmor("Shoes", UtilIdRegistrar.nextIdItem(), armorclothing, 3), "armor.boots.clothes", "cloth_boots.png");


    //spawn eggs
    public static Item penguinSpawnEgg = ItemHelper.createItem(BTBTA.MOD_ID, new Item("spawn.egg.penguin", UtilIdRegistrar.nextIdSpawnEggItem()), "spawn.egg.penguin", "unknown.png");

    public static void register() {
    }
}
