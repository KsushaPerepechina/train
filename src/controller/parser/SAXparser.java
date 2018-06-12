package controller.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class SAXparser {
    List<String> lines = new ArrayList<>();
    public final String notFound = "Нет элемента";

    public SAXparser(String name) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            for (String train : lines)
                System.out.println(train);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String startTag(String name) {

        return "[\t||\\s]*<" + name + "[A-Z||a-z||А-Я||а-я||0-9||\\.||'|| ]*>";
    }

    public String lastTag(String name) {

        return "</" + name + ">";
    }

    public String startElement(String name) {

        int index = 0;
        for (String line : lines) {
            if (line.contains(name) && !line.contains(name + "s")) {
                index = lines.indexOf(line);
                break;
            } else index = -1;
        }
        if (index == -1)
            return notFound;
        else
            return lines.get(index);
    }

    public String lastElement(String name) {

        int index = 0;
        for (String line : lines) {
            if (line.contains(lastTag(name))) {
                index = lines.indexOf(line);
                break;
            }
        }
        return lines.get(index);
    }


    public String characters(String startTag, String endTag) {
        String characters = "";
        for (String element : lines) {
            characters += element;
            if (element.contains(endTag))
                break;
        }
        characters = Pattern.compile(startTag).matcher(characters).replaceFirst("");
        characters = Pattern.compile(endTag).matcher(characters).replaceFirst("");
        characters = Pattern.compile("\t+").matcher(characters).replaceAll(" ");
        return characters;
    }

    public String nextElement() {

        int index = 0;
        lines.remove(index);
        return lines.get(index);
    }

    public String nextElement(String name) {

        int index = 0;
        while (lines.get(index) != name)
            lines.remove(index);
        return lines.get(++index);
    }
}
