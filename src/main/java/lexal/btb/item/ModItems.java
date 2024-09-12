package lexal.btb.item;

import lexal.btb.BTBTA;
import lexal.btb.UtilIdRegistrar;
import lexal.btb.block.ModBlocks;
import net.minecraft.core.item.*;
import net.minecraft.core.item.material.ArmorMaterial;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.ItemBuilder;

public class ModItems {
    //items
    public static final Item pancake = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/pancake")
        .build(new ItemPlacableLayer("pancake", UtilIdRegistrar.nextIdItem(), ModBlocks.layerPancake, false, false)).setMaxStackSize(16);

    public static final Item syrupJar = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/syrup_jar")
        .build(new Item("syrup", UtilIdRegistrar.nextIdItem())).setMaxStackSize(1);

    public static final Item corn = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/corn")
        .build(new ItemFood("corn", UtilIdRegistrar.nextIdItem(), 1, 3, false, 8));

    public static final Item popcornBucket = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/popcorn")
        .build(new ItemBucketIceCream("corn_popped", UtilIdRegistrar.nextIdItem(), 8, 4));

    public static final Item unPopcorn = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/unpopped_corn")
        .build(new Item("corn_unpopped", UtilIdRegistrar.nextIdItem())).setMaxStackSize(1);

    public static final Item cornBread = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/cornbread")
        .build(new ItemFood("corn_bread", UtilIdRegistrar.nextIdItem(), 5, 12, false, 8));

    public static final Item cornSeeds = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/corn_seeds")
        .build(new ItemSeeds("corn_seeds", UtilIdRegistrar.nextIdItem(),ModBlocks.cornCropBottom));

    public static final Item recordRain = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/record_5")
        .build(new ItemRecord("record_rain", UtilIdRegistrar.nextIdItem(), "rain", "r1915"));

    public static final Item recordBlank = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/blank_disc")
        .build(new ItemRecord("record_blank", UtilIdRegistrar.nextIdItem(), "blank", ""));


    //armor
    public static final ArmorMaterial armorclothing = ArmorHelper.createArmorMaterial(BTBTA.MOD_ID, "clothes", 128, 10.0f, 0.0f, 0.0f, 10.0f);
    public static final Item armorHelmetCloth = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/armor/cloth_hood_colored")
        .setItemModel(i -> new ItemModelArmorColored(i, 0))
        .build(new ItemArmorColored("armor.helmet.clothes", UtilIdRegistrar.nextIdItem(), armorclothing, 0)).withTags(ModItemTags.hideName);

    public static final Item armorChestplateCloth = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/armor/cloth_shirt_colored")
        .setItemModel(i -> new ItemModelArmorColored(i, 1))
        .build(new ItemArmorColored("armor.chestplate.clothes", UtilIdRegistrar.nextIdItem(), armorclothing, 1));

    public static final Item armorLeggingsCloth = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/armor/cloth_pants_colored")
        .setItemModel(i -> new ItemModelArmorColored(i, 2))
        .build(new ItemArmorColored("armor.leggings.clothes", UtilIdRegistrar.nextIdItem(), armorclothing, 2));

    public static final Item armorBootsCloth = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/armor/cloth_boots_colored")
        .setItemModel(i -> new ItemModelArmorColored(i, 3))
        .build(new ItemArmorColored("armor.boots.clothes", UtilIdRegistrar.nextIdItem(), armorclothing, 3));

    public static final Item recordPancake = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/disccake")
        .build(new ItemRecord("record_pancake", UtilIdRegistrar.nextIdItem(), "so long", "Lexalis8")); //.setNotInCreativeMenu();

    public static final Item recordSahara = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/disc_sahara")
        .build(new ItemRecord("record_sahara", UtilIdRegistrar.nextIdItem(), "sahara", "Maow"));

    public static final Item crackedCorn = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/cracked_corn")
        .build(new ItemFood("corn_cracked", UtilIdRegistrar.nextIdItem(), 1, 1, false, 8));

    /*public static final Item pumpkinPie = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/pumpkin_pie")
        .build(new ItemPlaceable("pie_pumpkin", UtilIdRegistrar.nextIdItem(),ModBlocks.pumpkinPie)).setMaxStackSize(1);*/
    // For id compat
    int id = UtilIdRegistrar.nextIdItem();

    public static final Item cherryPie = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/cherry_pie")
        .build(new ItemPlaceable("pie_cherry", UtilIdRegistrar.nextIdItem(),ModBlocks.cherryPie)).setMaxStackSize(1);

    public static final Item stool = new ItemBuilder(BTBTA.MOD_ID)
        .setIcon("btb:item/stool")
        .build(new ItemPlaceable("stool", UtilIdRegistrar.nextIdItem(), ModBlocks.stool));

    public static void register() {
    }
}
