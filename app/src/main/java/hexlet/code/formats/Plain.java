package hexlet.code.formats;

import hexlet.code.Fields;
import hexlet.code.GetData;
import java.util.Map;
import java.util.Set;

public class Plain {
    public static String form (Map<String, Fields> inputMap) {
        StringBuilder plainString = new StringBuilder();
        inputMap.keySet()
                .stream()
                .sorted()
                .forEach(key -> {
                    var tmpField = inputMap.get(key);
                    if (GetData.isValidFile(tmpField.oldValue) || isNotSimpleClass(tmpField.oldValue)) {
                        tmpField.oldValue = "[complex value]";
                    } else tmpField.oldValue = "'" + tmpField.oldValue + "'";
                    if (GetData.isValidFile(tmpField.newValue) || isNotSimpleClass(tmpField.newValue)) {
                        tmpField.newValue = "[complex value]";
                    } else tmpField.newValue = "'" + tmpField.newValue + "'";
                //я не понял какой должен быть вывод, если ключ есть в обоих файлах и значение не изменилось
                    switch (inputMap.get(key).keyStatus) {
                        case ADDED -> plainString.append("Property '" + key + "' was added with value: " + tmpField.newValue + "\n");
                        case REMOVED -> plainString.append("Property '" + key + "' was removed\n");
                        case UNCHANGED -> plainString.append("    " + key + ": " + inputMap.get(key).oldValue + "\n");
                        case CHANGED -> plainString.append("Property '" + key + "' was updated. From " + tmpField.oldValue +
                                        " to " + tmpField.newValue + "\n" );
                    }
                });
        return plainString.toString();
    }

private static boolean isNotSimpleClass(Object value){
    //check if an object is of primitive class or String
    //наверное можно как то проще
        if (value == null) {
            return false;
        }
        var simpleClasses = Set.of(Boolean.class,
                Character.class,
                Byte.class,
                Short.class,
                Integer.class,
                Long.class,
                Float.class,
                Double.class,
                Void.class,
                String.class);
        return !simpleClasses.contains(value.getClass());
    }
}
