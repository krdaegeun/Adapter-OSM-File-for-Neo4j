import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import util.OSMHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

import static setting.setting.fileAddress;

public class OSMHandlerTest {

    @BeforeAll
    public static void parseOSMFIle(){
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            OSMHandler osmHandler = new OSMHandler();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File(fileAddress), osmHandler);
        } catch (SAXException | ParserConfigurationException | IOException e){
            e.printStackTrace();
        }
    }



    @Test
    public void readBoundTest() {
        //given

    }
}
