package github.thelawf.xmmui.init;

import github.thelawf.xmmui.XMMUI;
import github.thelawf.xmmui.client.TestContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, XMMUI.MOD_ID);

    public static final RegistryObject<ContainerType<TestContainer>> TEST_CONTAINER = CONTAINERS.register("test_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> new TestContainer(windowId, inv))));
}
