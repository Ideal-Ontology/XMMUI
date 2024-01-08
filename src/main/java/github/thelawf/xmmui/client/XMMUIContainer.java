package github.thelawf.xmmui.client;

import github.thelawf.xmmui.data.IXMLValueParser;
import github.thelawf.xmmui.data.XMMUIData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.dom4j.Element;

public class XMMUIContainer extends Container implements IXMLValueParser {
    public IItemHandler inventory;
    protected XMMUIContainer(XMMUIData data, PlayerInventory inv, int id) {
        super(UIHelper.createContainerType(data.getElement()), id);
        if (data.hasInventory) {
            inventory = new InvWrapper(inv);
            layoutPlayerInventorySlots(data.inventoryX, data.inventoryY);
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(inventory, 9, leftCol, topRow, 9, 3, 18, 18);
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    private void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int verAmount, int dx, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
    }
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

}
