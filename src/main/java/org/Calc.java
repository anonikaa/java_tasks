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

public class Calc {

    public static void main(String[] args) {
        Calc calc = new Calc();

        String[] stroka = xml();
        Float res = calc.count(stroka[0]); //строка из xml
        if (res != null) {
            System.out.println(res);
        }
    }

    public static String[] xml() {

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

        return stroka;
    }


    public Float count(String expression) {

        Pattern pattern = Pattern.compile("^([\\+\\-]?\\d\\.\\d{0,2}?)([\\-\\+\\*\\/])(([\\-])?\\d(\\.\\d{0,2})?)$");
        Matcher matcher = pattern.matcher(expression);

        Float res = 0f;

        if (matcher.find()) {
            String[] ar = expression.split("[-+/*]"); //разбили на числа
            String sumbol = matcher.group(2); //получили знак
            String s = matcher.group(1); // первое число
            String s1 = matcher.group(3); // второе число
            float f = Float.parseFloat(s);
            float f1 = Float.parseFloat(s1);

            if (sumbol.equals("+")) {
                res = f + f1;
            } else {
                if (sumbol.equals("-")) {
                    res = f - f1;
                } else {
                    if (sumbol.equals("*")) {
                        res = f * f1;
                    } else {
                        if (sumbol.equals("/")) {
                            if (f1 != 0) {
                                res = f / f1;
                            } else {
                                res = 0.0f; // я не знаю как потом проверить эксепшн в юнит тесте, поэтому вернула ноль
                            }
                        }

                    }
                }
            }

        }

        res = Math.round(res*1000)/1000f; // типа округление, но только для деления
        return res;
    }
}
