package github.thelawf.xmmui.data;

import org.dom4j.Element;

import java.util.Arrays;
import java.util.List;

public interface IXMLValueParser {
    /**
     * @param value 字符串，为XML属性的值
     * @return 将原始XML属性值字符串按照分号分割之后的列表
     */
    default List<String> separateBySemicolon(String value) {
        return Arrays.asList(value.split(";"));
    }

    default List<String> separateByComma(String value) {
        return Arrays.asList(value.split(","));
    }
    default List<String> separateByBlank(String value) {
        return Arrays.asList(value.split(" "));
    }
    default List<String> separateByColon(String value) {
        return Arrays.asList(value.split(":"));
    }

    default boolean getBoolValue(String value) {
        return Boolean.parseBoolean(value.split("=")[1]);
    }

    default <T extends Enum<T>> T getEnumValue(String value, Class<T> enumClass) {
        return Enum.valueOf(enumClass, value);
    }

    default <T extends Enum<T>> T getEnumOrDefault(String value, Class<T> enumClass, T defaultValue) {
        return Enum.valueOf(enumClass, value);
    }

    default int getIntValue(String value) {
        return Integer.parseInt(value);
    }
    default int parseRGBAColor(String hexColor) {
        return hexColor.contains("#") ? Integer.parseInt(hexColor.substring(1,hexColor.length()-1)) : 0;
    }

    default float parsePercent(String percent) {
        return percent.contains("%") ? Float.parseFloat(percent.substring(percent.length()-2, percent.length()-1)) / 100 : 0;
    }

    default String getOrDefault(Element element, String key, String defaultValue) {
        return element.attributeValue(key) == null ? defaultValue : element.attributeValue(key);
    }
}
