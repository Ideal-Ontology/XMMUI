package github.thelawf.xmmui.init;

import github.thelawf.xmmui.client.XMMUIContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class XMMUIRegistries {
    public static final IForgeRegistry<XMMUIContainerType<XMMUIContainer>> CONTAINERS = RegistryManager.ACTIVE.getRegistry(XMMUIContainerType.class);
}
