package util;

import org.xml.sax.Attributes;

// SAX : XML parser
public class SaxHelper {

    public static long asLong(Attributes attribute, String qName){
        return Long.parseLong(attribute.getValue(qName));
    }

    public static double asDouble(Attributes attribute, String qName){
        return Double.parseDouble(attribute.getValue(qName));
    }

    public static boolean asBoolean(Attributes attribute, String qName){
        return Boolean.parseBoolean(attribute.getValue(qName));
    }

    public static String asString(Attributes attribute, String qName){
        return attribute.getValue(qName);
    }

    public static Short asShort(Attributes attribute, String qName){
        return Short.parseShort(attribute.getValue(qName));
    }
}
