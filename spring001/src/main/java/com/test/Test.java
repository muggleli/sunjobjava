package com.test;


import junit.framework.TestCase;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.sql.rowset.spi.XmlWriter;
import javax.xml.stream.XMLStreamException;
import java.io.FileWriter;
import java.io.IOException;

public class Test extends TestCase {
    public void _testCreate() throws IOException, XMLStreamException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("heros");

        Element element = root.addElement("hero");
        element.addAttribute("id", "1");
        element.addAttribute("name","亚索");

        Element element2 = root.addElement("hero");
        element2.addAttribute("id", "2");
        element2.addAttribute("name","亚瑟");

        Element element3 = root.addElement("hero");
        element3.addAttribute("id", "3");
        element3.addAttribute("name","亚伦");

        XMLWriter xmlWriter = new XMLWriter(new FileWriter("C://Users//hutlhj//Desktop/a.xml"), OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        xmlWriter.close();
    }

    public void _testSelectOne() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("C://Users//hutlhj//Desktop/a.xml");

        Element element = (Element) document.selectSingleNode("heros/hero[@id=1]");
        System.out.println(element.getText());
        System.out.println(element.attribute("id").getValue());
        System.out.println(element.attribute("name").getValue());
    }


}
