package github.thelawf.xmmui.client;

import com.sun.istack.internal.NotNull;
import github.thelawf.xmmui.data.XMMUIData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

public class UIHelper {
    public static String getXMLText(ResourceLocation location) {
        Minecraft minecraft = Minecraft.getInstance();
        StringBuilder builder = new StringBuilder();
        try {
            IResource resource = minecraft.getResourceManager().getResource(location);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            reader.lines().forEach(builder::append);
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream getXMLAsStream(ResourceLocation location) {
        Minecraft minecraft = Minecraft.getInstance();
        try {
            IResource resource = minecraft.getResourceManager().getResource(location);
            return resource.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static XMMUIData getDataFromXML(ResourceLocation location) throws DocumentException {
        SAXReader reader= new SAXReader();
        Document document = reader.read(getXMLAsStream(location));
        return new XMMUIData(document.getRootElement());
    }

    public static INamedContainerProvider displayXMMUI(ResourceLocation location) throws DocumentException {
        Minecraft minecraft = Minecraft.getInstance();
        XMMUIData data = getDataFromXML(location);
        if (data.hasContainer) {
            return displayContainer(data);
        }
        return null;
    }

    private static INamedContainerProvider displayContainer(@NotNull XMMUIData data) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent(data.id);
            }

            @Override
            public Container createMenu(int p_createMenu_1_, PlayerInventory inventory, PlayerEntity p_createMenu_3_) {
                return createContainer(data, inventory);
            }
        };
    }

    public static String getValueOrDefault(Element element, String attribute, String defaultValue) {
        return element.attributeValue(attribute) == null ? defaultValue : element.attributeValue(attribute);
    }

    public static ResourceLocation getOrDefaultRegistryName(Element element) {
        return new ResourceLocation(getValueOrDefault(element, "container", "xmmui:test_container"));
    }

    public static ContainerType<?> createContainerType(Element element) {
        return RegistryObject.of(getOrDefaultRegistryName(element), ForgeRegistries.CONTAINERS).get();
    }

    public static XMMUIContainer createContainer(XMMUIData data, PlayerInventory inv) {
        AtomicInteger id = new AtomicInteger();
        IForgeContainerType.create((windowId, inventory, packetBuffer) -> {
            id.set(windowId);
            return new XMMUIContainer(data, inv, windowId);
        });
        return new XMMUIContainer(data,inv, id.get());
    }
}
