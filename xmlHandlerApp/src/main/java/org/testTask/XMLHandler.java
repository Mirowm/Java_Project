package org.testTask;


import lombok.Data;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;



@Data
public class XMLHandler {
    private List<ADDRObj> addrObjList = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        File addressFile = new File("src/main/resources/AS_ADDR_OBJ.XML");
        XMLHandler handler = new XMLHandler();
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        System.out.println("-----------------------------------Task-1-----------------------------------------------------");
        //Scanner sc = new Scanner(new File("src/main/resources/Task1.txt"));
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine().trim();
        String str = sc.nextLine().trim();
        sc.close();
        System.out.println(task1.findAddress(date, str, addressFile));

        System.out.println("-----------------------------------Task-2-----------------------------------------------------");
        System.out.println(task2.findAddress(addressFile, "проезд"));


    }
}
