package hexlet.code.formats;

import hexlet.code.Differ;
import hexlet.code.Fields;
import java.util.Map;
import java.util.Set;

public class Plain {
    public static String form(Map<String, Fields> inputMap) {
        StringBuilder plainString = new StringBuilder();
        inputMap.keySet()
                .stream()
                .sorted()
                .forEach(key -> {
                    var tmpField = inputMap.get(key);

                    if (Differ.isValidFile(tmpField.getOldValue()) || isNotSimpleClass(tmpField.getOldValue())) {
                        tmpField.setOldValue("[complex value]");
                    } else if (tmpField.getOldValue() instanceof String) {
                        tmpField.setOldValue("'" + tmpField.getOldValue() + "'");
                    }
                    if (Differ.isValidFile(tmpField.getNewValue()) || isNotSimpleClass(tmpField.getNewValue())) {
                        tmpField.setNewValue("[complex value]");
                    } else if (tmpField.getNewValue() instanceof String) {
                        tmpField.setNewValue("'" + tmpField.getNewValue() + "'");
                    }
                    switch (inputMap.get(key).getKeyStatus()) {
                        case ADDED -> plainString.append("Property '" + key + "' was added with value: "
                                + tmpField.getNewValue() + "\n");
                        case REMOVED -> plainString.append("Property '" + key + "' was removed\n");
                        case CHANGED -> plainString.append("Property '" + key + "' was updated. From "
                                + tmpField.getOldValue() + " to " + tmpField.getNewValue() + "\n");
                        default -> plainString.append("");
                    }
                });
        return plainString.substring(0, plainString.toString().length() - 1);
    }

    private static boolean isNotSimpleClass(Object value) {
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
