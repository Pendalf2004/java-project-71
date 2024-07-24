package hexlet.code;

import hexlet.code.formats.JSON;
import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;
import java.io.IOException;
import java.util.Map;

public class Formator {

    public static String getString(Map<String, Fields> diffMap, String format) throws IOException {
        switch (format) {
            case "plain" -> {
                return Plain.form(diffMap);
            }
            case "json" -> {
                return JSON.form(diffMap);
            }
            default -> {
                return Stylish.form(diffMap);
            }
        }
    }

}
