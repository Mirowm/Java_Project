package org.testTask;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public String findAddress(String date, String str, File addressFile) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        StringBuilder sb = new StringBuilder();

        String[] objID = str.split("[, ]");

        List<ADDRObj> addrObjList = new ArrayList<>();

        int dateInt = Integer.parseInt(date.replaceAll("-", ""));

        //парсер
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(addressFile);

        XPath xPath = XPathFactory.newInstance().newXPath();
        // id из массива вставляем с датой в выражение xpath
        for (String id: objID) {
            String xpathExpression = String.format("//OBJECT[number(translate(@STARTDATE, '-', '')) <= %d and number(translate(@ENDDATE, '-', '')) >= %d and @OBJECTID = '%s']",
                    dateInt, dateInt, id);
            //нода соответствует условию
            Node node = (Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE);
            if(node != null) {
                addrObjList.add(new ADDRObj((String) getNodeValue(node, "OBJECTID"),
                        (String) getNodeValue(node, "NAME"),
                        (String) getNodeValue(node, "TYPENAME"))); //создаем объект, с значениями атрибутов из ноды, кидаем в лист
            }
        }
        for (ADDRObj a: addrObjList) {
            sb.append(a.getObjectID()+": ");
            sb.append(a.toString());
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    private String getNodeValue(Node node, String attributeName) {
        Node attributeNode = node.getAttributes().getNamedItem(attributeName);
        if (attributeNode != null) {
            return attributeNode.getNodeValue();
        }
        return "";
    }
}
