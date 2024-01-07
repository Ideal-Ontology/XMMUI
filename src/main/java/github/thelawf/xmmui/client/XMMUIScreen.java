package github.thelawf.xmmui.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.xmmui.data.IXMLValueParser;
import github.thelawf.xmmui.data.XMMUIData;
import github.thelawf.xmmui.data.XMMUITextData;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public abstract class XMMUIScreen extends Screen implements IXMLValueParser {
    private HashMap<String, Widget> widgetSet;
    private List<XMMUITextData> textData = new ArrayList<>();
    public Document document;
    private XMMUIData xmmui;

    /**
     * 获取获取根标签，获取XMMUI的反序列化数据<br>
     * this.textData can be fetched by turning root.elements()
     * into java.Stream and then filter the result by element name.
     * If the name is equivalent to text_label,
     * then map the element by providing the element into XMMUITextData constructor
     *
     * @param titleIn 标题
     * @param xmlText xml文档的内容
     * @throws DocumentException 文档解析异常
     */
    protected XMMUIScreen(ITextComponent titleIn, String xmlText) throws DocumentException {
        super(titleIn);
        document = DocumentHelper.parseText(xmlText);
        Element root = document.getRootElement();
        this.textData = root.elements().stream().filter(element -> element.getName().equals("text_label"))
                .map(XMMUITextData::new).collect(Collectors.toList());
    }

    @Override
    protected void init() {
        super.init();
        // 获取所有表单元素及其下方的组件元素，根据组件元素的标签将其内部的属性值赋给TextFieldWidget或者Button
    }

    /**
     * MCP的反混淆有问题，drawString最后三个整型形参的名称应该为 int x, int y, int hexColor.<br>
     * 即 drawString(MatrixStack matrixStack, FontRenderer font, String text, int x, int y, int hexColor);
     */
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        for (XMMUITextData data : this.textData) {
            drawStringFromData(matrixStack, this.font, data);
            LogManager.getLogger().info("width: {}, height: {}", this.width, this.height);
        }
        // this.textData.forEach(data -> drawStringFromData(matrixStack, this.font, data));
    }

    public Widget getComponentById(String id){
        return widgetSet.get(id);
    }

    public void drawStringFromData(MatrixStack matrixStack, FontRenderer font, XMMUITextData data) {
        TranslationTextComponent text = new TranslationTextComponent(data.text);
        switch (data.align) {
            case TOP:
                data.y = 0;
                break;
            case BOTTOM:
                data.y = this.height;
                break;
            case LEFT:
                data.x = 0;
                break;
            case RIGHT:
                data.x = this.width;
                break;
            case X_CENTERED:
                data.x = data.x - font.func_243245_a(text.func_241878_f()) / 2;
                break;
            case TOP_CENTERED:
                data.y = 0;
                data.x = data.x - font.func_243245_a(text.func_241878_f()) / 2;
            case HORIZONTAL_CENTERED:
                data.y = this.width / 2;
                data.x = data.x - font.func_243245_a(text.func_241878_f()) / 2;
                break;
            case LEFT_CENTERED:
            case RIGHT_CENTERED:
            case BOTTOM_CENTERED:
            case NONE:
            default:
                break;
        }
        drawString(matrixStack, this.font, text, data.x, data.y, data.hexColor);
    }
}
