package github.thelawf.xmmui.data;

import net.minecraft.util.ResourceLocation;
import org.dom4j.Element;

public class XMMUIImageData extends XMMUIWidgetData{
    public int u;
    public int v;
    public ResourceLocation textureSrc;
    public XMMUIImageData(Element element) {
        super(element);
        this.u = Integer.parseInt(getOrDefault(element, "u", "0"));
        this.v = Integer.parseInt(getOrDefault(element, "v", "0"));
        this.textureSrc = new ResourceLocation(element.attributeValue("texture_src"));
    }
}
