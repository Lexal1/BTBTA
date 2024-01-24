package lexal.btb;

import net.minecraft.core.item.Item;

import static lexal.btb.BTBTA.config;


public class UtilIdRegistrar {
    private static int curr_item_id = config.getInt("starting_item_id");
    private static int curr_block_id = config.getInt("starting_block_id");
    private static final boolean[] usedIds;
    static {
        usedIds = new boolean[Item.itemsList.length];
        for (int i = 0; i < usedIds.length; i++){
            if (Item.itemsList[i] != null){
                usedIds[i] = true;
            }
        }
    }

    public static int nextIdItem() {
        throwException(curr_item_id);
        return curr_item_id++;
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
