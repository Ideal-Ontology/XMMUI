package github.thelawf.xmmui.client;


import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nullable;

public abstract class XMMUIContainer extends Container {
    protected XMMUIContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }
}
