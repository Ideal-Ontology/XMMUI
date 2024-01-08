package github.thelawf.xmmui.client;


import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.TranslationTextComponent;
import org.dom4j.Element;

public abstract class XMMUIContainerScreen<T extends XMMUIContainer> extends ContainerScreen<T> {
    private Element element;

    public XMMUIContainerScreen(T screenContainer, PlayerInventory inv, Element element) {
        super(screenContainer, inv, new TranslationTextComponent(UIHelper.getOrDefault(element, "title", "gui.xmmui.title")));
    }

}
