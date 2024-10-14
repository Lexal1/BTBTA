package lexal.btb.item;

import lexal.btb.BTBTA;
import lexal.btb.UtilIdRegistrar;
import lexal.btb.block.BTBBlocks;
import net.minecraft.core.item.*;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.item.material.ArmorMaterial;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.ItemBuilder;

public class BTBItems {

    public static Item pancake;

    public static Item syrupJar;

    public static Item corn;
    public static Item unPopcorn;
    public static Item popcornBucket;
    public static Item cornBread;
    public static Item cornSeeds;
    public static Item crackedCorn;

    public static Item recordRain;
    public static Item recordBlank;
    public static Item recordPancake;
    public static Item recordSahara;

    public static Item foodCherryPie;

    public static Item stool;

    public static Item armorHelmetCloth;
    public static Item armorChestplateCloth;
    public static Item armorLeggingsCloth;
    public static Item armorBootsCloth;


    public void initilizeItems() {
        //items
        pancake = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/pancake")
                .build(new ItemPlacableLayer("pancake", UtilIdRegistrar.nextIdItem(), BTBBlocks.layerPancake, false, false)).setMaxStackSize(16);

        syrupJar = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/syrup_jar")
                .build(new Item("syrup", UtilIdRegistrar.nextIdItem())).setMaxStackSize(1);

        corn = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/corn")
                .build(new ItemFood("corn", UtilIdRegistrar.nextIdItem(), 1, 3, false, 8));

        popcornBucket = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/popcorn")
                .build(new ItemBucketIceCream("corn_popped", UtilIdRegistrar.nextIdItem(), 8, 4));

        unPopcorn = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/unpopped_corn")
                .build(new Item("corn_unpopped", UtilIdRegistrar.nextIdItem())).setMaxStackSize(1);

        cornBread = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/cornbread")
                .build(new ItemFood("corn_bread", UtilIdRegistrar.nextIdItem(), 5, 12, false, 8));

        cornSeeds = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/corn_seeds")
                .build(new ItemSeeds("corn_seeds", UtilIdRegistrar.nextIdItem(), BTBBlocks.cropsCornBottom));

        recordRain = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/record_5")
                .build(new ItemRecord("record_rain", UtilIdRegistrar.nextIdItem(), "rain", "r1915"));

        recordBlank = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/blank_disc")
                .build(new ItemRecord("record_blank", UtilIdRegistrar.nextIdItem(), "blank", ""));


        //armor
        final ArmorMaterial armorclothing = ArmorHelper.createArmorMaterial(BTBTA.MOD_ID, "clothes", 128, 10.0f, 0.0f, 0.0f, 10.0f);
        armorHelmetCloth = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/armor/cloth_hood_colored")
                .setItemModel(i -> new ItemModelArmorColored(i, 0))
                .build(new ItemArmorColored("armor.helmet.clothes", UtilIdRegistrar.nextIdItem(), armorclothing, 0));

        armorChestplateCloth = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/armor/cloth_shirt_colored")
                .setItemModel(i -> new ItemModelArmorColored(i, 1))
                .build(new ItemArmorColored("armor.chestplate.clothes", UtilIdRegistrar.nextIdItem(), armorclothing, 1));

        armorLeggingsCloth = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/armor/cloth_pants_colored")
                .setItemModel(i -> new ItemModelArmorColored(i, 2))
                .build(new ItemArmorColored("armor.leggings.clothes", UtilIdRegistrar.nextIdItem(), armorclothing, 2));

        armorBootsCloth = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/armor/cloth_boots_colored")
                .setItemModel(i -> new ItemModelArmorColored(i, 3))
                .build(new ItemArmorColored("armor.boots.clothes", UtilIdRegistrar.nextIdItem(), armorclothing, 3));

        recordPancake = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/disccake")
                .setTags(ItemTags.NOT_IN_CREATIVE_MENU)
                .build(new ItemRecord("record_pancake", UtilIdRegistrar.nextIdItem(), "so long", "Lexalis8"));

        recordSahara = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/disc_sahara")
                .build(new ItemRecord("record_sahara", UtilIdRegistrar.nextIdItem(), "sahara", "Maow"));

        crackedCorn = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/cracked_corn")
                .build(new ItemFood("corn_cracked", UtilIdRegistrar.nextIdItem(), 1, 1, false, 8));

        foodCherryPie = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/cherry_pie")
                .build(new ItemPlaceable("pie_cherry", UtilIdRegistrar.nextIdItem(), BTBBlocks.pieCherry)).setMaxStackSize(1);

        stool = new ItemBuilder(BTBTA.MOD_ID)
                .setIcon("btb:item/stool")
                .build(new ItemPlaceable("stool", UtilIdRegistrar.nextIdItem(), BTBBlocks.stool));

    }

}
