package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Differ {

    public static String generate(String firstFile, String secondFile, String format) throws Exception {

    //normalizing recived file paths
        String normiliseFirstPath = String.valueOf(Paths.get(firstFile).toAbsolutePath().normalize());
        String normiliseSecondPath = String.valueOf(Paths.get(secondFile).toAbsolutePath().normalize());
    //checking if both files exists
        File file1 = new File(normiliseFirstPath);
        File file2 = new File(normiliseSecondPath);
        if (!file1.isFile() || !file2.isFile()) {
            throw new IOException("File does not exist");
        }
    //compare two files and reciving resulting map with differences
        Map<String, Object> diffMap = output(normiliseFirstPath, normiliseSecondPath);
    //creating resulting string
        StringBuilder diffString = new StringBuilder("{\n");
    //sorting differences map and filling string
        diffMap.keySet()
                .stream()
                .sorted()
                .forEach((key) -> {switch (key.charAt(key.length() -1 )) {
                    case ('a'):
                        diffString.append("  - " + key.substring(0, key.length() - 1) + " = " + diffMap.get(key) + "\n");
                        break;
                    case ('b'):
                        diffString.append("  + " + key.substring(0, key.length() - 1) + " = " + diffMap.get(key) + "\n");
                        break;
                    case ('c'):
                        diffString.append("    " + key.substring(0, key.length() - 1) + " = " + diffMap.get(key) + "\n");
                        break;
                }
                });

        diffString.append("}");
        return diffString.toString();
    }

    private static Map<String, Object> output(String firstFilePath, String secondFilePath) throws Exception {

    //Parsing files to Map
        Map<String, Object> firstFileData = Parser.parseFile(firstFilePath);
        Map<String, Object> secondFileData = Parser.parseFile(secondFilePath);
    //combining both files data into one map
        HashMap<String, Object> resultMap = new HashMap<>(firstFileData);
        resultMap.putAll(secondFileData);
    //creating new map
        HashMap<String, Object> diffMap = new HashMap<String, Object>();
    //sorting map and creating resulting map with changed keys
    //adding in the end of key string - "a" in case entry is absent in the second file(or value has changed),
    // "b" if entry is added in the second file (or value has changed) and "c" if entry doesn't change
        for (String key : resultMap.keySet()) {
            if (firstFileData.containsKey(key) && (secondFileData.containsKey(key))
                    && (firstFileData.get(key).equals(secondFileData.get(key)))) {
                diffMap.put(key + "c", firstFileData.get(key));
            } else if ((firstFileData.containsKey(key) && (secondFileData.containsKey(key)))) {
                diffMap.put(key + "a", firstFileData.get(key));
                diffMap.put(key + "b", secondFileData.get(key));
            } else if (firstFileData.containsKey(key)) {
                diffMap.put(key + "a", firstFileData.get(key));
            } else if (secondFileData.containsKey(key)) {
                diffMap.put(key + "b", secondFileData.get(key));
            }
        }
        return diffMap;
    }

}
