package lexal.btb.gui;

import lexal.btb.block.tile_entity.TileEntityInscriber;
import lexal.btb.item.ModItems;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemRecord;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotCrafting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiInscriber extends GuiContainer {
    private static int GUIx;
    private static int GUIy;
    private final TileEntityInscriber tileEntityInscriber;
    public GuiInscriber(EntityPlayer player, TileEntityInscriber tileEntityInscriber) {
        super(new ContainerInscriber(player.inventory, tileEntityInscriber));
        this.tileEntityInscriber = tileEntityInscriber;
    }
    public void initGui() {
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int i = this.mc.renderEngine.getTexture("/assets/btb/gui/inscriber.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.bindTexture(i);
        drawTexturedModalRect(GUIx, GUIy, 0, 0, this.xSize, this.ySize);

        if (tileEntityInscriber.canInscribe()){
            drawTexturedModalRect(GUIx + 78, GUIy + 35, 176, 0, 25, 16);
        }
        int arrowheight = tileEntityInscriber.getInscribeProgressScaled(15);
        drawTexturedModalRect(GUIx + 58, GUIy + 36, 176, 16, 12, arrowheight);

    }

    public void clickInventory(int x, int y, int mouseButton) {
        int slotId = this.getSlotId(x, y);
        if (slotId == -1) {
            return;
        }
        if (slotId == -999) {
            InventoryAction action = InventoryAction.DROP_HELD_STACK;
            if (mouseButton == 1) {
                action = InventoryAction.DROP_HELD_SINGLE;
            }
            this.mc.playerController.doInventoryAction(this.inventorySlots.windowId, action, null, this.mc.thePlayer);
            return;
        }
        if (!this.mc.thePlayer.getGamemode().consumeBlocks && mouseButton == 2) {
            this.mc.playerController.doInventoryAction(this.inventorySlots.windowId, InventoryAction.CREATIVE_GRAB, new int[]{slotId, 64}, this.mc.thePlayer);
            return;
        }
        InventoryAction action = InventoryAction.CLICK_LEFT;
        boolean shiftPressed = Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
        boolean ctrlPressed = Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
        boolean altPressed = Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
        boolean spacePressed = Keyboard.isKeyDown(57);
        if (mouseButton == 10) {
            shiftPressed = true;
            mouseButton = 0;
        }
        int target = 0;
        Slot slot = this.inventorySlots.getSlot(slotId);
        ItemStack stackInSlot = slot != null ? slot.getStack() : null;
        Item itemInSlot = stackInSlot != null ? stackInSlot.getItem() : null;
        int clickedItemId = stackInSlot != null ? stackInSlot.getItem().id : 0;
        ItemStack grabbedItem = this.mc.thePlayer.inventory.getHeldItemStack();
        if (mouseButton == 1) {
            action = InventoryAction.CLICK_RIGHT;
        }
        if (slot instanceof SlotCrafting) {
            if (this.mc.gameSettings.swapCraftingButtons.value) {
                if (shiftPressed && ctrlPressed) {
                    action = InventoryAction.MOVE_SIMILAR;
                } else if (shiftPressed) {
                    action = InventoryAction.MOVE_SINGLE_ITEM;
                } else if (ctrlPressed) {
                    action = InventoryAction.MOVE_STACK;
                }
            } else if (shiftPressed && ctrlPressed) {
                action = InventoryAction.MOVE_SIMILAR;
            } else if (shiftPressed) {
                action = InventoryAction.MOVE_STACK;
            } else if (ctrlPressed) {
                action = InventoryAction.MOVE_SINGLE_ITEM;
            }
        } else if (spacePressed) {
            action = InventoryAction.MOVE_ALL;
        } else if (shiftPressed && ctrlPressed) {
            action = InventoryAction.MOVE_SIMILAR;
        } else if (shiftPressed || altPressed) {
            action = InventoryAction.MOVE_STACK;
        } else if (ctrlPressed) {
            action = InventoryAction.MOVE_SINGLE_ITEM;
        }
        if (this.inventorySlots instanceof ContainerInscriber) { // This is the only section that actually really matters
            if (clickedItemId == ModItems.recordBlank.id){
                target = 1;
            } else if (Item.itemsList[clickedItemId] instanceof ItemRecord) {
                target = 2;
            }
        }
        if (slot != null && itemInSlot != null && itemInSlot instanceof ItemArmor && mouseButton == 1 && shiftPressed) {
            this.mc.playerController.doInventoryAction(this.inventorySlots.windowId, InventoryAction.EQUIP_ARMOR, new int[]{slot.id}, this.mc.thePlayer);
            return;
        }
        if (slot != null && slot.allowItemInteraction() && grabbedItem != null && grabbedItem.getItem().hasInventoryInteraction() && mouseButton == 1) {
            this.mc.playerController.doInventoryAction(this.inventorySlots.windowId, InventoryAction.INTERACT_GRABBED, new int[]{slot.id}, this.mc.thePlayer);
            return;
        }
        if (slot != null && stackInSlot != null && slot.allowItemInteraction() && stackInSlot.getItem().hasInventoryInteraction() && mouseButton == 1) {
            this.mc.playerController.doInventoryAction(this.inventorySlots.windowId, InventoryAction.INTERACT_SLOT, new int[]{slot.id}, this.mc.thePlayer);
            return;
        }
        int[] args = new int[]{slotId, target};
        this.mc.playerController.doInventoryAction(this.inventorySlots.windowId, action, args, this.mc.thePlayer);
    }
    private int getSlotId(int x, int y) {
        Slot slot = this.getSlotAtPosition(x, y);
        int x2 = (this.width - this.xSize) / 2;
        int y2 = (this.height - this.ySize) / 2;
        boolean flag = x < x2 || y < y2 || x >= x2 + this.xSize || y >= y2 + this.ySize;
        int slotId = -1;
        if (slot != null) {
            slotId = slot.id;
        }
        if (flag) {
            slotId = -999;
        }
        return slotId;
    }
}
