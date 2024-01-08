package github.thelawf.xmmui.client;


import github.thelawf.xmmui.data.XMMUIData;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.TranslationTextComponent;
import org.dom4j.Element;

public abstract class XMMUIContainerScreen extends ContainerScreen<Container> {
    protected XMMUIData data;

    public XMMUIContainerScreen(PlayerInventory inv, XMMUIData data) {
        super(UIHelper.createContainer(data, inv), inv, new TranslationTextComponent(UIHelper.getValueOrDefault(data.getElement(), "title", "gui.xmmui.title")));
        this.data = data;
    }

}
