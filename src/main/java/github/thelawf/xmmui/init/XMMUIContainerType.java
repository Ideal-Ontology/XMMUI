package github.thelawf.xmmui.init;

import github.thelawf.xmmui.client.XMMUIContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public class XMMUIContainerType<T extends XMMUIContainer> implements IForgeRegistryEntry<XMMUIContainerType<T>> {

    private ResourceLocation registryName;

    @Override
    public XMMUIContainerType<T> setRegistryName(ResourceLocation name) {
        try {
            this.registryName = name;
            return getRegistryType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return this.registryName;
    }

    @Override
    public Class<XMMUIContainerType<T>> getRegistryType() {
        return null;
    }
}
