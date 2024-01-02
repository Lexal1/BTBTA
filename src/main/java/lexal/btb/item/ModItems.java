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
    public static final Item corn = ItemHelper.createItem(BTBTA.MOD_ID, new ItemFoodStackable(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(),1,false,8), "corn", "corn.png");
    public static final Item popcornBucket = ItemHelper.createItem(BTBTA.MOD_ID, new ItemBucketIceCream(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(),8), "corn.popped", "popcorn.png");
    public static final Item unPopcorn = ItemHelper.createItem(BTBTA.MOD_ID, new Item(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem()), "corn.unpopped", "unpopped_corn.png").setMaxStackSize(1);
    public static final Item cornBread = ItemHelper.createItem(BTBTA.MOD_ID, new ItemFood(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(),5,false), "corn.bread", "cornbread.png");
    public static final Item cornSeeds = ItemHelper.createItem(BTBTA.MOD_ID, new ItemSeeds(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(),ModBlocks.cornCropBottom), "corn.seeds", "corn_seeds.png");
    public static final Item recordRain = ItemHelper.createItem(BTBTA.MOD_ID, new ItemRecordAccessor(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(), "rain"), "record.rain", "record_5.png");
    public static final Item recordBlank = ItemHelper.createItem(BTBTA.MOD_ID, new ItemRecordAccessor(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(), "blank"), "record.blank", "blank_disc.png");


    //armor
    public static final ArmorMaterial armorclothing = ArmorHelper.createArmorMaterial("clothes", 128, 10.0f, 0.0f, 0.0f, 10.0f);
    public static final Item armorHelmetCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmorColored("Cloth Hood", UtilIdRegistrar.nextIdItem(), armorclothing, 0), "armor.helmet.clothes").withTags(ModItemTags.hideName);
    public static final Item armorChestplateCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmorColored("Cloth Shirt", UtilIdRegistrar.nextIdItem(), armorclothing, 1), "armor.chestplate.clothes");
    public static final Item armorLeggingsCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmorColored("Cloth Pants", UtilIdRegistrar.nextIdItem(), armorclothing, 2), "armor.leggings.clothes");
    public static final Item armorBootsCloth = ItemHelper.createItem(BTBTA.MOD_ID, new ItemArmorColored("Shoes", UtilIdRegistrar.nextIdItem(), armorclothing, 3), "armor.boots.clothes");

    public static final Item recordPancake = ItemHelper.createItem(BTBTA.MOD_ID, new ItemRecordAccessor(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(), "so long"), "record.pancake", "disccake.png").setNotInCreativeMenu();
    public static final Item recordSahara = ItemHelper.createItem(BTBTA.MOD_ID, new ItemRecordAccessor(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(), "sahara"), "record.sahara", "disc_sahara.png");
    public static final Item crackedCorn = ItemHelper.createItem(BTBTA.MOD_ID, new ItemFood(BTBTA.MOD_ID, UtilIdRegistrar.nextIdItem(),1,false), "corn.cracked", "cracked_corn.png");
    public static final Item pumpkinPie = ItemHelper.createItem(BTBTA.MOD_ID, new ItemPlaceable("pumpkin_pie",UtilIdRegistrar.nextIdItem(),ModBlocks.pumpkinPie),"pie.pumpkin","pumpkin_pie.png").setMaxStackSize(1);
    public static final Item cherryPie = ItemHelper.createItem(BTBTA.MOD_ID, new ItemPlaceable("cherry_pie",UtilIdRegistrar.nextIdItem(),ModBlocks.cherryPie),"pie.cherry","cherry_pie.png").setMaxStackSize(1);

    public static void register() {
    }
}
