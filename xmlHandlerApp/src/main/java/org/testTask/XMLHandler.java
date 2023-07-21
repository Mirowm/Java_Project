package org.testTask;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
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
import java.util.Scanner;

@Data
public class XMLHandler {
    private List<ADDRObj> addrObjList = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        File addressFile = new File("src/main/resources/AS_ADDR_OBJ.XML");
        //Scanner sc = new Scanner(new File("src/main/resources/Task1.txt"));
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine().trim();
        String str = sc.nextLine().trim();
        sc.close();


        String result = new XMLHandler().task1(date, str, addressFile);
        System.out.println(result);


    }



    public String task1(String date, String str, File addressFile) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        StringBuilder sb = new StringBuilder();
        String[] objID = str.split("[, ]");

        List<ADDRObj> addrObjList = new ArrayList<>();
        int dateInt = Integer.parseInt(date.replaceAll("-", ""));


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(addressFile);

        XPath xPath = XPathFactory.newInstance().newXPath();

        for (String id: objID) {
            String xpathExpression = String.format("//OBJECT[number(translate(@STARTDATE, '-', '')) <= %d and number(translate(@ENDDATE, '-', '')) >= %d and @OBJECTID = '%s']",
                    dateInt, dateInt, id);
                Node node = (Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE);
                if(node != null) {
                    addrObjList.add(new ADDRObj((String) getNodeValue(node, "OBJECTID"),
                            (String) getNodeValue(node, "NAME"),
                            (String) getNodeValue(node, "TYPENAME")));
                }
        }
        for (ADDRObj a: addrObjList) {
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
