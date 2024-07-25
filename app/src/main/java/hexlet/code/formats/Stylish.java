package hexlet.code.formats;

import hexlet.code.Fields;
import hexlet.code.GetData;
import hexlet.code.Parser;
import java.util.Map;

public class Stylish {
    public static String form(Map<String, Fields> inputMap) {
        StringBuilder stylishString = new StringBuilder("{\n");
        inputMap.keySet()
                .stream()
                .sorted()
                .forEach(key -> {
                    Fields tmpFields = inputMap.get(key);
                    if (GetData.isValidFile(tmpFields.oldValue)) {
                        try {
                            tmpFields.oldValue = getFileString(tmpFields.oldValue.toString());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (GetData.isValidFile(tmpFields.newValue)) {
                        try {
                            tmpFields.newValue = getFileString(tmpFields.newValue.toString());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    switch (tmpFields.keyStatus) {
                        case ADDED -> stylishString.append("  + " + key + ": " + inputMap.get(key).newValue + "\n");
                        case REMOVED -> stylishString.append("  - " + key + ": " + inputMap.get(key).oldValue + "\n");
                        case UNCHANGED -> stylishString.append("    " + key + ": " + inputMap.get(key).oldValue + "\n");
                        case CHANGED -> stylishString.append("  - " + key + ": " + inputMap.get(key).oldValue + "\n"
                                + "  + " + key + ": " + inputMap.get(key).newValue + "\n");
                        default -> stylishString.append("\n");
                    }
                });
        return stylishString + "}";
    }

    private static String getFileString(String filePath) throws Exception {
        return Parser.parseData(GetData.readFile(filePath), GetData.getExtension(filePath)).toString();
    }

}
