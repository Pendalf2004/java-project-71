package hexlet.code;

import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;
import java.util.Map;

public class Formator {

    public static String stylish(Map<String, Fields> inputMap) {
        return Stylish.form(inputMap);
    }

    public static String plain(Map<String, Fields> inputMap) {
        return Plain.form(inputMap);
    }
}
