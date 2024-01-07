# XMMUI (Extendable Minecraft Markdown User Interface)
  
This project is to help Minecraft mod authors to use XML documents to create Game User Interface.<br>
## Getting Started
1. Create an XML format file called ```test.xml``` in your mod project under the path ```resources/assets/your_mod_id/xmmui/test.xml```.
2. A legal XML file must start with the label of ```<?xml version="1.0" encoding="UTF-8" ?>```. Then you have to create 
a root element called ```<xmmui> </xmmui>```. The XML file should look like below:<br>
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<xmmui>
</xmmui>
```
```<xmmui> </xmmui>``` element has these attribute values:
- ```has_tile_entity``` default value: ```"false"```
- ```has_container``` default value: ```"false"```
- ```has_inventory``` default value: ```"false"```
3. Now you can add child elements to the screen. For example, the code below will create a text label, the text within 
the element will be drawn to (0,0) on the screen:
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<xmmui>
    <text_label>
        gui.xmmui.test_screen.title
    </text_label>
</xmmui>
```
```<text_label> </text_label>``` element has these attribute values:
- ```x``` default value: ```0```
- ```y``` default value: ```0```
- ```width``` default value: ```100```
- ```height``` default value: ```30```
- ```align``` default value: ```NONE```
- ```bold``` default value: ```false```
- ```italic``` default value: ```false```
- ```del``` default value: ```false```
4. You can display this gui by using 
```java
Minecraft minecraft = Minecraft.getInstance();
TranslationTextComponent text = new TranslationTextComponent("gui.your_mod_id.test_screen.title");
ResourceLocation xmlPath = new ResourceLocation("your_mod_id", "xmmui/test.xml")
XMMUIScreen xmmui = new XMMUIScreen(text, UIHelper.getXMLText(xmlPath));
minecraft.displayGUI(xmmui);
```
5. More functions are under development, please look forward to future updates ~