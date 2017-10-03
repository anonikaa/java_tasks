package org;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    public void goStartSearch () {

    File fXml = new File("src/main/resources/str.xml");
    String[] stroka = new String[54];

        try {
        //какая-то дичь для xml-файла
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(fXml);

        //зачем-то нормализовали, я не знаю, может это даже не нужно
        doc.getDocumentElement().normalize();


        NodeList nodeLst = doc.getElementsByTagName("input_string");

        for (int i = 0; i < nodeLst.getLength(); i++) {
            // Выводим информацию по каждому из найденных элементов
            Node node = nodeLst.item(i);

            if (Node.ELEMENT_NODE == node.getNodeType()) {
                Element element = (Element) node;

                // прошлись по всем тегам str
                stroka[i] = element
                        .getElementsByTagName("str")
                        .item(0)
                        .getTextContent();

            }

        }
    } catch (Exception ei) {
        System.out.println(ei.getMessage());

    }


    Pattern pattern = Pattern.compile(" ");
    String[] reg = pattern.split(stroka[0]);

        for (int i=0; i<reg.length; i++) {
        Pattern pat = Pattern.compile(reg[i]); //каждый элемент регулярки стал регуляркой
        Matcher matcher = pat.matcher(stroka[1]);
        boolean b = matcher.find();


        if (!b){
            System.out.println(reg[i]);
        }
    }



}
}

