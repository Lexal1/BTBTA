package lexal.btb;

import net.minecraft.core.item.Item;


public class UtilIdRegistrar {
    private static int curr_item_id = 0;
    private static int curr_spawnegg_item_id = 0;
    private static int curr_block_id = 0;
    private static boolean[] usedIds;

    public static void initIds(int blockId, int itemId, int spawnEggItemID) {
        usedIds = new boolean[Item.itemsList.length];
        for (int i = 0; i < usedIds.length; i++){
            if (Item.itemsList[i] != null){
                usedIds[i] = true;
            }
        }
        curr_item_id = itemId;
        curr_block_id = blockId;
        curr_spawnegg_item_id = spawnEggItemID;
    }

    public static int nextIdItem() {
        throwException(curr_item_id);
        return curr_item_id++;
    }
    public static int nextIdSpawnEggItem() {
        throwException(curr_spawnegg_item_id);
        return curr_spawnegg_item_id++;
    }
    public static int nextIdBlock() {
        throwException(curr_block_id);
        return curr_block_id++;
    }
    private static void throwException(int id){
        if (usedIds[id]) { // If id is already used then throw exception
            throw new RuntimeException("Tried to use id: " + id + " while its already being used by " + Item.itemsList[id].getKey() + "!");
        }
        else {
            usedIds[id] = true;
        }
    }
}
