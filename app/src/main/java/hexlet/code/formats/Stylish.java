package hexlet.code.formats;

import hexlet.code.DataCompare;
import hexlet.code.Differ;
import hexlet.code.Parser;
import java.util.Map;

public class Stylish {

    public static String form(Map<String, DataCompare> inputMap) {
        StringBuilder stylishString = new StringBuilder("{\n");
        inputMap.keySet()
                .stream()
                .sorted()
                .forEach(key -> {
                    var tmpFields = inputMap.get(key);
                    if (Differ.isValidFile(tmpFields.getOldValue())) {
                        try {
                            tmpFields.setOldValue(getFileString(tmpFields.getOldValue().toString()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (Differ.isValidFile(tmpFields.getNewValue())) {
                        try {
                            tmpFields.setNewValue(getFileString(tmpFields.getNewValue().toString()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    switch (tmpFields.getKeyStatus()) {
                        case ADDED -> stylishString.append(
                                "  + " + key + ": " + inputMap.get(key).getNewValue() + "\n");
                        case REMOVED -> stylishString.append(
                                "  - " + key + ": " + inputMap.get(key).getOldValue() + "\n");
                        case UNCHANGED -> stylishString.append(
                                "    " + key + ": " + inputMap.get(key).getOldValue() + "\n");
                        case CHANGED -> stylishString.append(
                                "  - " + key + ": " + inputMap.get(key).getOldValue() + "\n"
                                + "  + " + key + ": " + inputMap.get(key).getNewValue() + "\n");
                        default -> stylishString.append("\n");
                    }
                });
        return stylishString + "}";
    }

    private static String getFileString(String filePath) throws Exception {
        return Parser.parseData(Differ.readFile(filePath), Differ.getExtension(filePath)).toString();
    }

}
