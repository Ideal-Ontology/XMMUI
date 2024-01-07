package github.thelawf.xmmui.data;

import github.thelawf.xmmui.client.WidgetAligns;
import org.dom4j.Element;

import java.util.Locale;

public class XMMUIWidgetData implements IXMLValueParser {
    public int x;
    public int y;
    public int width = 100;
    public int height = 30;
    public String id = "";
    public String text = "";
    public WidgetAligns align = WidgetAligns.NONE;

    public XMMUIWidgetData(Element element) {
        this.id = element.attributeValue("id");
        this.text = element.getText().trim();
        this.x = Integer.parseInt(getOrDefault(element, "x" ,"0"));
        this.y = Integer.parseInt(getOrDefault(element, "y" ,"0"));
        this.width = Integer.parseInt(getOrDefault(element, "width", "100"));
        this.height = Integer.parseInt(getOrDefault(element, "height", "30"));
        this.align = getEnumValue(getOrDefault(element, "align", "NONE").toUpperCase(), WidgetAligns.class);
    }

    public int getX() {
        return this.x;
    }

    public XMMUIWidgetData setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return this.y;
    }

    public XMMUIWidgetData setY(int y) {
        this.y = y;
        return this;
    }

    public int getWidth() {
        return this.width;
    }

    public XMMUIWidgetData setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return this.height;
    }

    public XMMUIWidgetData setHeight(int height) {
        this.height = height;
        return this;
    }

    public WidgetAligns getAlign() {
        return this.align;
    }

    public XMMUIWidgetData setAlign(WidgetAligns align) {
        this.align = align;
        return this;
    }
    
    
}
