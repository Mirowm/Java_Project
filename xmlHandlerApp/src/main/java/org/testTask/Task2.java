package org.testTask;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

public class Task2 {
    public String findAddress(File addressFile, String typeName) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        List<ADDRObj> addrObjList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(addressFile);

        XPath xPath = XPathFactory.newInstance().newXPath();

        String xpathExpression = String.format("//OBJECT[@TYPENAME='%s' and @ISACTUAL='1' and @ISACTIVE='1']", typeName);
        NodeList nodeList = (NodeList) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {

            String address = getNodeValue(nodeList.item(i), "NAME");
            String objId = getNodeValue(nodeList.item(i), "OBJECTID");
            String typeaddr = getNodeValue(nodeList.item(i), "TYPENAME");
            ADDRObj addrObj = new ADDRObj();

            addrObj.setName(address);
            addrObj.setAddress(String.format("%s %s",typeaddr, address) );
            addrObj.setObjectID(objId);
            addrObj.setTypeName(typeName);
            addrObjList.add(addrObj);
        }
        for (ADDRObj a: addrObjList) {
            setFullAddress(a);
        }
        for (ADDRObj a: addrObjList) {
            sb.append(a.getAddress());
            sb.append("\n");
        }

        return sb.toString().trim();
    }
    private void setFullAddress(ADDRObj addrObj) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
             DocumentBuilder builder = factory.newDocumentBuilder();
             Document document = builder.parse("src/main/resources/AS_ADM_HIERARCHY.XML");

             XPath xPath = XPathFactory.newInstance().newXPath();
             String xpathExpression = String.format("//ITEM[@OBJECTID='%s' and @ISACTIVE='1']", addrObj.getObjectID());

             Node node = (Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE);
             String objId = getNodeValue(node, "PARENTOBJID");
             String isActive = getNodeValue(node, "ISACTIVE");
             addrObj.setActiveParent(isActive);
             addrObj.setObjectID(objId);
             setSubAddress(addrObj); //рекурсия
    }
    private void setSubAddress(ADDRObj addrObj) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("src/main/resources/AS_ADDR_OBJ.XML");

        XPath xPath = XPathFactory.newInstance().newXPath();

        String xpathExpression = String.format("//OBJECT[@OBJECTID ='%s' and @ISACTUAL='1' and @ISACTIVE='1']",addrObj.getObjectID());
        Node node = (Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE);
        if(node != null) {
            String address = getNodeValue(node, "NAME");
            String objId = getNodeValue(node, "OBJECTID");
            String typeaddr = getNodeValue(node, "TYPENAME");

            addrObj.setAddress(String.format("%s %s %s", typeaddr, address, addrObj.getAddress()));
            addrObj.setObjectID(objId);
            setFullAddress(addrObj); //рекурсия
        }
    }
    private String getNodeValue(Node node, String attributeName) {
        Node attributeNode = node.getAttributes().getNamedItem(attributeName);
        if (attributeNode != null) {
            return attributeNode.getNodeValue();
        }
        return "";
    }
}
