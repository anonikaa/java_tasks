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

public class Task2 {

    public void goStartCount () {

        File fXml = new File("src/main/resources/str.xml");
        String[] stroka = new String[54];

        try {
            //какая-то дичь для xml-файла
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fXml);

            //зачем-то нормализовали, я не знаю, может это даже не нужно
            doc.getDocumentElement().normalize();


            NodeList nodeLst = doc.getElementsByTagName("input_string1");

            for (int i = 0; i < nodeLst.getLength(); i++) {
                // Выводим информацию по каждому из найденных элементов
                Node node = nodeLst.item(i);

                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;

                    // прошлись по всем тегам str1
                    stroka[i] = element
                            .getElementsByTagName("str1")
                            .item(0)
                            .getTextContent();

                }

            }
        } catch (Exception ei) {
            System.out.println(ei.getMessage());
        }


        Pattern patChars = Pattern.compile("[.,;: ]");
        char [] chars = stroka[0].toCharArray();
        int countPunctuation = 0;

        for (int i = 0; i < chars.length; i++) {
            Matcher match = patChars.matcher(String.valueOf(chars[i]));
            boolean c = match.find();

            if (c) {
                countPunctuation++;
            }

        }


        Pattern pattern = Pattern.compile(" ");
        String[] reg = pattern.split(stroka[0]); // строка0 - исходная строка
        int countWords = 0;

        for (int i = 0; i < reg.length; i++) {
            Pattern pat = Pattern.compile(reg[i]); // строка1 - проверочное слово
            Matcher matcher = pat.matcher(stroka[1]);
            boolean b = matcher.find();


            if (b) {
                countWords++;
            }

        }
        System.out.println("Cлово " + stroka[1] + " встречается: " + countWords + " раз");
        System.out.println("Знаков препинания и пробелов: " + countPunctuation + " штук");
    }
}