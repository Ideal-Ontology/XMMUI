package github.thelawf.xmmui.client;

import com.sun.istack.internal.NotNull;
import github.thelawf.xmmui.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public class TestContainer extends Container {
    public IItemHandler inv;
    public TestContainer(int id, PlayerInventory inventory) {
        super(ModContainers.TEST_CONTAINER.get(), id);
        this.inv = new InvWrapper(inventory);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
